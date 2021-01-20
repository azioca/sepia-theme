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
	private final Color foreground = palette.black().darker(2);

	@JsonProperty public String id() { return "com.github.adamwojszczyk." + toCamelCase() + "Theme"; }
	@JsonProperty public String name() { return name + " Theme"; }
	@JsonProperty public String email() { return "adam.wojszczyk@gmail.com"; }
	@JsonProperty public String vendor() { return "Adam Wojszczyk"; }
	@JsonProperty public String themeProviderId() { return "f20d8fb3-da86-46ac-8621-661b16f0d135"; }

	public Theme theme() {
		return new Theme(palette, background, foreground, name + " Theme", vendor());
	}

	public Scheme scheme() {
		return new Scheme(name, palette, background, foreground);
	}

	private String toCamelCase() {
		final String first = name.substring(0, 1);
		return name.replace(" ", "").replaceAll("(.)(.*)", first.toLowerCase() + "$2");
	}
}
