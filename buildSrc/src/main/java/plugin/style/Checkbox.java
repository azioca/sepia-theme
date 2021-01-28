package plugin.style;

import plugin.domain.Color;

public class Checkbox {

	private final Style style;

	Checkbox(Style style) {
		this.style = style;
	}

	public Color textForeground() {
		return style.foreground();
	}

	public Color textBackground() {
		return style.background().ui().base();
	}

	public Color focus() {
		return border().brighter();
	}

	public Color hook() {
		return style.background().ui().base().brightest();
	}

	public Color selectedBoxBackground() {
		return style.background().ui().base().darkest();
	}

	public Color disabledBoxBackground() {
		return style.background().ui().base();
	}

	public Color border() {
		return selectedBoxBackground();
	}

	public Color disabledBorder() {
		return border().brighter();
	}
}
