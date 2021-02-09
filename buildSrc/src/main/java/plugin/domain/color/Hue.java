package plugin.domain.color;

import plugin.lang.AsString;

import java.util.List;

class Hue {
	private final int chosen;
	private final List<Value> values;

	Hue(int chosen, List<Value> values) {
		this.chosen = chosen;
		this.values = List.copyOf(values);
	}

	Value chosen() {
		return values.get(chosen);
	}

	Hue chooseDarker() {
		int chosen = this.chosen - 1;
		if (chosen < 0) throw new IndexOutOfBoundsException("No such dark value defined: " + chosen + ", hue: " + this);
		return new Hue(chosen, values);
	}

	public Hue chooseDarkest() {
		return new Hue(0, values);
	}

	Hue chooseBrighter() {
		if (chosen >= values.size()) throw new IndexOutOfBoundsException("No such bright value defined: " + chosen + ", hue: " + this);
		return new Hue(chosen + 1, values);
	}

	public Hue chooseBrightest() {
		return new Hue(values.size() - 1, values);
	}

	@Override
	public String toString() {
		return new AsString(this).string();
	}
}
