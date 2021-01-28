package plugin.intellij.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.style.Style;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@JsonPropertyOrder(value = {"name", "author", "dark", "editorScheme"})
public class Theme {
	private final String name;
	private final String author;
	private final Palette palette;

	private final Style style;

	public Theme(String name, String author, Palette palette, Style style) {
		this.name = requireNonNull(name);
		this.author = requireNonNull(author);
		this.palette = new Palette.Hex(requireNonNull(palette));
		this.style = requireNonNull(style);
	}

	@JsonProperty String name() { return name; }
	@JsonProperty String author() { return author; }
	@JsonProperty boolean dark() { return false; }
	@JsonProperty String editorScheme() { return "/intellij.scheme.xml"; }
	@JsonProperty UI ui() { return new UI(palette, style); }
	@JsonProperty Icons icons() { return new Icons(palette, style); }

	static class UI {
		private final Palette palette;
		private final Style style;

		UI(Palette palette, Style style) {
			this.palette = requireNonNull(palette);
			this.style = Objects.requireNonNull(style);
		}

		@JsonProperty("*") Asterisk asterisk() { return new Asterisk(); }
		@JsonProperty ActionButton ActionButton() { return new ActionButton(); }
		@JsonProperty Borders Borders() { return new Borders(); }
		@JsonProperty Button Button() { return new Button(); }
		@JsonProperty CheckBox CheckBox() { return new CheckBox(); }
		@JsonProperty ComboBox ComboBox() { return new ComboBox(); }
		@JsonProperty ComboBoxButton ComboBoxButton() { return new ComboBoxButton(); }
		@JsonProperty CompletionPopup CompletionPopup() { return new CompletionPopup(); }
		@JsonProperty Component Component() { return new Component(); }
		@JsonProperty Editor Editor() { return new Editor(); }
		@JsonProperty EditorPane EditorPane() { return new EditorPane(); }
		@JsonProperty EditorTabs EditorTabs() { return new EditorTabs(); }
		@JsonProperty FileColor FileColor() { return new FileColor(); }
		@JsonProperty FormattedTextField FormattedTextField() { return new FormattedTextField(); }
		@JsonProperty Group Group() { return new Group(); }
		@JsonProperty Link Link() { return new Link(); }
		@JsonProperty List List() { return new List(); }
		@JsonProperty Menu Menu() { return new Menu(); }
		@JsonProperty MenuBar MenuBar() { return new MenuBar(); }
		@JsonProperty MenuItem MenuItem() { return new MenuItem(); }
		@JsonProperty MemoryIndicator MemoryIndicator() { return new MemoryIndicator(); }
		@JsonProperty Panel Panel() { return new Panel(); }
		@JsonProperty Popup Popup() { return new Popup(); }
		@JsonProperty ScrollBar ScrollBar() { return new ScrollBar(); }
		@JsonProperty Separator Separator() { return new Separator(); }
		@JsonProperty SidePanel SidePanel() { return new SidePanel(); }
		@JsonProperty TabbedPane TabbedPane() { return new TabbedPane(); }
		@JsonProperty Table Table() { return new Table(); }
		@JsonProperty TextField TextField() { return new TextField(); }
		@JsonProperty ToolBar ToolBar() { return new ToolBar(); }
		@JsonProperty ToolWindow ToolWindow() { return new ToolWindow(); }
		@JsonProperty Tree Tree() { return new Tree(); }
		@JsonProperty VersionControl VersionControl() { return new VersionControl(); }
		@JsonProperty WelcomeScreen WelcomeScreen() { return new WelcomeScreen(); }

		class Asterisk {
			@JsonProperty Color background = style.background().ui().base();

			@JsonProperty Color hoverBackground = style.background().ui().hover();

			@JsonProperty Color selectedBackgroundInactive = style.background().ui().selectedInactive();
			@JsonProperty Color selectionBackgroundInactive = style.background().ui().selectedInactive();
			@JsonProperty Color selectionInactiveBackground = style.background().ui().selectedInactive();
			@JsonProperty Color selectedInactiveBackground = style.background().ui().selectedInactive();

