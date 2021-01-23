package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
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
		@JsonUnwrapped(prefix = "Actions.") @JsonProperty Actions Actions() { return new Actions(); }
		@JsonUnwrapped(prefix = "Objects.") @JsonProperty Objects Objects() { return new Objects(); }
		@JsonUnwrapped(prefix = "Checkboxes.") @JsonProperty Checkboxes Checkboxes() { return new Checkboxes(); }

		class Actions {
			@JsonProperty Color Grey = palette.gray();
			@JsonProperty Color Red = palette.red();
			@JsonProperty Color Yellow = palette.yellow();
			@JsonProperty Color Green = palette.green();
			@JsonProperty Color Blue = palette.blue();
			@JsonProperty("GreyInline.Dark") Color GreyInlineDark = palette.gray().darker();
		}

		class Objects {
			@JsonProperty Color Grey = palette.gray();
			@JsonProperty Color RedStatus = palette.red();
			@JsonProperty Color Red = palette.red();
			@JsonProperty Color Pink = palette.purple().darker();
			@JsonProperty Color Yellow = palette.yellow();
			@JsonProperty Color Green = palette.green();
			@JsonProperty Color Blue = palette.blue();
			@JsonProperty Color Purple = palette.purple();
			@JsonProperty Color BlackText = foreground;
			@JsonProperty Color YellowDark = palette.yellow().darker();
		}

		class Checkboxes {
			@JsonUnwrapped(prefix = "Foreground.") @JsonProperty Foreground Foreground = new Foreground();
			@JsonUnwrapped(prefix = "Background.") @JsonProperty Background Background = new Background();
			@JsonUnwrapped(prefix = "Border.") @JsonProperty Border Border = new Border();
			@JsonUnwrapped(prefix = "Focus.") @JsonProperty Focus Focus = new Focus();

			class Foreground {
				@JsonProperty("Selected.Dark") Color Selected = foreground;
				@JsonProperty("Disabled.Dark") Color Disabled = palette.gray().darker();
			}

			class Background {
				@JsonProperty("Default.Dark") Color Defaultt = palette.sepia().brighter(2);
				@JsonProperty("Disabled.Dark") Color Disabled = palette.sepia().brighter(2);
			}

			class Border {
				@JsonProperty("Default.Dark") Color Defaultt = foreground;
				@JsonProperty("Disabled.Dark") Color Disabled = (palette.gray().darker());
			}

			class Focus {
				@JsonProperty("Wide.Dark") Color Wide = palette.blue();
				@JsonUnwrapped(prefix = "Thin.") @JsonProperty Checkboxes.Focus.Thin Thin = new Checkboxes.Focus.Thin();

				class Thin {
					@JsonProperty("Default.Dark") Color Defaultt = palette.blue();
					@JsonProperty("Selected.Dark") Color Selected = palette.gray().darker();
				}
			}
		}
	}
}
