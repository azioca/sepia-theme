package plugin.style.theme;

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
		return new Background(palette.sepia().brightest());
	}
}
