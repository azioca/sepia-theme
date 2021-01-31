package plugin.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import plugin.lang.AsString;
import plugin.lang.Check;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Color {

	private final Slider slider;
	private final Opacity opacity;
	private final boolean presentAsHex;

	public static Color transparent() {
		return new Color("000000").opacity(0);
	}

	public Color(String hue) {
		this(0, hue);
	}

	public Color(int chosen, String... hues) {
		this(chosen, Arrays.stream(hues).map(Slider.Hue::new).collect(Collectors.toList()));
	}

	private Color(int chosen, List<Slider.Hue> hues) {
		this(new Slider(chosen, hues), Opacity.opaque, false);
	}

	private Color(Slider slider, Opacity opacity, boolean presentAsHex) {
		this.slider = slider;
		this.opacity = opacity;
		this.presentAsHex = presentAsHex;
	}

	public Color darkest() {
		return new Color(slider.slideToDarkest(), opacity, presentAsHex);
	}

	public Color darker(int times) {
		Color darker = darker();
		for (int i = 0; i < times - 1; i++) {
			darker = darker.darker();
		}
		return darker;
	}

	public Color darker() {
		return new Color(slider.slideToDarker(), opacity, presentAsHex);
	}

	public Color brightest() {
		return new Color(slider.slideToBrightest(), opacity, presentAsHex);
	}

	public Color brighter(int times) {
		Color brighter = brighter();
		for (int i = 0; i < times - 1; i++) {
			brighter = brighter.brighter();
		}
		return brighter;
	}

	public Color brighter() {
		return new Color(slider.slideToBrighter(), opacity, presentAsHex);
	}

	public Color opaque() {
		return opacity(1);
	}

	public Color opacity(double opacity) {
		return new Color(slider, new Opacity(opacity), presentAsHex);
	}

	public Color hex() {
		return new Color(slider, opacity, true);
	}

	public Color plain() {
		return new Color(slider, opacity, false);
	}


	@JsonValue
	public String string() {
		return (presentAsHex ? "#" : "") + slider.chosen().string() + opacity.string();
	}

	private static class Opacity {
		static final Opacity opaque = new Opacity(1);

		private final double opacity;

		Opacity(double opacity) {
			this.opacity = Check.argument(opacity, o -> o >= 0 && o <= 1, "Should be in range [0, 1]");
		}

		String string() {
			if (opaque.equals(this)) {
				return "";
			} else {
				return String.format("%02X", (int) (opacity * 255)).toLowerCase();
			}
		}

		@Override public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Opacity)) return false;
			Opacity opacity1 = (Opacity) o;
			return opacity == opacity1.opacity;
		}
		@Override public int hashCode() {
			return Objects.hash(opacity);
		}
		@Override public String toString() {
			return new AsString(this).string();
		}
	}

	private static class Slider {
		private final int chosen;
		private final List<Hue> hues;

		Slider(int chosen, List<Hue> hues) {
			this.chosen = chosen;
			this.hues = List.copyOf(hues);
		}

		Hue chosen() {
			return hues.get(chosen);
		}

		Slider slideToDarker() {
			int chosen = this.chosen - 1;
			if (chosen < 0) throw new IndexOutOfBoundsException("No such dark hue defined. " + this);
			return new Slider(chosen, hues);
		}

		public Slider slideToDarkest() {
			return new Slider(0, hues);
		}

		Slider slideToBrighter() {
			if (chosen >= hues.size()) throw new IndexOutOfBoundsException("No such bright hue defined. " + this);
			return new Slider(chosen + 1, hues);
		}

		public Slider slideToBrightest() {
			return new Slider(hues.size() - 1, hues);
		}

		@Override
		public String toString() {
			return new AsString(this).string();
		}

		private static class Hue extends StringValue {

			protected Hue(String id) {
				super(id);
			}
		}
	}
}
