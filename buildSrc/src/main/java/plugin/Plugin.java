package plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.scheme.Scheme;
import plugin.theme.Theme;

public class Plugin {

	private final String name = "Gruvbox Light Hard Black";
	private final Palette palette = new GruvBoxPallete();
	private final Color background = palette.sepia().brighter(3);
	private final Color schemeBackground = background.darker();
	private final Color foreground = palette.black().darker(2);

	@JsonProperty String id = "com.github.adamwojszczyk." + toCamelCase(name) + "Theme";
	@JsonProperty String pluginName = name + " Theme";
	@JsonProperty String email = "adam.wojszczyk@gmail.com";
	@JsonProperty String vendor = "Adam Wojszczyk";
	@JsonProperty String themeProviderId = "f20d8fb3-da86-46ac-8621-661b16f0d135";

	public Theme theme() {
		return new Theme(name + " Theme", vendor, palette, schemeBackground, background, foreground);
	}

	public Scheme scheme() {
		return new Scheme(name, palette, background, foreground);
	}

	private static String toCamelCase(String name) {
		final String first = name.substring(0, 1);
		return name.replace(" ", "").replaceAll("(.)(.*)", first.toLowerCase() + "$2");
	}
}
