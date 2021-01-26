package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

class Icons {
	private final Palette palette;
	private final Color background;
	private final Color foreground;

	Icons(Palette palette, Color background, Color foreground) {
		this.palette = Objects.requireNonNull(palette);
		this.background = Objects.requireNonNull(background);
		this.foreground = Objects.requireNonNull(foreground);
	}

	@JsonProperty ColorPalette ColorPalette() { return new ColorPalette(); }

	class ColorPalette {
		@JsonUnwrapped(prefix = "Actions.") @JsonProperty Actions Actions = new Actions();
		@JsonUnwrapped(prefix = "Objects.") @JsonProperty Objects Objects = new Objects();
		@JsonUnwrapped(prefix = "Checkbox.") @JsonProperty Checkbox Checkbox = new Checkbox();

		class Actions {
			@JsonProperty Color Grey = palette.gray();
			@JsonProperty Color Red = palette.red();
			@JsonProperty Color Yellow = palette.yellow();
			@JsonProperty Color Green = palette.aqua().brighter();
			@JsonProperty Color Blue = palette.blue();
			@JsonProperty("GreyInline.Dark") Color GreyInlineDark = palette.gray().darker();
		}

		class Objects {
			@JsonProperty Color Grey = palette.gray();
			@JsonProperty Color RedStatus = palette.red();
			@JsonProperty Color Red = palette.red();
			@JsonProperty Color Pink = palette.purple();
			@JsonProperty Color Yellow = palette.yellow();
			@JsonProperty Color Green = palette.green().brighter();
			@JsonProperty Color Blue = palette.blue().brighter();
			@JsonProperty Color Purple = palette.purple().brighter();
			@JsonProperty Color BlackText = foreground;
			@JsonProperty Color YellowDark = palette.yellow().darker();
		}

		class Checkbox {
			@JsonUnwrapped(prefix = "Foreground.") @JsonProperty Foreground Foreground = new Foreground();
			@JsonUnwrapped(prefix = "Background.") @JsonProperty Background Background = new Background();
			@JsonUnwrapped(prefix = "Border.") @JsonProperty Border Border = new Border();
			@JsonUnwrapped(prefix = "Focus.") @JsonProperty Focus Focus = new Focus();

			class Foreground {
				@JsonProperty("Selected") Color Selected;
				@JsonProperty("Selected.Dark") Color SelectedDark;
				@JsonProperty("Disabled") Color Disabled;
				@JsonProperty("Disabled.Dark") Color DisabledDark;
			}

			class Background {
				@JsonProperty("Default") Color Defaultt;
				@JsonProperty("Default.Dark") Color DefaultDark;
				@JsonProperty("Disabled") Color Disabled;
				@JsonProperty("Disabled.Dark") Color DisabledDark;
				@JsonProperty("Selected") Color Selected;
				@JsonProperty("Selected.Dark") Color SelectedDark;
			}

			class Border {
				@JsonProperty("Default") Color Defaultt;
				@JsonProperty("Default.Dark") Color DefaultDark;
				@JsonProperty("Disabled") Color Disabled;
				@JsonProperty("Disabled.Dark") Color DisabledDark;
			}

			class Focus {
				@JsonProperty("Wide") Color Wide;
				@JsonProperty("Wide.Dark") Color WideDark;
				@JsonUnwrapped(prefix = "Thin.") @JsonProperty Checkbox.Focus.Thin Thin = new Checkbox.Focus.Thin();

				class Thin {
					@JsonProperty("Default") Color Defaultt;
					@JsonProperty("Default.Dark") Color DefaultDark;
					@JsonProperty("Selected") Color Selected;
					@JsonProperty("Selected.Dark") Color SelectedDark;
				}
			}
		}
	}
}