			@JsonProperty Color lightSelectionBackground = style.background().ui().selected();
			@JsonProperty Color selectedBackground = style.background().ui().selected();
			@JsonProperty Color selectionBackground = style.background().ui().selected();

			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color selectionForeground = style.foreground();
			@JsonProperty Color selectedForeground = style.foreground();
			@JsonProperty Color infoForeground = style.foreground();

			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color disabledBorderColor = borderColor.brighter();
			@JsonProperty Color separatorColor = borderColor;
		}

		class ActionButton {
			@JsonProperty Color hoverBackground;
			@JsonProperty Color hoverBorderColor;
			@JsonProperty Color pressedBackground;
			@JsonProperty Color pressedBorderColor;
		}

		class Borders {
			@JsonProperty Color color = style.borderColor();
			@JsonProperty Color ContrastBorderColor = style.borderColor();
		}

		class Button {

			private final Color ordinaryButtonBackground = style.background().ui().base().darker();
			@JsonProperty Integer ark;
			@JsonProperty Color background;
			@JsonProperty Color disabledBorderColor;
			@JsonProperty Color disabledText;
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color shadowColor;
			@JsonProperty Integer shadowWidth = 0;

			@JsonProperty Color focusedBorderColor;
			@JsonProperty Color startBackground = ordinaryButtonBackground;
			@JsonProperty Color endBackground = startBackground;
			@JsonProperty Color startBorderColor = startBackground;
			@JsonProperty Color endBorderColor = startBorderColor;

			@JsonProperty("default") Default defaultt = new Default();

			class Default {

				private final Color defaultBackground = ordinaryButtonBackground.darker(2);

				@JsonProperty Color foreground = style.foreground();
				@JsonProperty Color startBackground = defaultBackground;
				@JsonProperty Color endBackground = defaultBackground;
				@JsonProperty Color startBorderColor = defaultBackground;
				@JsonProperty Color endBorderColor = defaultBackground;
				@JsonProperty Color focusColor; // only for dark theme, even for dark theme I don't see an effect
				@JsonProperty Color focusedBorderColor = style.background().editor().selectedText();
			}
		}

		class CheckBox {
			@JsonProperty Color background = style.checkbox().textBackground();
			@JsonProperty Color foreground = style.checkbox().textForeground();
			@JsonProperty Color select; // no effect
			@JsonProperty Color disabledText = style.checkbox().disabledBorder();
		}

		class ComboBox {
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color selectionForeground = style.foreground();
			@JsonProperty Color disabledForeground = palette.gray();
			@JsonProperty Color modifiedItemForeground;
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color nonEditableBackground = background;
			@JsonProperty Color selectionBackground = style.background().ui().selected();

			@JsonProperty ComboBox.ArrowButton ArrowButton = new ArrowButton();

			class ArrowButton {
				@JsonProperty Color iconColor = style.foreground();
				@JsonProperty Color disabledIconColor = palette.gray();

				@JsonProperty Color background = ComboBox.this.background;
				@JsonProperty Color nonEditableBackground = ComboBox.this.nonEditableBackground;
			}
		}

		class ComboBoxButton {
			@JsonProperty Color background = style.background().ui().base();
		}

		class CompletionPopup {
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color matchForeground = style.background().editor().selectedText().darker().darker();
			@JsonProperty Color nonFocusedMask = Color.transparent();
			@JsonProperty Color selectionBackground = style.background().editor().selectedLine();
			@JsonProperty Color selectionInactiveBackground = selectionBackground.brighter();
		}

		class Component {
			@JsonProperty Color focusedBorderColor = style.borderColor();
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Integer focusWidth;
			@JsonProperty Integer arc;
			@JsonProperty Color disabledBorderColor;
			@JsonProperty Color errorFocusColor;
			@JsonProperty Color focusColor = style.background().editor().selectedText();
			@JsonProperty Color hoverIconColor;
			@JsonProperty Color iconColor;
			@JsonProperty Color inactiveErrorFocusColor;
			@JsonProperty Color inactiveWarningFocusColor;
			@JsonProperty Color infoForeground = style.foreground();
			@JsonProperty Color warningFocusColor = style.warning();
		}

