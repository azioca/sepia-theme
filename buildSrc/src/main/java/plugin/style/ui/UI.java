package plugin.style.ui;

import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class UI {

	private final Palette palette;

	public UI(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Background background() {
		return new Background(palette.sepia().brightest());
	}
}
