package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

@JsonPropertyOrder({"name", "author", "dark", "editorScheme", "ui", "icons"})
public class Theme {
	private final Palette palette;
	private final Color background;
	private final Color foreground;

	private final String name;
	private final String author;

	public Theme(Palette palette, Color background, Color foreground, String name, String author) {
		this.palette = new Palette.Hex(Objects.requireNonNull(palette));
		this.background = Objects.requireNonNull(background.hex());
		this.foreground = Objects.requireNonNull(foreground.hex());
		this.name = Objects.requireNonNull(name);
		this.author = Objects.requireNonNull(author);
	}

	@JsonProperty String name() { return name; }
	@JsonProperty String author() { return author; }
	@JsonProperty boolean dark() { return false; }
	@JsonProperty String editorScheme() { return "/intellij.scheme.xml"; }
	@JsonProperty UI ui() { return new UI(palette, background, foreground); }
	@JsonProperty Icons icons() { return new Icons(palette, foreground); }
}
