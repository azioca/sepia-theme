package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

class Icons {
	private final Palette palette;
	private final Color foreground;

	Icons(Palette palette, Color foreground) {
		this.palette = Objects.requireNonNull(palette);
		this.foreground = Objects.requireNonNull(foreground);
	}

	@JsonProperty ColorPalette ColorPalette() { return new ColorPalette(); }

	@JsonPropertyOrder({"Actions", "Objects", "Checkboxes"})
	class ColorPalette {
		@JsonProperty Actions Actions() { return new Actions(); }
		@JsonProperty Objects Objects() { return new Objects(); }
		@JsonProperty Checkboxes Checkboxes() { return new Checkboxes(); }

		class Actions {
			@JsonProperty Color Grey = palette.gray();
			@JsonProperty Color Red = palette.red().darker();
			@JsonProperty Color Yellow = palette.yellow().darker();
			@JsonProperty Color Green = palette.green();
			@JsonProperty Color Blue = palette.blue();
			@JsonProperty Dark GreyInline = new Dark(foreground);
		}

		class Objects {
			@JsonProperty Color Grey = palette.gray();
			@JsonProperty Color RedStatus = palette.red().darker();
			@JsonProperty Color Red = palette.red().darker();
			@JsonProperty Color Pink = palette.purple().darker();
			@JsonProperty Color Yellow = palette.yellow();
			@JsonProperty Color Green = palette.green();
			@JsonProperty Color Blue = palette.blue();
			@JsonProperty Color Purple = palette.purple();
			@JsonProperty Color BlackText = foreground;
			@JsonProperty Color YellowDark = palette.yellow();
			@JsonProperty Color GreenAndroid = palette.green().darker();
		}

		class Checkboxes {
			@JsonProperty Foreground Foreground = new Foreground();
			@JsonProperty Background Background = new Background();
			@JsonProperty Border Border = new Border();
			@JsonProperty Focus Focus = new Focus();

			class Foreground {
				@JsonProperty Dark Selected = new Dark(foreground);
				@JsonProperty Dark Disabled = new Dark(palette.gray().darker());
			}

			class Background {
				@JsonProperty("Default") Dark Defaultt = new Dark(palette.sepia().brighter().brighter());
				@JsonProperty Dark Disabled = new Dark(palette.sepia().brighter().brighter());
			}

			class Border {
				@JsonProperty("Default") Dark Defaultt = new Dark(foreground);
				@JsonProperty Dark disabled = new Dark(palette.gray().darker());
			}

			class Focus {
				@JsonProperty Dark Wide = new Dark(palette.blue());
				@JsonProperty Checkboxes.Focus.Thin Thin = new Checkboxes.Focus.Thin();

				class Thin {
					@JsonProperty("Default") Dark Defaultt = new Dark(palette.blue());
					@JsonProperty Dark Selected = new Dark(palette.gray().darker());
				}
			}
		}
	}
}
