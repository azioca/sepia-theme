package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Background {
	private final Palette palette;
	private final Color editor;

	public Background(Palette palette, Color editorBackground) {
		this.palette = requireNonNull(palette);
		this.editor = requireNonNull(editorBackground);
	}

	public Editor editor() {
		return new Editor(palette, editor);
	}

	public UI ui() {
		return new UI(editor());
	}

	public Color readOnly() {
		return ui().base().darker();
	}

	public static class Editor {
		private final Palette palette;
		private final Color base;

		public Editor(Palette palette, Color base) {
			this.palette = requireNonNull(palette);
			this.base = requireNonNull(base);
		}

		public Color base() {
			return base;
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

		public Color base() {
			return editor.base().darker();
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