		class Editor {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color shortcutForeground = style.link();
		}

		class EditorPane {
			@JsonProperty Color background = style.background().editor().base();
			@JsonProperty Color caretForeground = style.foreground();
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color inactiveBackground = style.background().editor().base(); // descriptions
			@JsonProperty Color inactiveForeground = style.foreground();
			@JsonProperty Color selectionBackground = style.background().editor().selectedText();
			@JsonProperty Color selectionForeground = style.foreground();
		}

		class EditorTabs {
			@JsonProperty Color underlinedTabForeground = style.foreground();
			@JsonProperty Color underlinedTabBackground = style.background().editor().base();
			@JsonProperty Color hoverBackground = underlinedTabBackground;
			@JsonProperty Color background = hoverBackground.darker(3);
			@JsonProperty Color borderColor = underlinedTabBackground;

			@JsonProperty int underlineHeight = 2;
			@JsonProperty Color underlineColor = style.background().editor().selectedText();
			@JsonProperty Color inactiveUnderlineColor = underlineColor;

			@JsonProperty boolean tabInsets; // no effect
			@JsonProperty Color inactiveColoredFileBackground; // masks
		}

		class FileColor {
			@JsonProperty Color Yellow = style.background().readOnly();
			@JsonProperty Color Green = palette.green().brighter().opacity(0.13);
			@JsonProperty Color Blue = palette.blue().brighter();
			@JsonProperty Color Violet = palette.purple().brighter();
			@JsonProperty Color Orange = palette.orange().brighter();
			@JsonProperty Color Rose = palette.red().brighter();
		}

		class FormattedTextField {
			@JsonProperty Color background = style.background().ui().base().brighter();
			@JsonProperty Color caretForeground;
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color inactiveBackground = background;
			@JsonProperty Color inactiveForeground = palette.gray();
		}

		class Group {
			@JsonProperty Color disabledSeparatorColor = style.borderColor();
			@JsonProperty Color separatorColor = style.borderColor();
		}

		class Link {
			private final Color foreground = style.link();
			@JsonProperty Color activeForeground = foreground;
			@JsonProperty Color hoverForeground = foreground;
			@JsonProperty Color pressedForeground = foreground;
			@JsonProperty Color visitedForeground = foreground;
		}

		class List {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color dropLineColor = style.borderColor();
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color hoverBackground = style.background().ui().hover();
			@JsonProperty Color hoverInactiveBackground = style.background().ui().hover();
			@JsonProperty Color selectionBackground = style.background().ui().selected();
			@JsonProperty Color selectionForeground = style.foreground();
			@JsonProperty Color selectionInactiveBackground = style.background().ui().selectedInactive();
			@JsonProperty Color selectionInactiveForeground = style.foreground();
		}

		class MemoryIndicator {
			@JsonProperty Color allocatedBackground = style.background().ui().hover();
			@JsonProperty Color usedBackground = style.background().ui().selected();
		}

		class Menu {
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color acceleratorForeground = style.foreground();
			@JsonProperty Color acceleratorSelectionForeground = style.foreground();
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color disabledBackground;
			@JsonProperty Color disabledForeground;
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color selectionForeground = style.foreground();
			@JsonProperty Color separatorColor = style.borderColor(); // * color works as supposed, this one changes color but not exactly as expected
		}

		class MenuBar {
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color disabledBackground = style.background().ui().base();
			@JsonProperty Color disabledForeground = palette.gray();
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color hightlight; // unknown effect
			@JsonProperty Color selectionBackground = style.background().ui().selected();
			@JsonProperty Color selectionForeground = style.foreground();
			@JsonProperty Color shadow;
		}

		class MenuItem {
			@JsonProperty Color acceleratorForeground = palette.gray();
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color disabledBackground = style.background().ui().base();
			@JsonProperty Color disabledForeground = palette.gray();
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color selectionBackground = style.background().ui().selected();
			@JsonProperty Color selectionForeground = style.foreground();
		}

