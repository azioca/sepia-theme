package plugin.style.theme;

import plugin.domain.Color;

import java.util.Objects;

public class Button {

	private final Theme theme;

	public Button(Theme theme) {
		this.theme = Objects.requireNonNull(theme);
	}

	public Color around() {
		return theme.background().base();
	}

	public Ordinary ordinary() {
		return new Ordinary();
	}

	public Default Default() {
		return new Default();
	}

	public class Ordinary {

		public Color background() {
			return theme.background().base().darker();
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
			return theme.focus();
		}
	}
}
