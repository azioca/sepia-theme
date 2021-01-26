package plugin;

import plugin.domain.Color;

public class Scrollbar {

	private final Color trackColor;

	public Scrollbar(Color trackColor) { this.trackColor = trackColor; }

	public Color trackColor() { return trackColor; }
	public Color thumbColor() { return trackColor().darker(3).opacity(0.4); }
	public Color thumbBorderColor() { return thumbColor(); }
	public Color hoverTrackColor() { return trackColor(); }
	public Color hoverThumbColor() { return thumbColor().opacity(0.65); }
	public Color hoverThumbBorderColor() { return hoverThumbColor(); }
}
