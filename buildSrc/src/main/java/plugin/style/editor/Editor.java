package plugin.style.editor;

import plugin.domain.Palette;

import static java.util.Objects.requireNonNull;

public class Editor {

	private final Palette palette;

	public Editor(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Background background() {
		return new Background(palette);
	}
}
