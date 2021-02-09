package plugin.domain.color;

import plugin.domain.StringValue;

class Value extends StringValue {

	protected Value(String value) {
		super(value);
	}

	public String hex() {
		return "#" + string();
	}

	public String plain() {
		return string();
	}
}
