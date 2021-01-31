package plugin.style;

import plugin.domain.Color;

import java.util.Objects;

public class Table {

	private final Style style;

	public Table(Style style) {
		this.style = Objects.requireNonNull(style);
	}

	public Color background() {
		return style.background().ui().base().brighter();
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
		return style.borderColor().brighter();
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
			return style.borderColor();
		}

		public Color focusCellBackground() {
			return focusBackground();
		}
	}
}
