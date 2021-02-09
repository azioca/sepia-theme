package plugin.intellij.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import plugin.domain.color.Color;
import plugin.domain.Palette;
import plugin.style.Style;

import static java.util.Objects.requireNonNull;

class Icons {
	private final Palette palette;
	private final Style style;

	Icons(Palette palette, Style style) {
		this.palette = requireNonNull(palette);
		this.style = requireNonNull(style);
	}

	@JsonProperty ColorPalette ColorPalette() { return new ColorPalette(); }

	class ColorPalette {
		@JsonUnwrapped(prefix = "Actions.") @JsonProperty ColorPalette.Actions Actions = new ColorPalette.Actions();
		@JsonUnwrapped(prefix = "Objects.") @JsonProperty ColorPalette.Objects Objects = new ColorPalette.Objects();
		@JsonUnwrapped(prefix = "Checkbox.") @JsonProperty ColorPalette.Checkbox Checkbox = new ColorPalette.Checkbox();
		@JsonProperty("Tree.iconColor") Color treeIcon = style.theme().background().base().darkest();

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
			@JsonProperty Color BlackText = style.theme().foreground().base();
			@JsonProperty Color YellowDark = palette.yellow().darker();
		}

		class Checkbox {
			@JsonUnwrapped(prefix = "Foreground.") @JsonProperty ColorPalette.Checkbox.Foreground Foreground = new ColorPalette.Checkbox.Foreground();
			@JsonUnwrapped(prefix = "Background.") @JsonProperty ColorPalette.Checkbox.Background Background = new ColorPalette.Checkbox.Background();
			@JsonUnwrapped(prefix = "Border.") @JsonProperty ColorPalette.Checkbox.Border Border = new ColorPalette.Checkbox.Border();
			@JsonUnwrapped(prefix = "Focus.") @JsonProperty ColorPalette.Checkbox.Focus Focus = new ColorPalette.Checkbox.Focus();

			class Foreground {
				@JsonProperty("Default") Color Default;
				@JsonProperty("Selected") Color Selected;
				@JsonProperty("Disabled") Color Disabled;
				@JsonProperty("Selected.Dark") Color SelectedDark;
				@JsonProperty("Disabled.Dark") Color DisabledDark;
			}

			class Background {
				@JsonProperty("Default") Color Default = style.theme().checkbox().hook();
				@JsonProperty("Disabled") Color Disabled = style.theme().checkbox().disabledBoxBackground();
				@JsonProperty("Selected") Color Selected = style.theme().checkbox().selectedBoxBackground();
				@JsonProperty("Default.Dark") Color DefaultDark;
				@JsonProperty("Disabled.Dark") Color DisabledDark;
				@JsonProperty("Selected.Dark") Color SelectedDark;
			}

			class Border {
				@JsonProperty("Default") Color Default = style.theme().checkbox().border();
				@JsonProperty("Disabled") Color Disabled = style.theme().checkbox().disabledBorder();
				@JsonProperty("Selected") Color Selected = Default;
				@JsonProperty("Default.Dark") Color DefaultDark;
				@JsonProperty("Disabled.Dark") Color DisabledDark;
			}

			class Focus {
				@JsonProperty("Wide") Color Wide = style.theme().checkbox().focus();
				@JsonProperty("Wide.Dark") Color WideDark;
				@JsonUnwrapped(prefix = "Thin.") @JsonProperty ColorPalette.Checkbox.Focus.Thin Thin = new ColorPalette.Checkbox.Focus.Thin();

				class Thin {
					@JsonProperty("Default") Color Default = style.theme().checkbox().focus();
					@JsonProperty("Selected") Color Selected = style.theme().checkbox().focus();
					@JsonProperty("Default.Dark") Color DefaultDark;
					@JsonProperty("Selected.Dark") Color SelectedDark;
				}
			}
		}
	}
}
