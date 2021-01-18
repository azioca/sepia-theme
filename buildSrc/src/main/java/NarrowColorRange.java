

class NarrowColorRange {

	private final Color dark;
	private final Color medium;
	private final Color light;

	NarrowColorRange(String dark, String medium, String light) {
		this(new Color(dark), new Color(medium), new Color(light));
	}

	NarrowColorRange(Color dark, Color medium, Color light) {
		this.dark = dark;
		this.medium = medium;
		this.light = light;
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
}