		class Panel {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color foreground = style.foreground();
		}

		class Popup {
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color inactiveBorderColor = style.borderColor();
			@JsonProperty Color innerBorderColor = style.borderColor();
			@JsonProperty boolean paintBorder; // on Mac
			@JsonProperty Color separatorColor = style.borderColor();
			@JsonProperty Color separatorForeground = style.foreground();

			@JsonProperty Header Header = new Header();
			@JsonProperty Advertiser Advertiser = new Advertiser();
			@JsonProperty Toolbar Toolbar = new Toolbar();

			class Header {
				@JsonProperty Color inactiveBackground = style.background().ui().base().darker();
				@JsonProperty Color activeBackground = style.background().ui().base().darker(2);
			}

			class Advertiser {
				@JsonProperty Color background = style.background().ui().base();
				@JsonProperty Color borderColor = style.borderColor();
				@JsonProperty Integer borderInsets;
				@JsonProperty Color foreground = style.foreground();
			}

			class Toolbar {
				@JsonProperty Color background = style.background().ui().base();
				@JsonProperty Color borderColor = style.borderColor();
			}
		}

		class ScrollBar {
			@JsonProperty Color track;
			@JsonProperty Color trackColor;
			@JsonProperty Color background;
			@JsonProperty Color hoverThumbBorderColor;
			@JsonProperty Color hoverThumbColor;
			@JsonProperty Color hoverTrackColor;
			@JsonProperty Color thumb;
			@JsonProperty Color thumbBorderColor;
			@JsonProperty Color thumbColor;
			@JsonProperty Color thumbDarkShadow;
			@JsonProperty Color thumbHighlight;
			@JsonProperty Color thumbShadow;
			@JsonProperty Color trackHighlight;

			@JsonProperty ScrollBar.Mac Mac = new Mac();
			@JsonProperty ScrollBar.Transparent Transparent = new Transparent();

			class Mac {
				@JsonProperty Color hoverThumbBorderColor;
				@JsonProperty Color hoverThumbColor;
				@JsonProperty Color hoverTrackColor;
				@JsonProperty Color thumbBorderColor;
				@JsonProperty Color thumbColor;
				@JsonProperty ScrollBar.Transparent Transparent = new Transparent();
			}

			class Transparent {
				@JsonProperty Color trackColor = style.scrollbar().trackColor();
				@JsonProperty Color thumbColor = style.scrollbar().thumbColor();
				@JsonProperty Color thumbBorderColor = style.scrollbar().thumbBorderColor();
				@JsonProperty Color hoverTrackColor = style.scrollbar().hoverTrackColor();
				@JsonProperty Color hoverThumbColor = style.scrollbar().hoverThumbColor();
				@JsonProperty Color hoverThumbBorderColor = style.scrollbar().hoverThumbBorderColor();
			}
		}

		class Separator {
			@JsonProperty Color separatorColor = style.borderColor();
		}

		class SidePanel {
			@JsonProperty Color background = style.background().ui().base().darker();
		}

		class TabbedPane { // example: editor -> code style
			@JsonProperty Color background = style.tab().inBackground();
			@JsonProperty Color contentAreaColor = style.background().ui().base();
			@JsonProperty Color disabledForeground = style.foreground();
			@JsonProperty Color focus = style.tab().selected();
			@JsonProperty Color focusColor = style.tab().selected();
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color hoverColor = style.tab().hover();
			@JsonProperty int tabSelectionHeight = 0;
			@JsonProperty Color underlineColor;
			@JsonProperty Color disabledUnderlineColor;
		}

		class Table {
			@JsonProperty Color stripeColor;
			@JsonProperty Color lightSelectionForeground = style.foreground();
			@JsonProperty Color lightSelectionInactiveForeground = style.foreground();
			@JsonProperty Color lightSelectionBackground = style.background().ui().selected();
			@JsonProperty Color lightSelectionInactiveBackground = style.background().ui().selectedInactive();
		}

