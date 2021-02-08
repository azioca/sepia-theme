package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Editor {

	private final Palette palette;

	public Editor(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Background background() {
		return new Background(palette);
	}

	public static class Background {

		private final Palette palette;

		public Background(Palette palette) {
			this.palette = requireNonNull(palette);
		}

		public Color base() {
			return palette.sepia().brighter(3);
		}

		public Color selectedLine() {
			return base().darker(3);
		}

		public Color underCaret() {
			return selectedLine().darker();
		}

		public Color underCaretWrite() {
			return underCaret().darker();
		}

		public Color selectedText() {
			return palette.blue().brighter();
		}
	}
}
