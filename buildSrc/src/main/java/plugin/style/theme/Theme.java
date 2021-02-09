package plugin.style.theme;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Theme {

	private final Palette palette;

	public Theme(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Foreground foreground() {
		return new Foreground(palette);
	}

	public Background background() {
		return new Background(palette, palette.sepia().brightest());
	}

	public Button button() {
		return new Button(this);
	}

	public Checkbox checkbox() {
		return new Checkbox(this);
	}

	public Tab tab() {
		return new Tab(this);
	}

	public Table table() {
		return new Table(this);
	}

	public Color borderColor() {
		return background().base().darker(3);
	}

	Color focus() {
		return palette.blue().brighter();
	}
}
