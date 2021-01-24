package plugin.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import plugin.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Color {

	private final Slider slider;
	private final Transparency transparency;
	private final boolean presentAsHex;

	public Color(int chosen, String... hues) {
		this(chosen, Arrays.stream(hues).map(Slider.Hue::new).collect(Collectors.toList()));
	}

	private Color(int chosen, List<Slider.Hue> hues) {
		this(new Slider(chosen, hues), Transparency.opaque, true);
	}

	private Color(Slider slider, Transparency transparency, boolean presentAsHex) {
		this.slider = slider;
		this.transparency = transparency;
		this.presentAsHex = presentAsHex;
	}

	public Color darker(int times) {
		Color darker = darker();
		for (int i = 0; i < times - 1; i++) {
			darker = darker.darker();
		}
		return darker;
	}

	public Color darker() {
		return new Color(slider.slideToDarker(), transparency, presentAsHex);
	}

	public Color brighter(int times) {
		Color brighter = brighter();
		for (int i = 0; i < times - 1; i++) {
			brighter = brighter.brighter();
		}
		return brighter;
	}

	public Color brighter() {
		return new Color(slider.slideToBrighter(), transparency, presentAsHex);
	}

	public Color transparent(String transparency) {
		return new Color(slider, new Transparency(transparency), presentAsHex);
	}

	public Color hex() {
		return new Color(slider, transparency, true);
	}

	public Color plain() {
		return new Color(slider, transparency, false);
	}

	@JsonValue
	public String asString() {
		return (presentAsHex ? "#" : "") + slider.chosen().asString() + transparency.asString();
	}

	public static class Transparency extends StringValue {

		static Transparency opaque = new Transparency("");

		Transparency(String id) {
			super(id);
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
			if (chosen < 0) throw new IllegalStateException("Slider doesn't have such a dark element. " + this);
			return new Slider(chosen, hues);
		}

		Slider slideToBrighter() {
			if (chosen >= hues.size()) throw new IllegalStateException("Slider doesn't have such a bright element. " + this);
			return new Slider(chosen + 1, hues);
		}

		@Override
		public String toString() {
			return ToString.toString(this);
		}

		private static class Hue extends StringValue {

			protected Hue(String id) {
				super(id);
			}
		}
	}
}
