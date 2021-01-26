package plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Palette;
import plugin.intellij.scheme.Scheme;
import plugin.intellij.theme.Theme;
import plugin.style.Style;

public class Plugin {

	private final String name;
	private final Palette palette;

	public Plugin() {
		this.name = "Sepia";
		this.palette = new GruvBoxPalette();
	}

	@JsonProperty String id() { return "com.github.adamwojszczyk." + camelCaseName() + "Theme"; }
	@JsonProperty String name() { return name + " Theme"; }
	@JsonProperty String email() { return "adam.wojszczyk@gmail.com"; }
	@JsonProperty String vendor() { return "Adam Wojszczyk"; }
	@JsonProperty String themeProviderId() { return "be9ee20a-ea64-4eac-b420-29191a79191d"; }

	public Theme theme() {
		return new Theme(name, vendor(), palette, new Style(new Palette.Hex(palette)));
	}

	public Scheme scheme() {
		return new Scheme(name, palette, new Style(new Palette.Plain(palette)));
	}

	private String camelCaseName() {
		final String first = name.substring(0, 1);
		return name.replace(" ", "").replace("-", "").replaceAll("(.)(.*)", first.toLowerCase() + "$2");
	}
}
