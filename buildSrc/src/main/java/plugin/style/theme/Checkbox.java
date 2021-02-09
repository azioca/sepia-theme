package plugin.style.theme;

import plugin.domain.Color;

public class Checkbox {

	private final Theme theme;

	public Checkbox(Theme theme) {
		this.theme = theme;
	}

	public Color textForeground() {
		return theme.foreground().base();
	}

	public Color textBackground() {
		return theme.background().base();
	}

	public Color focus() {
		return border().brighter();
	}

	public Color hook() {
		return theme.background().base().brightest();
	}

	public Color selectedBoxBackground() {
		return theme.background().base().darkest();
	}

	public Color disabledBoxBackground() {
		return theme.background().base();
	}

	public Color border() {
		return selectedBoxBackground();
	}

	public Color disabledBorder() {
		return border().brighter();
	}
}
