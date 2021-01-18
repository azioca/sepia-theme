import com.fasterxml.jackson.annotation.JsonValue;

class Color {

	private final String base;
	private final String transparency;
	private final boolean hex;

	Color(String base) {
		this(base, "FF", true);
	}

	private Color(String base, String transparency, boolean hex) {
		this.base = base;
		this.transparency = transparency;
		this.hex = hex;
	}

	Color transparent(String transparency) {
		return new Color(base, transparency, hex);
	}

	Color plain() {
		return new Color(base, transparency, false);
	}

	@JsonValue
	String value() {
		return (hex ? "#" : "") + base + (transparency.equals("FF") ? "" : transparency);
	}
}
