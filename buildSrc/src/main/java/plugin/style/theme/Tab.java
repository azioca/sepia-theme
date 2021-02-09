package plugin.style.theme;

import plugin.domain.Color;

import static java.util.Objects.requireNonNull;

public class Tab {

	private final Theme theme;

	public Tab(Theme theme) {
		this.theme = requireNonNull(theme);
	}

	public Color selected() {
		return theme.background().base();
	}

	public Color hover() {
		return selected();
	}

	public Color inBackground() {
		return selected().darker(3);
	}

	public Color inBackgroundInactive() {
		return inBackground().brighter();
	}

	public Color borderColor() {
		return selected();
	}

	public Color underline() {
		return theme.focus();
	}

	public Integer editorUnderlineHeight() {
		return 2;
	}

	public Integer settingsUnderlineHeight() {
		return 3;
	}
}
