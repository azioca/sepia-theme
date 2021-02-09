package plugin;

import plugin.domain.color.Color;
import plugin.domain.Palette;

public class GruvBoxPalette implements Palette {

	public Color red() { return new Color("9d0006", "cc241d", "fb4934"); }
	public Color orange() { return new Color("af3a03", "d65d0e", "fe8019"); }
	public Color yellow() { return new Color("b57614", "d79921", "fabd2f"); }
	public Color green() { return new Color("79740e", "98971a", "b8bb26"); }
	public Color aqua() { return new Color("427b58", "689d6a", "8ec07c"); }
	public Color blue() { return new Color("076678", "458588", "83a598"); }
	public Color purple() { return new Color("8f3f71", "b16286", "d3869b"); }

	public Color black() {
		return new Color(
			"1d2021", "282828", "32302f",
			"3c3836",
			"504945", "665c54", "7c6f64"
		);
	}
	public Color gray() {
		return new Color(
			"504945", "665c54", "7c6f64",
			"928374",
			"a89984", "bdae93", "d5c4a1"
		);
	}
	public Color sepia() {
		return new Color(
			"a89984", "bdae93", "d5c4a1",
			"ebdbb2",
			"f2e5bc", "fbf1c7", "f9f5d7"
		);
	}
}
