package plugin.domain.color;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Color {

	private final Hue hue;
	private final Opacity opacity;
	private final boolean presentAsHex;

	public static Color transparent() {
		return new Color("000000").opacity(0);
	}

	public Color(String value) {
		this(0, value);
	}

	public Color(int chosen, String... hues) {
		this(chosen, Arrays.stream(hues).map(Value::new).collect(Collectors.toList()));
	}

	private Color(int chosen, List<Value> values) {
		this(new Hue(chosen, values), Opacity.opaque, false);
	}

	private Color(Hue hue, Opacity opacity, boolean presentAsHex) {
		this.hue = hue;
		this.opacity = opacity;
		this.presentAsHex = presentAsHex;
	}
	public Color darkest() {
		return new Color(hue.chooseDarkest(), opacity, presentAsHex);
	}

	public Color darker(int times) {
		Color darker = darker();
		for (int i = 0; i < times - 1; i++) {
			darker = darker.darker();
		}
		return darker;
	}

	public Color darker() {
		return new Color(hue.chooseDarker(), opacity, presentAsHex);
	}

	public Color brightest() {
		return new Color(hue.chooseBrightest(), opacity, presentAsHex);
	}

	public Color brighter(int times) {
		Color brighter = brighter();
		for (int i = 0; i < times - 1; i++) {
			brighter = brighter.brighter();
		}
		return brighter;
	}

	public Color brighter() {
		return new Color(hue.chooseBrighter(), opacity, presentAsHex);
	}

	public Color opaque() {
		return opacity(1);
	}

	public Color opacity(double opacity) {
		return new Color(hue, new Opacity(opacity), presentAsHex);
	}

	public Color hex() {
		return new Color(hue, opacity, true);
	}

	public Color plain() {
		return new Color(hue, opacity, false);
	}

	@JsonValue
	public String string() {
		return (presentAsHex ? hue.chosen().hex() : hue.chosen().plain()) + opacity.string();
	}
}
