package plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.scheme.Scheme;
import plugin.theme.Theme;

public class Plugin {

	private final Palette palette = new GruvBoxPallete();
	private final Color background = palette.sepia().brighter().brighter().brighter();
	private final Color foreground = palette.black().darker().darker();
	@JsonProperty public String id() { return "com.github.adamwojszczyk.gruvboxLightHardBlackTheme"; }
	@JsonProperty public String name() { return "Gruvbox Light Hard Black Theme"; }
	@JsonProperty public String email() { return "adam.wojszczyk@gmail.com"; }
	@JsonProperty public String vendor() { return "Adam Wojszczyk"; }
	@JsonProperty public String themeProviderId() { return "f20d8fb3-da86-46ac-8621-661b16f0d135"; }
	public Theme theme() {
		return new Theme(palette, background, foreground, name(), vendor());
	}

	public Scheme scheme() {
		return new Scheme(this, palette);
	}
}
