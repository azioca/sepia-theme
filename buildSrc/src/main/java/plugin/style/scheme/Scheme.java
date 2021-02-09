package plugin.style.scheme;

import plugin.domain.Color;
import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Scheme {

	private final Palette palette;

	public Scheme(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Foreground foreground() {
		return new Foreground(palette);
	}

	public Background background() {
		return new Background(palette);
	}

	public Color lines() {
		return palette.gray().brighter(3);
	}
}
