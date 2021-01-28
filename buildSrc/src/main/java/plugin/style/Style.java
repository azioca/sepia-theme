package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Style {

	private final Palette palette;

	public Style(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Color foreground() {
		return palette.black().darker(2);
	}

	public Background background() {
		return new Background(palette, palette.sepia().brighter(3));
	}

	public Color borderColor() {
		return background().ui().base().darker(3);
	}

	public Color lines() {
		return palette.gray().brighter(2);
	}

	public Color error() {
		return palette.red();
	}

	public Color warning() {
		return palette.yellow().brighter();
	}

	public Checkbox checkbox() {
		return new Checkbox(this);
	}

	public Scrollbar scrollbar() {
		return new Scrollbar(background().ui().base());
	}

	public Color link() {
		return palette.blue();
	}

	public Tab tab() {
		return new Tab(background());
	}

	public static class Tab {

		private final Background background;

		public Tab(Background background) {
			this.background = Objects.requireNonNull(background);
		}

		public Color selected() {
			return background.ui().base();
		}

		public Color hover() {
			return selected();
		}

		public Color inBackground() {
			return selected().darker(3);
		}

		public Color inBackgroundInactive() {
			return inBackground().brighter();
		}

		public Color borderColor() {
			return selected();
		}

		public Color underline() {
			return background.editor().selectedText();
		}

		public Integer underlineHeight() {
			return 2;
		}
	}
}
