package plugin.style;

import plugin.domain.Color;

public class Checkbox {

	private final Style style;

	Checkbox(Style style) {
		this.style = style;
	}

	public Color textForeground() {
		return style.foreground().base();
	}

	public Color textBackground() {
		return style.ui().background().base();
	}

	public Color focus() {
		return border().brighter();
	}

	public Color hook() {
		return style.ui().background().base().brightest();
	}

	public Color selectedBoxBackground() {
		return style.ui().background().base().darkest();
	}

	public Color disabledBoxBackground() {
		return style.ui().background().base();
	}

	public Color border() {
		return selectedBoxBackground();
	}

	public Color disabledBorder() {
		return border().brighter();
	}
}
