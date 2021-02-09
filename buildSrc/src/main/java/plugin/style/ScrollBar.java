package plugin.style;

import plugin.domain.Color;
import plugin.style.theme.Theme;

import static java.util.Objects.requireNonNull;

public class ScrollBar {

	private final Theme theme;

	public ScrollBar(Theme theme) {
		this.theme = requireNonNull(theme);
	}

	public Color trackColor() { return theme.background().base().opacity(0); }
	public Color thumbColor() { return trackColor().darker(3).opacity(0.4); }
	public Color thumbBorderColor() { return thumbColor(); }
	public Color hoverTrackColor() { return trackColor(); }
	public Color hoverThumbColor() { return thumbColor().opacity(0.65); }
	public Color hoverThumbBorderColor() { return hoverThumbColor(); }
}
