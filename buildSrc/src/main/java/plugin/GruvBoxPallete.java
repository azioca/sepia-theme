package plugin;

import plugin.domain.Color;
import plugin.domain.Palette;

public class GruvBoxPallete implements Palette {

	public Color sepia() { return new Color(3, "a89984", "bdae93", "d5c4a1", "ebdbb2", "f2e5bc", "fbf1c7", "f9f5d7"); }
	public Color black() { return new Color(3, "1d2021", "282828", "32302f", "3c3836", "504945", "665c54", "7c6f64"); }
	public Color gray() { return new Color(1, "7c6f64", "928374", "a89984"); }
	public Color red() { return new Color(1, "9d0006", "cc241d", "fb4934"); }
	public Color green() { return new Color(1, "79740e", "98971a", "b8bb26"); }
	public Color yellow() { return new Color(1, "b57614", "d79921", "fabd2f"); }
	public Color blue() { return new Color(1, "076678", "458588", "83a598"); }
	public Color purple() { return new Color(1, "8f3f71", "b16286", "d3869b"); }
	public Color aqua() { return new Color(1, "427b58", "689d6a", "8ec07c"); }
	public Color orange() { return new Color(1, "af3a03", "d65d0e", "fe8019"); }
}
