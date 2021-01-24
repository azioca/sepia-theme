package plugin.domain;

import plugin.AsString;

import java.util.Objects;

abstract class IntegerValue {

	private final int value;

	protected IntegerValue(int value) {
		this.value = value;
	}

	public String string() {
		return Integer.toString(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof IntegerValue)) return false;
		IntegerValue that = (IntegerValue) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return new AsString(this).string();
	}
}