		class TextField {
			@JsonProperty Color background = style.background().editor().base();
			@JsonProperty Color caretForeground = style.foreground();
			@JsonProperty Color darkShadow;
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color highlight;
			@JsonProperty Color inactiveForeground = palette.gray();
			@JsonProperty Color selectionBackground = style.background().editor().selectedText();
			@JsonProperty Color selectionForeground = style.foreground();
		}

		class ToolBar { // unknown effect
			@JsonProperty Color background;
			@JsonProperty Color borderHandleColor;
			@JsonProperty Color darkShadow;
			@JsonProperty("Floating.background") Color floatingBackground;
			@JsonProperty Color floatingForeground;
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color hightlight;
			@JsonProperty Color light;
			@JsonProperty Color shadow;
		}

		class ToolWindow {
			@JsonProperty ToolWindow.Header Header = new Header();
			@JsonProperty ToolWindow.HeaderTab HeaderTab = new HeaderTab();

			class Header {
				@JsonProperty Color inactiveBackground = style.tab().inBackgroundInactive();
				@JsonProperty Color borderColor = style.tab().borderColor();
				@JsonProperty Color background = style.tab().inBackground();
			}

			class HeaderTab {
				@JsonProperty Color hoverBackground = style.tab().hover();
				@JsonProperty Color hoverInactiveBackground = hoverBackground;
				@JsonProperty Color inactiveUnderlineColor;
				@JsonProperty Color selectedInactiveBackground = style.tab().selected();
				@JsonProperty Color underlineColor;
				@JsonProperty Color underlinedTabBackground = style.tab().selected();
				@JsonProperty Color underlinedTabInactiveBackground = style.tab().selected();
				@JsonProperty int underlineHeight = 0;
			}
		}

		class Tree {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color errorForeground;
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color hash = style.background().ui().base().darker(2);
			@JsonProperty Color hoverBackground = style.background().ui().hover();
			@JsonProperty Color hoverInactiveBackground = hoverBackground;
			@JsonProperty Color modifiedItemForeground;
			@JsonProperty Color paintLines;
			@JsonProperty Color rowHeight;
			@JsonProperty Color selectionBackground = style.background().ui().selected();
			@JsonProperty Color selectionForeground = style.foreground();
			@JsonProperty Color selectionInactiveBackground = style.background().ui().selectedInactive();
		}

		class VersionControl {
			@JsonProperty("FileHistory.Commit.selectedBranchBackground") Color selectedBranchBackground;

			@JsonProperty VersionControl.GitLog GitLog = new GitLog();
			@JsonProperty VersionControl.Log Log = new Log();
			@JsonProperty VersionControl.RefLabel RefLabel = new RefLabel();

			class GitLog {
				@JsonProperty Color headIconColor;
				@JsonProperty Color localBranchIconColor;
				@JsonProperty Color otherIconColor;
				@JsonProperty Color remoteBranchIconColor;
				@JsonProperty Color tagIconColor;
			}

			class Log {
				@JsonProperty VersionControl.Log.Commit Commit = new Commit();

				class Commit {
					@JsonProperty Color currentBranchBackground = style.background().ui().base().darker();
					@JsonProperty Color hoveredBackground = currentBranchBackground.darker();
					@JsonProperty Color unmatchedForeground;
				}
			}

			class RefLabel {
				@JsonProperty Color backgroundBase = palette.blue();
				@JsonProperty double backgroundBrightness = 0.50;
				@JsonProperty Color foreground = style.foreground();
			}
		}

		class WelcomeScreen {
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color captionBackground = palette.red();
			@JsonProperty Color captionForeground = style.foreground();
			@JsonProperty Color footerBackground;
			@JsonProperty Color footerForeground = style.foreground();
			@JsonProperty Color groupIconBorderColor = style.borderColor();
			@JsonProperty Color headerBackground;
			@JsonProperty Color headerForeground = style.foreground();
			@JsonProperty Color separatorColor = style.borderColor();

