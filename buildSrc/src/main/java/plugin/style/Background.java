package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Background {
	private final Palette palette;

	public Background(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Editor editor() {
		return new Editor(palette);
	}

	public UI ui() {
		return new UI(editor());
	}

	public Color readOnly() {
		return ui().base().darker();
	}

	public static class Editor {
		private final Palette palette;

		public Editor(Palette palette) {
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

	public static class UI {
		private final Editor editor;

		UI(Editor editor) { this.editor = editor; }

		public Color input() {
			return editor.base();
		}

		public Color base() {
			return input().darker();
		}

		public Color hover() {
			return base().darker();
		}

		public Color selectedInactive() {
			return hover().darker();
		}

		public Color selected() {
			return selectedInactive().darker();
		}
	}
}
