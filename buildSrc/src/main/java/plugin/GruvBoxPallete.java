package plugin;

import plugin.domain.Color;
import plugin.domain.Palette;

public class GruvBoxPallete implements Palette {

	private final Color sepia = new Color(3, "a89984", "bdae93", "d5c4a1", "ebdbb2", "f2e5bc", "fbf1c7", "f9f5d7");
	private final Color black = new Color(3, "1d2021", "282828", "32302f", "3c3836", "504945", "665c54", "7c6f64");
	private final Color gray = new Color(1, "7c6f64", "928374", "a89984");
	private final Color red = new Color(1, "9d0006", "cc241d", "fb4934");
	private final Color green = new Color(1, "79740e", "98971a", "b8bb26");
	private final Color yellow = new Color(1, "b57614", "d79921", "fabd2f");
	private final Color blue = new Color(1, "076678", "458588", "83a598");
	private final Color purple = new Color(1, "8f3f71", "b16286", "d3869b");
	private final Color aqua = new Color(1, "427b58", "689d6a", "8ec07c");
	private final Color orange = new Color(1, "af3a03", "d65d0e", "fe8019");

	@Override
	public Color sepia() {
		return sepia;
	}

	@Override
	public Color black() {
		return black;
	}

	@Override
	public Color gray() {
		return gray;
	}

	@Override
	public Color red() {
		return red;
	}

	@Override
	public Color green() {
		return green;
	}

	@Override
	public Color yellow() {
		return yellow;
	}

	@Override
	public Color blue() {
		return blue;
	}

	@Override
	public Color purple() {
		return purple;
	}

	@Override
	public Color aqua() {
		return aqua;
	}

	@Override
	public Color orange() {
		return orange;
	}
}
