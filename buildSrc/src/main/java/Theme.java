class Theme {
	Color background = Palette.sepia.lightest();
	Color foreground = Palette.black.darker();

	UI ui = new UI();
	Scheme scheme = new Scheme();

	class UI {
		Color background = Theme.this.background;
		Color foreground = Theme.this.foreground;
		Color infoForeground = foreground;
		Color lightSelectionBackground = Palette.sepia.light();
		Color selectionBackground = Palette.sepia.light();
		Color selectionForeground = foreground;
		Color selectionBackgroundInactive = Palette.sepia.light();
		Color selectionInactiveBackground = Palette.sepia.light();
		Color selectedInactiveBackground = Palette.sepia.light();
		Color selectedBackgroundInactive = Palette.sepia.light();
		Color selectedBackground = background;
		Color selectedForeground = foreground;
		Color hoverBackground = Palette.sepia.lighter();
		Color borderColor = Palette.sepia.dark();
		Color disabledBorderColor = background;
		Color separatorColor = Palette.sepia.dark();

		ActionButton actionButton = new ActionButton();
		Button button = new Button();
		ComboBox comboBox = new ComboBox();
		EditorTabs editorTabs = new EditorTabs();
		ToolWindow toolWindow = new ToolWindow();
		Table table = new Table();
		FileColor fileColor = new FileColor();
		Link link = new Link();
		Icons icons = new Icons();

		class ActionButton {
			Color hoverBackground = Palette.sepia.dark();
		}

		class Button {
			Color startBackground = background;
			Color endBackground = background;
			Color startBorderColor = Palette.sepia.dark();
			Color endBorderColor = Palette.sepia.dark();

			Default defaultt = new Default();

			class Default {
				Color foreground = UI.this.foreground;
				Color startBackground = Palette.sepia.light();
				Color endBackground = Palette.sepia.light();
				Color startBorderColor = Palette.black.lighter();
				Color endBorderColor = Palette.black.lighter();
				Color focusedBorderColor = background;
			}
		}

		class ComboBox {
			Color nonEditableBackground = background;
			ArrowButton arrowButton = new ArrowButton();

			class ArrowButton {
				Color iconColor = foreground;
				Color disabledIconColor = foreground;
				Color nonEditableBackground = background;
			}
		}

		class EditorTabs {
			Color underlinedTabBackground = background;
			Color underlineColor = Palette.blue.dark();
		}

		class ToolWindow {
			Header header = new Header();
			HeaderTab headerTab = new HeaderTab();

			class Header {
				Color background = Palette.sepia.light();
				Color inactiveBackground = UI.this.background;
			}

			class HeaderTab {
				Color selectedInactiveBackground = UI.this.background;
				Color hoverInactiveBackground = UI.this.background;
			}
		}

		class Table {
			Color stripeColor = Palette.sepia.light();
			Color lightSelectionForeground = UI.this.foreground;
			Color lightSelectionInactiveForeground = Palette.black.lightest();
			Color lightSelectionBackground = Palette.sepia.light();
			Color lightSelectionInactiveBackground = UI.this.background;
		}

		class FileColor {
			Color yellow = Palette.yellow.light().transparent("15");
			Color green = Palette.green.light().transparent("30");
			Color blue = Palette.blue.light();
			Color violet = Palette.purple.light();
			Color orange = Palette.orange.light();
			Color rose = Palette.red.light();
		}

		class Link {
			Color activeForeground = Palette.blue.dark();
			Color hoverForeground = Palette.blue.dark();
			Color pressedForeground = Palette.blue.dark();
			Color visitedForeground = Palette.blue.dark();
		}

		class Icons {
			ColorPalette colorPalette = new ColorPalette();

			class ColorPalette {
				Actions actions = new Actions();
				Objects objects = new Objects();
				Checkboxes checkboxes = new Checkboxes();

				class Actions {
					Color grey = Palette.gray.medium();
					Color red = Palette.red.dark();
					Color yellow = Palette.yellow.dark();
					Color green = Palette.green.medium();
					Color blue = Palette.blue.medium();
					GreyInline greyInline = new GreyInline();

					class GreyInline {
						Color dark = UI.this.foreground;
					}
				}

				class Objects {
					Color grey = Palette.gray.medium();
					Color redStatus = Palette.red.dark();
					Color red = Palette.red.dark();
					Color pink = Palette.purple.dark();
					Color yellow = Palette.yellow.medium();
					Color green = Palette.green.medium();
					Color blue = Palette.blue.medium();
					Color purple = Palette.purple.medium();
					Color blackText = UI.this.foreground;
					Color yellowDark = Palette.yellow.medium();
					Color greenAndroid = Palette.green.dark();
				}

				class Checkboxes {
					Foreground foreground = new Foreground();
					Background background = new Background();
					Border border = new Border();
					Focus focus = new Focus();

					class Foreground {
						Color selected = UI.this.foreground;
						Color disabled = Palette.gray.dark();
					}

					class Background {
						Color defaultt = Palette.sepia.lighter();
						Color disabled = Palette.sepia.lighter();
					}

					class Border {
						Color defaultt = UI.this.foreground;
						Color disabled = Palette.gray.dark();
					}

					class Focus {
						Color wide = Palette.blue.medium();
						Thin thin = new Thin();

						class Thin {
							Color defaultt = Palette.blue.medium();
							Color selected = Palette.gray.dark();
						}
					}
				}
			}
		}
	}

	class Scheme {
		Color background = Theme.this.background;
		Color foreground = Theme.this.foreground;
	}
}
