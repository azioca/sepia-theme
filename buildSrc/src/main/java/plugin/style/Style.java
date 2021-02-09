package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.style.scheme.Scheme;
import plugin.style.theme.Theme;

import static java.util.Objects.requireNonNull;

public class Style {

	private final Palette palette;

	public Style(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Theme theme() {
		return new Theme(palette);
	}

	public Scheme scheme() {
		return new Scheme(palette);
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

	public Color link() {
		return palette.blue();
	}
}
