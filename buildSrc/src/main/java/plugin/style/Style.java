package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Style {

	private final Palette palette;

	public Style(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Foreground foreground() {
		return new Foreground(palette);
	}

	public UI ui() {
		return new UI(palette);
	}

	public Editor editor() {
		return new Editor(palette);
	}

	public Color searchBackground() {
		return palette.aqua().brighter();
	}

	public Color borderColor() {
		return ui().background().base().darker(3);
	}

	public Color editorLines() {
		return palette.gray().brighter(3);
	}

	public Color success() {
		return palette.aqua().brighter();
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
		return new Scrollbar(ui().background().base());
	}

	public Color link() {
		return palette.blue();
	}

	public Tab tab() {
		return new Tab(this);
	}

	public Table table() {
		return new Table(this);
	}
}
