package plugin;

import plugin.domain.color.Color;
import plugin.domain.Palette;

public class GruvBoxPalette implements Palette {

	public Color red() { return new Color("6a0004", "840005", "9d0006", "cc241d", "fb4934", "fc8c7f", "fecfca"); }
	public Color orange() { return new Color("7d2902", "963203", "af3a03", "d65d0e", "fe8019", "feaa65", "fed4b1"); }
	public Color yellow() { return new Color("87580f", "9e6711", "b57614", "d79921", "fabd2f", "fcd57a", "feedc5"); }
	public Color green() { return new Color("4b4809", "625e0b", "79740e", "98971a", "b8bb26", "d9dc52", "e7e992"); }
	public Color aqua() { return new Color("305a40", "396a4c", "427b58", "689d6a", "8ec07c", "bbd9b0", "e7f2e4"); }
	public Color blue() { return new Color("043d48", "065260", "076678", "458588", "83a598", "afc5bd", "dbe5e2"); }
	public Color purple() { return new Color("6c2f55", "7d3763", "8f3f71", "b16286", "d3869b", "e7bec9", "fcf6f7"); }
	public Color silver() { return new Color("9a9a9a", "a7a7a7", "b3b3b3", "c0c0c0", "cdcdcd", "dadada", "e6e6e6"); }

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
