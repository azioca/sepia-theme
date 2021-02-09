package plugin.domain.color;

import plugin.lang.AsString;
import plugin.lang.Check;

import java.util.Objects;

class Opacity {
	static final Opacity opaque = new Opacity(1);

	private final double opacity;

	Opacity(double opacity) {
		this.opacity = Check.argument(opacity, o -> o >= 0 && o <= 1, "Should be in range [0, 1]");
	}

	String string() {
		if (opaque.equals(this)) {
			return "";
		} else {
			return String.format("%02X", (int) (opacity * 255)).toLowerCase();
		}
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Opacity)) return false;
		Opacity opacity1 = (Opacity) o;
		return opacity == opacity1.opacity;
	}
	@Override public int hashCode() {
		return Objects.hash(opacity);
	}
	@Override public String toString() {
		return new AsString(this).string();
	}
}
