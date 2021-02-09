package plugin.style.theme;

import plugin.domain.Color;
import plugin.style.Style;

import java.util.Objects;

public class Button {

	private final Style style;

	public Button(Style style) {
		this.style = Objects.requireNonNull(style);
	}

	public Color around() {
		return style.theme().background().base();
	}

	public Ordinary ordinary() {
		return new Ordinary();
	}

	public Default Default() {
		return new Default();
	}

	public class Ordinary {

		public Color background() {
			return style.theme().background().base().darker();
		}

		public Color border() {
			return background().darker(2);
		}

		public Color disabledBorder() {
			return border().brighter();
		}

		public Color focusedBorder() {
			return border();
		}
	}

	public class Default {

		public Color background() {
			return Button.this.ordinary().background().darker(2);
		}

		public Color border() {
			return background().darker();
		}

		public Color focusedBorder() {
			return style.scheme().background().selectedText();
		}
	}
}
