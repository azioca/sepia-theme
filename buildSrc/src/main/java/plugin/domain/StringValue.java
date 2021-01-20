package plugin.domain;

import java.util.Objects;
import java.util.StringJoiner;

abstract class StringValue {

	private final String id;

	protected StringValue(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public String asString() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof StringValue)) return false;
		StringValue that = (StringValue) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", StringValue.class.getSimpleName() + "[", "]")
			.add("id='" + id + "'")
			.toString();
	}
}
