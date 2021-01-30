package plugin.intellij.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import plugin.domain.Palette;
import plugin.style.Style;

import static java.util.Objects.requireNonNull;

@JsonPropertyOrder(value = {"name", "author", "dark", "editorScheme"})
public class Theme {
	private final String name;
	private final String author;
	private final Palette palette;

	private final Style style;

	public Theme(String name, String author, Palette palette, Style style) {
		this.name = requireNonNull(name);
		this.author = requireNonNull(author);
		this.palette = new Palette.Hex(requireNonNull(palette));
		this.style = requireNonNull(style);
	}

	@JsonProperty String name() { return name; }
	@JsonProperty String author() { return author; }
	@JsonProperty boolean dark() { return false; }
	@JsonProperty String editorScheme() { return "/intellij.scheme.xml"; }
	@JsonProperty UI ui() { return new UI(palette, style); }
	@JsonProperty Icons icons() { return new Icons(palette, style); }
}
