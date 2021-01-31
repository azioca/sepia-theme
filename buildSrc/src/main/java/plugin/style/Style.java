package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Style {

	private final Palette palette;

	public Style(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Color foreground() {
		return palette.black().darker(2);
	}

	public Color infoForeground() {
		return palette.gray();
	}

	public Color searchBackground() {
		return palette.aqua().brighter();
	}

	public Color disabledForeground() {
		return palette.gray().brighter();
	}

	public Color modifiedForeground() {
		return palette.blue();
	}

	public Background background() {
		return new Background(palette, palette.sepia().brighter(3));
	}

	public Color borderColor() {
		return background().ui().base().darker(3);
	}

	public Color editorLines() {
		return palette.gray().brighter(3);
	}

	public Color info() {
		return palette.green().brighter();
	}

	public Color warning() {
		return palette.yellow().brighter();
	}

	public Color error() {
		return palette.red();
	}

	public Button button() {
		return new Button(this);
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

	public Table table() {
		return new Table(this);
	}
}
