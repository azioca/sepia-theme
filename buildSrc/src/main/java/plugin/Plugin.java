package plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.scheme.Scheme;
import plugin.theme.Theme;

public class Plugin {

	private final String name = "Eye-Friendly Sepia";
	private final Palette palette = new GruvBoxPallete();
	private final Color editorBackground = palette.sepia().brighter(3);
	private final Color uiBackground = editorBackground.darker();
	private final Color readOnlyBackground = palette.yellow().brighter().transparent("10");
	private final Color foreground = palette.black().darker(2);

	@JsonProperty String id = "com.github.adamwojszczyk." + toCamelCase(name) + "Theme";
	@JsonProperty String pluginName = name + " Theme";
	@JsonProperty String email = "adam.wojszczyk@gmail.com";
	@JsonProperty String vendor = "Adam Wojszczyk";
	@JsonProperty String themeProviderId = "be9ee20a-ea64-4eac-b420-29191a79191d";

	public Theme theme() {
		return new Theme(name + " Theme", vendor, palette, foreground, uiBackground, editorBackground, readOnlyBackground);
	}

	public Scheme scheme() {
		return new Scheme(name, palette, editorBackground, foreground);
	}

	private static String toCamelCase(String name) {
		final String first = name.substring(0, 1);
		return name.replace(" ", "").replace("-", "").replaceAll("(.)(.*)", first.toLowerCase() + "$2");
	}
}
