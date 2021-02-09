package plugin.style.editor;

import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

// todo divide to editor and ui
public class Foreground {

	private final Palette palette;

	Foreground(Palette palette) {
		this.palette = Objects.requireNonNull(palette);
	}

	public Color base() {
		return palette.black().darker(2);
	}

	public Color disabled() {
		return palette.gray().brighter();
	}
}
