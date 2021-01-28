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

	public Background background() {
		return new Background(palette, palette.sepia().brighter(3));
	}

	public Color borderColor() {
		return background().ui().base().darker(3);
	}

	public Color error() {
		return palette.red();
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
}
