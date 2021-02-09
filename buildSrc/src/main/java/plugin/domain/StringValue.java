package plugin.domain;

import plugin.lang.AsString;

import java.util.Objects;

public abstract class StringValue {

	private final String value;

	protected StringValue(String value) {
		this.value = Objects.requireNonNull(value);
	}

	public String string() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof StringValue)) return false;
		StringValue that = (StringValue) o;
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
