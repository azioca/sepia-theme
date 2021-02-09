package plugin.style.theme;

import plugin.domain.color.Color;
import plugin.domain.Palette;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Background {

	private final Palette palette;
	private final Color input;

	public Background(Palette palette, Color input) {
		this.palette = Objects.requireNonNull(palette);
		this.input = requireNonNull(input);
	}

	public Color input() {
		return input;
	}

	public Color base() {
		return input().darker();
	}

	public Color readOnly() {
		return base().darker();
	}

	public Color hover() {
		return base().darker();
	}

	public Color selectedInactive() {
		return hover().darker();
	}

	public Color selected() {
		return selectedInactive().darker();
	}

	public Color search() {
		return palette.aqua().brighter();
	}
}
