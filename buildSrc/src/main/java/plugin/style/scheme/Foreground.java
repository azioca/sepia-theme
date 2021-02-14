package plugin.style.scheme;

import plugin.domain.Palette;
import plugin.domain.color.Color;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Foreground {

	private final Palette palette;
	private final Scheme scheme;

	Foreground(Palette palette, Scheme scheme) {
		this.palette = requireNonNull(palette);
		this.scheme = Objects.requireNonNull(scheme);
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
