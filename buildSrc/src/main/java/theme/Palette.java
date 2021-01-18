package theme;

interface Palette {

	WideColorRange sepia = new WideColorRange("a89984", "bdae93", "d5c4a1", "ebdbb2", "f2e5bc", "fbf1c7", "f9f5d7");
	WideColorRange black = new WideColorRange("1d2021", "282828", "32302f", "3c3836", "504945", "665c54", "7c6f64");
	NarrowColorRange gray = new NarrowColorRange("7c6f64", "928374", "a89984");
	NarrowColorRange red = new NarrowColorRange("9d0006", "cc241d", "fb4934");
	NarrowColorRange green = new NarrowColorRange("79740e", "98971a", "b8bb26");
	NarrowColorRange yellow = new NarrowColorRange("b57614", "d79921", "fabd2f");
	NarrowColorRange blue = new NarrowColorRange("076678", "458588", "83a598");
	NarrowColorRange purple = new NarrowColorRange("8f3f71", "b16286", "d3869b");
	NarrowColorRange aqua = new NarrowColorRange("427b58", "689d6a", "8ec07c");
	NarrowColorRange orange = new NarrowColorRange("af3a03", "d65d0e", "fe8019");
}
