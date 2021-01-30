package plugin.style;

import plugin.domain.Color;

import java.util.Objects;

public class Tab {

	private final Background background;

	public Tab(Background background) {
		this.background = Objects.requireNonNull(background);
	}

	public Color selected() {
		return background.ui().base();
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
		return background.editor().selectedText();
	}

	public Integer editorUnderlineHeight() {
		return 2;
	}

	public Integer settingsUnderlineHeight() {
		return 3;
	}
}
