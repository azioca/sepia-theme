package plugin.style.theme;

import plugin.domain.Color;

import static java.util.Objects.requireNonNull;

public class Background {

	private final Color input;

	public Background(Color input) {
		this.input = requireNonNull(input);
	}

	public Color input() {
		return input;
	}

	public Color base() {
		return input().darker();
	}

	public Color readOnly() {
		return base().darker();
	}

	public Color hover() {
		return base().darker();
	}

	public Color selectedInactive() {
		return hover().darker();
	}

	public Color selected() {
		return selectedInactive().darker();
	}
}
