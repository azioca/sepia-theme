package plugin.style;

import plugin.domain.Color;

import static java.util.Objects.requireNonNull;

public class Background {
	private final Color editor;

	public Background(Color editorBackground) {
		this.editor = requireNonNull(editorBackground);
	}

	public Editor editor() {
		return new Editor(editor);
	}

	public UI ui() {
		return new UI(editor());
	}

	public Color readOnly() {
		return ui().base().darker();
	}

	public static class Editor {
		private final Color base;

		public Editor(Color base) {
			this.base = base;
		}

		public Color base() {
			return base;
		}

		public Color selectedLine() {
			return base().darker(3);
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
			return hover();
		}

		public Color selected() {
			return selectedInactive().darker();
		}
	}
}
