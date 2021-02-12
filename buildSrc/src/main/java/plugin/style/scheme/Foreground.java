package plugin.style.scheme;

import plugin.domain.Palette;
import plugin.domain.color.Color;

import static java.util.Objects.requireNonNull;

public class Foreground {

	private final Palette palette;

	Foreground(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Color base() {
		return palette.black().darker(2);
	}

	public Color disabled() {
		return palette.gray().brighter();
	}

	public Color selectedText() {
		return base();
	}
}
