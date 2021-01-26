package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import plugin.Scrollbar;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@JsonPropertyOrder(value = {"name", "author", "dark", "editorScheme"})
public class Theme {
	private final String name;
	private final String author;
	private final Palette palette;
	private final Color foreground;
	private final Color background;
	private final Color schemeBackground;
	private final Color readOnlyBackground;
	private final Scrollbar scrollbar;

	public Theme(String name, String author, Palette palette, Color foreground, Color uiBackground, Color schemeBackground, Color readOnlyBackground, Scrollbar scrollbar) {
		this.name = requireNonNull(name);
		this.author = requireNonNull(author);
		this.palette = new Palette.Hex(requireNonNull(palette));
		this.foreground = requireNonNull(foreground.hex());
		this.background = requireNonNull(uiBackground.hex());
		this.schemeBackground = requireNonNull(schemeBackground.hex());
		this.readOnlyBackground = requireNonNull(readOnlyBackground);
		this.scrollbar = Objects.requireNonNull(scrollbar);
	}

	@JsonProperty String name() { return name; }
	@JsonProperty String author() { return author; }
	@JsonProperty boolean dark() { return false; }
	@JsonProperty String editorScheme() { return "/intellij.scheme.xml"; }
	@JsonProperty UI ui() { return new UI(palette, foreground, background, schemeBackground, readOnlyBackground, scrollbar); }
	@JsonProperty Icons icons() { return new Icons(palette, background, foreground); }
}
