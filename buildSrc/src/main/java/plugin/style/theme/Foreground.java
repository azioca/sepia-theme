package plugin.style.theme;

import plugin.domain.color.Color;
import plugin.domain.Palette;

import java.util.Objects;

public class Foreground {

	private final Palette palette;

	Foreground(Palette palette) {
		this.palette = Objects.requireNonNull(palette);
	}

	public Color base() {
		return palette.black().darker(2);
	}

	public Color info() {
		return palette.gray();
	}

	public Color disabled() {
		return palette.gray().brighter();
	}

	public Color modified() {
		return palette.blue();
	}
}
