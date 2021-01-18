package theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Plugin {

	final Color background = Palette.sepia.lightest();
	final Color foreground = Palette.black.darker();
	public Theme theme = new Theme();
	public Scheme scheme = new Scheme();
	String id = "com.github.adamwojszczyk.gruvboxLightHardBlackTheme";
	String name = "Gruvbox Light Hard Black Theme";
	String email = "adam.wojszczyk@gmail.com";
	String vendor = "Adam Wojszczyk";
	String themeProviderId = "f20d8fb3-da86-46ac-8621-661b16f0d135";

	class Theme {
		String name = Plugin.this.name;
		String author = Plugin.this.vendor;
		boolean dark = false;
		String editorScheme = "/intellij.scheme.xml";

		UI ui = new UI();
		Icons icons = new Icons();

		@JsonPropertyOrder("*")
		class UI {
			@JsonProperty("*")
			Asterisk asterisk = new Asterisk();
			UI.ActionButton ActionButton = new ActionButton();
			UI.Button Button = new Button();
			UI.ComboBox ComboBox = new ComboBox();
			UI.EditorTabs EditorTabs = new EditorTabs();
			UI.ToolWindow ToolWindow = new ToolWindow();
			UI.Table Table = new Table();
			UI.FileColor FileColor = new FileColor();
			UI.Link Link = new Link();

			class Asterisk {
				Color background = Plugin.this.background;
				Color foreground = Plugin.this.foreground;
				Color infoForeground = Plugin.this.foreground;
				Color lightSelectionBackground = Palette.sepia.light();
				Color selectionBackground = Palette.sepia.light();
				Color selectionForeground = Plugin.this.foreground;
				Color selectionBackgroundInactive = Palette.sepia.light();
				Color selectionInactiveBackground = Palette.sepia.light();
				Color selectedInactiveBackground = Palette.sepia.light();
				Color selectedBackgroundInactive = Palette.sepia.light();
				Color selectedBackground = Plugin.this.background;
				Color selectedForeground = Plugin.this.foreground;
				Color hoverBackground = Palette.sepia.lighter();
				Color borderColor = Palette.sepia.dark();
				Color disabledBorderColor = Plugin.this.background;
				Color separatorColor = Palette.sepia.dark();
			}

			class ActionButton {
				Color hoverBackground = Palette.sepia.dark();
			}

			class Button {
				Color startBackground = Plugin.this.background;
				Color endBackground = Plugin.this.background;
				Color startBorderColor = Palette.sepia.dark();
				Color endBorderColor = Palette.sepia.dark();

				@JsonProperty("default")
				Default defaultt = new Default();

				class Default {
					Color foreground = Plugin.this.foreground;
					Color startBackground = Palette.sepia.light();
					Color endBackground = Palette.sepia.light();
					Color startBorderColor = Palette.black.lighter();
					Color endBorderColor = Palette.black.lighter();
					Color focusedBorderColor = Plugin.this.background;
				}
			}

			class ComboBox {
				Color nonEditableBackground = Plugin.this.background;
				UI.ComboBox.ArrowButton ArrowButton = new ArrowButton();

				class ArrowButton {
					Color iconColor = Plugin.this.foreground;
					Color disabledIconColor = Plugin.this.foreground;
					Color nonEditableBackground = Plugin.this.background;
				}
			}

			class EditorTabs {
				Color underlinedTabBackground = Plugin.this.background;
				Color underlineColor = Palette.blue.dark();
			}

			class ToolWindow {
				UI.ToolWindow.Header Header = new Header();
				UI.ToolWindow.HeaderTab HeaderTab = new HeaderTab();

				class Header {
					Color background = Palette.sepia.light();
					Color inactiveBackground = Plugin.this.background;
				}

				class HeaderTab {
					Color selectedInactiveBackground = Plugin.this.background;
					Color hoverInactiveBackground = Plugin.this.background;
				}
			}

			class Table {
				Color stripeColor = Palette.sepia.light();
				Color lightSelectionForeground = Plugin.this.foreground;
				Color lightSelectionInactiveForeground = Palette.black.lightest();
				Color lightSelectionBackground = Palette.sepia.light();
				Color lightSelectionInactiveBackground = Plugin.this.background;
			}

			class FileColor {
				Color Yellow = Palette.yellow.light().transparent("15");
				Color Green = Palette.green.light().transparent("30");
				Color Blue = Palette.blue.light();
				Color Violet = Palette.purple.light();
				Color Orange = Palette.orange.light();
				Color Rose = Palette.red.light();
			}

			class Link {
				Color activeForeground = Palette.blue.dark();
				Color hoverForeground = Palette.blue.dark();
				Color pressedForeground = Palette.blue.dark();
				Color visitedForeground = Palette.blue.dark();
			}
		}

		class Icons {
			Icons.ColorPalette ColorPalette = new ColorPalette();

			class ColorPalette {
				Icons.ColorPalette.Actions Actions = new Actions();
				Icons.ColorPalette.Objects Objects = new Objects();
				Icons.ColorPalette.Checkboxes Checkboxes = new Checkboxes();

				class Dark {
					Color Dark;

					Dark(Color dark) {
						Dark = dark;
					}
				}

				class Actions {
					Color Grey = Palette.gray.medium();
					Color Red = Palette.red.dark();
					Color Yellow = Palette.yellow.dark();
					Color Green = Palette.green.medium();
					Color Blue = Palette.blue.medium();
					Dark GreyInline = new Dark(Plugin.this.foreground);
				}

				class Objects {
					Color Grey = Palette.gray.medium();
					Color RedStatus = Palette.red.dark();
					Color Red = Palette.red.dark();
					Color Pink = Palette.purple.dark();
					Color Yellow = Palette.yellow.medium();
					Color Green = Palette.green.medium();
					Color Blue = Palette.blue.medium();
					Color Purple = Palette.purple.medium();
					Color BlackText = Plugin.this.foreground;
					Color YellowDark = Palette.yellow.medium();
					Color GreenAndroid = Palette.green.dark();
				}

				class Checkboxes {
					Icons.ColorPalette.Checkboxes.Foreground Foreground = new Foreground();
					Icons.ColorPalette.Checkboxes.Background Background = new Background();
					Icons.ColorPalette.Checkboxes.Border Border = new Border();
					Icons.ColorPalette.Checkboxes.Focus Focus = new Focus();

					class Foreground {
						Dark Selected = new Dark(Plugin.this.foreground);
						Dark Disabled = new Dark(Palette.gray.dark());
					}

					class Background {
						@JsonProperty("Default")
						Dark Defaultt = new Dark(Palette.sepia.lighter());
						Dark Disabled = new Dark(Palette.sepia.lighter());
					}

					class Border {
						@JsonProperty("Default")
						Dark Defaultt = new Dark(Plugin.this.foreground);
						Dark disabled = new Dark(Palette.gray.dark());
					}

					class Focus {
						Dark Wide = new Dark(Palette.blue.medium());
						Icons.ColorPalette.Checkboxes.Focus.Thin Thin = new Thin();

						class Thin {
							@JsonProperty("Default")
							Dark Defaultt = new Dark(Palette.blue.medium());
							Dark Selected = new Dark(Palette.gray.dark());
						}
					}
				}
			}
		}
	}

	class Scheme {
	}
}
