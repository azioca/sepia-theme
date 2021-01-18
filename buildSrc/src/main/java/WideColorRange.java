class WideColorRange {

	private final Color darkest;
	private final Color darker;
	private final Color dark;
	private final Color medium;
	private final Color light;
	private final Color lighter;
	private final Color lightest;

	WideColorRange(String darkest, String darker, String dark, String medium, String light, String lighter, String lightest) {
		this(new Color(darkest), new Color(darker), new Color(dark), new Color(medium), new Color(light), new Color(lighter), new Color(lightest));
	}

	WideColorRange(Color darkest, Color darker, Color dark, Color medium, Color light, Color lighter, Color lightest) {
		this.darkest = darkest;
		this.darker = darker;
		this.dark = dark;
		this.medium = medium;
		this.light = light;
		this.lighter = lighter;
		this.lightest = lightest;
	}

	Color darkest() {
		return darkest;
	}

	Color darker() {
		return darker;
	}

	Color dark() {
		return dark;
	}

	Color medium() {
		return medium;
	}

	Color light() {
		return light;
	}

	Color lighter() {
		return lighter;
	}

	Color lightest() {
		return lightest;
	}
}
