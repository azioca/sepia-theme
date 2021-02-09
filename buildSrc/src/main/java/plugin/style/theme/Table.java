package plugin.style.theme;

import plugin.domain.Color;

import java.util.Objects;

public class Table {

	private final Theme theme;

	public Table(Theme theme) {
		this.theme = Objects.requireNonNull(theme);
	}

	public Color background() {
		return theme.background().base().brighter();
	}

	public Color alternativeRowBackground() {
		return background().darker();
	}

	public Color hoverBackground() {
		return alternativeRowBackground().darker();
	}

	public Color selectionInactiveBackground() {
		return hoverBackground().darker();
	}

	public Color selectionBackground() {
		return selectionInactiveBackground().darker();
	}

	public Color focusBackground() {
		return selectionBackground();
	}

	public Color stripe() {
		return alternativeRowBackground();
	}

	public Color grid() {
		return theme.borderColor().brighter();
	}

	public Color sortIcon() {
		return selectionBackground().brighter();
	}

	public Header header() {
		return new Header();
	}

	public class Header {

		public Color background() {
			return selectionBackground();
		}

		public Color separator() {
			return theme.borderColor();
		}

		public Color focusCellBackground() {
			return focusBackground();
		}
	}
}