			@JsonProperty Details Details = new Details();
			@JsonProperty Projects Projects = new Projects();
			@JsonProperty SidePanel SidePanel = new SidePanel();

			class Details {
				@JsonProperty Color background = style.background().ui().base();
			}

			class Projects {
				@JsonProperty Color background = style.background().ui().base();
				@JsonProperty Color selectionBackground = style.background().ui().selected();
				@JsonProperty Color selectionInactiveBackground = style.background().ui().selectedInactive();

				@JsonProperty Actions actions = new Actions();

				class Actions {
					@JsonProperty Color background = style.background().ui().base();
					@JsonProperty Color selectionBackground = style.background().ui().selected();
				}
			}

			class SidePanel {
				@JsonProperty Color background = style.background().ui().base().darker();
			}
		}
	}

	static class Icons {
		private final Palette palette;
		private final Style style;

		Icons(Palette palette, Style style) {
			this.palette = requireNonNull(palette);
			this.style = requireNonNull(style);
		}

		@JsonProperty ColorPalette ColorPalette() { return new ColorPalette(); }

		class ColorPalette {
			@JsonUnwrapped(prefix = "Actions.") @JsonProperty ColorPalette.Actions Actions = new Actions();
			@JsonUnwrapped(prefix = "Objects.") @JsonProperty ColorPalette.Objects Objects = new Objects();
			@JsonUnwrapped(prefix = "Checkbox.") @JsonProperty ColorPalette.Checkbox Checkbox = new Checkbox();

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
				@JsonProperty Color BlackText = style.foreground();
				@JsonProperty Color YellowDark = palette.yellow().darker();
			}

			class Checkbox {
				@JsonUnwrapped(prefix = "Foreground.") @JsonProperty ColorPalette.Checkbox.Foreground Foreground = new Foreground();
				@JsonUnwrapped(prefix = "Background.") @JsonProperty ColorPalette.Checkbox.Background Background = new Background();
				@JsonUnwrapped(prefix = "Border.") @JsonProperty ColorPalette.Checkbox.Border Border = new Border();
				@JsonUnwrapped(prefix = "Focus.") @JsonProperty ColorPalette.Checkbox.Focus Focus = new Focus();

				class Foreground {
					@JsonProperty("Default") Color Defaultt;
					@JsonProperty("Selected") Color Selected;
					@JsonProperty("Disabled") Color Disabled;
					@JsonProperty("Selected.Dark") Color SelectedDark;
					@JsonProperty("Disabled.Dark") Color DisabledDark;
				}

				class Background {
					@JsonProperty("Default") Color Defaultt = style.checkbox().hook();
					@JsonProperty("Disabled") Color Disabled = style.checkbox().disabledBoxBackground();
					@JsonProperty("Selected") Color Selected = style.checkbox().selectedBoxBackground();
					@JsonProperty("Default.Dark") Color DefaultDark;
					@JsonProperty("Disabled.Dark") Color DisabledDark;
					@JsonProperty("Selected.Dark") Color SelectedDark;
				}

				class Border {
					@JsonProperty("Default") Color Defaultt = style.checkbox().border();
					@JsonProperty("Disabled") Color Disabled = style.checkbox().disabledBorder();
					@JsonProperty("Selected") Color Selected = Defaultt;
					@JsonProperty("Default.Dark") Color DefaultDark;
					@JsonProperty("Disabled.Dark") Color DisabledDark;
				}

				class Focus {
					@JsonProperty("Wide") Color Wide = style.checkbox().focus();
					@JsonProperty("Wide.Dark") Color WideDark;
					@JsonUnwrapped(prefix = "Thin.") @JsonProperty ColorPalette.Checkbox.Focus.Thin Thin = new Thin();

					class Thin {
						@JsonProperty("Default") Color Defaultt = style.checkbox().focus();
						@JsonProperty("Selected") Color Selected = style.checkbox().focus();
						@JsonProperty("Default.Dark") Color DefaultDark;
						@JsonProperty("Selected.Dark") Color SelectedDark;
					}
				}
			}
		}
	}
}
