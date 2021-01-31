package plugin.intellij.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.style.Style;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

class UI {
	private final Palette palette;
	private final Style style;

	UI(Palette palette, Style style) {
		this.palette = requireNonNull(palette);
		this.style = Objects.requireNonNull(style);
	}

	// @JsonProperty("*") Asterisk asterisk() { return new Asterisk(); }
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
	@JsonProperty Label Label() { return new Label(); }
	@JsonProperty Link Link() { return new Link(); }
	@JsonProperty List List() { return new List(); }
	@JsonProperty Menu Menu() { return new Menu(); }
	@JsonProperty MenuBar MenuBar() { return new MenuBar(); }
	@JsonProperty MenuItem MenuItem() { return new MenuItem(); }
	@JsonProperty MemoryIndicator MemoryIndicator() { return new MemoryIndicator(); }
	@JsonProperty Notification Notification() { return new Notification(); }
	@JsonProperty OptionPane OptionPane() { return new OptionPane(); }
	@JsonProperty Panel Panel() { return new Panel(); }
	@JsonProperty ParameterInfo ParameterInfo() { return new ParameterInfo(); }
	@JsonProperty Popup Popup() { return new Popup(); }
	@JsonProperty PopupMenu PopupMenu() { return new PopupMenu(); }
	@JsonProperty PopupMenuSeparator PopupMenuSeparator() { return new PopupMenuSeparator(); }
	@JsonProperty RadioButton RadioButton() { return new RadioButton(); }
	@JsonProperty ScrollBar ScrollBar() { return new ScrollBar(); }
	@JsonProperty SearchEverywhere SearchEverywhere() { return new SearchEverywhere(); }
	@JsonProperty Separator Separator() { return new Separator(); }
	@JsonProperty SearchMatch SearchMatch() { return new SearchMatch(); }
	@JsonProperty SidePanel SidePanel() { return new SidePanel(); }
	@JsonProperty StatusBar StatusBar() { return new StatusBar(); }
	@JsonProperty TabbedPane TabbedPane() { return new TabbedPane(); }
	@JsonProperty Table Table() { return new Table(); }
	@JsonProperty TextArea TextArea() { return new TextArea(); }
	@JsonProperty TextField TextField() { return new TextField(); }
	@JsonProperty TextPane TextPane() { return new TextPane(); }
	@JsonProperty ToolBar ToolBar() { return new ToolBar(); }
	@JsonProperty ToolTip ToolTip() { return new ToolTip(); }
	@JsonProperty ToolWindow ToolWindow() { return new ToolWindow(); }
	@JsonProperty Tree Tree() { return new Tree(); }
	@JsonProperty VersionControl VersionControl() { return new VersionControl(); }
	@JsonProperty Viewport Viewport() { return new Viewport(); }
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
		@JsonProperty Color infoForeground = style.infoForeground();

		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color disabledBorderColor = borderColor.brighter();
		@JsonProperty Color separatorColor = borderColor;
	}

	class ActionButton {
		@JsonProperty Color hoverBackground = style.background().ui().hover();
		@JsonProperty Color hoverBorderColor = hoverBackground.darker();
		@JsonProperty Color pressedBackground = style.background().ui().selected();
		@JsonProperty Color pressedBorderColor = pressedBackground.darker();
	}

	class Borders {
		@JsonProperty Color color = style.borderColor();
		@JsonProperty Color ContrastBorderColor = style.borderColor();
	}

	class Button {
		@JsonProperty Integer ark;
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color background = style.button().around();
		@JsonProperty Color disabledText = style.disabledForeground();
		@JsonProperty Color shadowColor;
		@JsonProperty Integer shadowWidth = 0;

		@JsonProperty Color startBackground = style.button().ordinary().background();
		@JsonProperty Color endBackground = style.button().ordinary().background();
		@JsonProperty Color startBorderColor = style.button().ordinary().border();
		@JsonProperty Color endBorderColor = style.button().ordinary().border();
		@JsonProperty Color disabledBorderColor = style.button().ordinary().disabledBorder();
		@JsonProperty Color focusedBorderColor = style.button().ordinary().focusedBorder();

		@JsonProperty("default") Button.Default Default = new Button.Default();

		class Default {
			@JsonProperty Color foreground = style.foreground();
			@JsonProperty Color startBackground = style.button().Default().background();
			@JsonProperty Color endBackground = style.button().Default().background();
			@JsonProperty Color startBorderColor = style.button().Default().border();
			@JsonProperty Color endBorderColor = style.button().Default().border();
			@JsonProperty Color focusedBorderColor = style.button().Default().focusedBorder();
			@JsonProperty Color focusColor; // only for dark theme, even for dark theme I don't see an effect
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
		@JsonProperty Color disabledForeground = style.disabledForeground();
		@JsonProperty Color modifiedItemForeground;
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color nonEditableBackground = background;
		@JsonProperty Color selectionBackground = style.background().ui().selected();

		@JsonProperty ComboBox.ArrowButton ArrowButton = new ComboBox.ArrowButton();

		class ArrowButton {
			@JsonProperty Color iconColor = style.foreground();
			@JsonProperty Color disabledIconColor = style.disabledForeground();

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
		@JsonProperty Color infoForeground = style.infoForeground();
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

		@JsonProperty int underlineHeight = style.tab().editorUnderlineHeight();
		@JsonProperty Color underlineColor = style.tab().underline();
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
		@JsonProperty Color caretForeground = style.foreground();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color inactiveBackground = background;
		@JsonProperty Color inactiveForeground = style.disabledForeground();
	}

	class Group {
		@JsonProperty Color disabledSeparatorColor = style.borderColor();
		@JsonProperty Color separatorColor = style.borderColor();
	}

	class Label {
		@JsonProperty Color background = style.background().ui().base(); // unknown effect
		@JsonProperty Color disabledText = style.disabledForeground(); // unknown effect
		@JsonProperty Color disabledForeground = style.disabledForeground(); // unknown effect
		@JsonProperty Color errorForeground = style.error(); // unknown effect
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color infoForeground = style.infoForeground();
		@JsonProperty Color selectedForeground = style.foreground(); // unknown effect
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
		@JsonProperty Color disabledForeground = style.disabledForeground();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color hightlight; // unknown effect
		@JsonProperty Color selectionBackground = style.background().ui().selected();
		@JsonProperty Color selectionForeground = style.foreground();
		@JsonProperty Color shadow;
	}

	class MenuItem {
		@JsonProperty Color acceleratorForeground = style.disabledForeground();
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color disabledBackground = style.background().ui().base();
		@JsonProperty Color disabledForeground = style.disabledForeground();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color selectionBackground = style.background().ui().selected();
		@JsonProperty Color selectionForeground = style.foreground();
	}

	class Notification {
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color errorBackground = style.error();
		@JsonProperty Color errorBorderColor = style.borderColor();
		@JsonProperty Color errorForeground = style.foreground();
		@JsonProperty Color foreground = style.foreground();

		@JsonProperty MoreButton MoreButton = new MoreButton();
		@JsonProperty ToolWindow ToolWindow = new ToolWindow();

		class MoreButton {
			@JsonProperty Color background = Notification.this.background.darker();
			@JsonProperty Color foreground = style.disabledForeground();
			@JsonProperty Color innerBorderColor = background.darker(2);
		}

		class ToolWindow {
			@JsonProperty Color errorBackground = style.error().brightest();
			@JsonProperty Color errorBorderColor = errorBackground.darker();
			@JsonProperty Color errorForeground = style.foreground();
			@JsonProperty Color informativeBackground = style.info().brightest();
			@JsonProperty Color informativeBorderColor = informativeBackground.darker();
			@JsonProperty Color informativeForeground = style.foreground();
			@JsonProperty Color warningBackground = style.warning().brightest();
			@JsonProperty Color warningBorderColor = warningBackground.darker();
			@JsonProperty Color warningForeground = style.foreground();
		}
	}

	class OptionPane {
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color messageForeground = style.foreground();
	}

	class Panel {
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color foreground = style.foreground();
	}

	class ParameterInfo {
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color currentOverloadBackground = style.background().ui().selected();
		@JsonProperty Color currentParameterForeground = style.foreground();
		@JsonProperty Color disabledForeground = style.disabledForeground();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color infoForeground = style.infoForeground();
		@JsonProperty Color lineSeparatorColor = style.borderColor();
	}

	class Popup {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color inactiveBorderColor = style.borderColor();
		@JsonProperty Color innerBorderColor = style.borderColor();
		@JsonProperty boolean paintBorder; // on Mac
		@JsonProperty Color separatorColor = style.borderColor();
		@JsonProperty Color separatorForeground = style.foreground();

		@JsonProperty Popup.Header Header = new Popup.Header();
		@JsonProperty Popup.Advertiser Advertiser = new Popup.Advertiser();
		@JsonProperty Popup.Toolbar Toolbar = new Popup.Toolbar();

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

	class PopupMenu {
		@JsonProperty Color background = style.background().ui().base(); // menu separators
		@JsonProperty Integer borderInsets;
		@JsonProperty Integer borderWidth = 0;
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color selectionBackground = style.background().ui().selected();
		@JsonProperty Color selectionForeground = style.foreground();
		@JsonProperty Color translucentBackgroun;
	}

	class PopupMenuSeparator {
		@JsonProperty Integer height;
		@JsonProperty Integer stripeIndent;
		@JsonProperty Integer stripeWidth;
	}

	class RadioButton {
		@JsonProperty Color background = style.checkbox().textBackground();
		@JsonProperty Color foreground = style.checkbox().textForeground();
		@JsonProperty Color select; // unknown effect
		@JsonProperty Color disabledText = style.checkbox().disabledBorder();
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

		@JsonProperty ScrollBar.Mac Mac = new ScrollBar.Mac();
		@JsonProperty ScrollBar.Transparent Transparent = new ScrollBar.Transparent();

		class Mac {
			@JsonProperty Color hoverThumbBorderColor;
			@JsonProperty Color hoverThumbColor;
			@JsonProperty Color hoverTrackColor;
			@JsonProperty Color thumbBorderColor;
			@JsonProperty Color thumbColor;
			@JsonProperty ScrollBar.Transparent Transparent = new ScrollBar.Transparent();
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

	class SearchEverywhere {
		@JsonProperty SearchEverywhere.Advertiser Advertiser = new SearchEverywhere.Advertiser();
		@JsonProperty SearchEverywhere.Header Header = new SearchEverywhere.Header();
		@JsonProperty SearchEverywhere.List List = new SearchEverywhere.List();
		@JsonProperty SearchEverywhere.SearchField SearchField = new SearchEverywhere.SearchField();
		@JsonProperty SearchEverywhere.Tab Tab = new SearchEverywhere.Tab();

		class Advertiser {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Integer borderInsets;
			@JsonProperty Color foreground = style.foreground();
		}

		class Header {
			@JsonProperty Color background = style.background().ui().base().darker();
		}

		class List {
			@JsonProperty Color separatorColor = style.borderColor();
			@JsonProperty Color separatorForeground = style.foreground();
		}

		class SearchField {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color infoForeground = style.infoForeground();
		}

		class Tab {
			@JsonProperty Color selectedBackground = style.background().ui().selected();
			@JsonProperty Color selectedForeground = style.foreground();
		}
	}

	class Separator {
		@JsonProperty Color separatorColor = style.borderColor();
	}

	class SearchMatch {
		@JsonProperty Color startBackground = style.searchBackground();
		@JsonProperty Color endBackground = startBackground;
	}

	class SidePanel {
		@JsonProperty Color background = style.background().ui().base().darker();
	}

	class StatusBar {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color hoverBackground = style.background().ui().hover();
	}

	class TabbedPane { // example: editor -> code style
		@JsonProperty Color background = style.tab().inBackground();
		@JsonProperty Color contentAreaColor = style.background().ui().base();
		@JsonProperty Color disabledForeground = style.foreground();
		@JsonProperty Color focus = style.tab().selected();
		@JsonProperty Color focusColor = style.tab().selected();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color hoverColor = style.tab().hover();
		@JsonProperty int tabSelectionHeight = style.tab().settingsUnderlineHeight();
		@JsonProperty Color underlineColor = style.tab().underline();
		@JsonProperty Color disabledUnderlineColor = underlineColor;
	}

	class Table {
		@JsonProperty Color stripeColor;
		@JsonProperty Color lightSelectionForeground = style.foreground();
		@JsonProperty Color lightSelectionInactiveForeground = style.foreground();
		@JsonProperty Color lightSelectionBackground = style.background().ui().selected();
		@JsonProperty Color lightSelectionInactiveBackground = style.background().ui().selectedInactive();
	}

	class TextArea {
		@JsonProperty Color background = style.background().editor().base();
		@JsonProperty Color caretForeground = style.foreground();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color inactiveBackground = style.background().editor().base();
		@JsonProperty Color inactiveForeground = style.foreground();
		@JsonProperty Color selectionBackground = style.background().editor().selectedText();
		@JsonProperty Color selectionForeground = style.foreground();
	}

	class TextField {
		@JsonProperty Color background = style.background().editor().base();
		@JsonProperty Color caretForeground = style.foreground();
		@JsonProperty Color darkShadow;
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color highlight;
		@JsonProperty Color inactiveForeground = style.disabledForeground();
		@JsonProperty Color selectionBackground = style.background().editor().selectedText();
		@JsonProperty Color selectionForeground = style.foreground();
	}

	class TextPane {
		@JsonProperty Color background = style.background().editor().base();
		@JsonProperty Color caretForeground = style.foreground();
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color inactiveBackground = style.background().readOnly();
		@JsonProperty Color inactiveForeground = style.foreground();
		@JsonProperty Color selectionBackground = style.background().editor().selectedLine();
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

	class ToolTip {
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color borderColor;
		@JsonProperty Color foreground = style.foreground();
		@JsonProperty Color infoForeground = style.infoForeground();
		@JsonProperty Boolean paintBorder = false;
		@JsonProperty Color shortcutForeground = style.disabledForeground();

		@JsonProperty ToolTip.Actions Actions = new ToolTip.Actions();

		class Actions {
			@JsonProperty Color background = ToolTip.this.background.darker();
			@JsonProperty Color infoForeground = style.infoForeground();
		}
	}

	class ToolWindow {
		@JsonProperty ToolWindow.Button Button = new ToolWindow.Button();
		@JsonProperty ToolWindow.Header Header = new ToolWindow.Header();
		@JsonProperty ToolWindow.HeaderCloseButton HeaderCloseButton = new ToolWindow.HeaderCloseButton();
		@JsonProperty ToolWindow.HeaderTab HeaderTab = new ToolWindow.HeaderTab();

		class Button {
			@JsonProperty Color hoverBackground = style.background().ui().hover();
			@JsonProperty Color selectedBackground = style.background().ui().selected();
			@JsonProperty Color selectedForeground = style.foreground();
		}

		class Header {
			@JsonProperty Color inactiveBackground = style.tab().inBackgroundInactive();
			@JsonProperty Color borderColor = style.tab().borderColor();
			@JsonProperty Color background = style.tab().inBackground();
		}

		class HeaderCloseButton {
			@JsonProperty Color background = palette.red(); // unknown effect
		}

		class HeaderTab {
			@JsonProperty Color hoverBackground = style.tab().hover();
			@JsonProperty Color hoverInactiveBackground = hoverBackground;
			@JsonProperty Color selectedInactiveBackground = style.tab().selected();
			@JsonProperty Color underlinedTabBackground = style.tab().selected();
			@JsonProperty Color underlinedTabInactiveBackground = style.tab().selected();
			@JsonProperty int underlineHeight = 0;
			@JsonProperty Color underlineColor;
			@JsonProperty Color inactiveUnderlineColor;
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

		@JsonProperty VersionControl.GitLog GitLog = new VersionControl.GitLog();
		@JsonProperty VersionControl.Log Log = new VersionControl.Log();
		@JsonProperty VersionControl.RefLabel RefLabel = new VersionControl.RefLabel();

		class GitLog {
			@JsonProperty Color headIconColor;
			@JsonProperty Color localBranchIconColor;
			@JsonProperty Color otherIconColor;
			@JsonProperty Color remoteBranchIconColor;
			@JsonProperty Color tagIconColor;
		}

		class Log {
			@JsonProperty VersionControl.Log.Commit Commit = new VersionControl.Log.Commit();

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

	class Viewport {
		@JsonProperty Color background = style.background().ui().base();
		@JsonProperty Color foreground = style.foreground();
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

		@JsonProperty WelcomeScreen.Details Details = new WelcomeScreen.Details();
		@JsonProperty WelcomeScreen.Projects Projects = new WelcomeScreen.Projects();
		@JsonProperty WelcomeScreen.SidePanel SidePanel = new WelcomeScreen.SidePanel();

		class Details {
			@JsonProperty Color background = style.background().ui().base();
		}

		class Projects {
			@JsonProperty Color background = style.background().ui().base();
			@JsonProperty Color selectionBackground = style.background().ui().selected();
			@JsonProperty Color selectionInactiveBackground = style.background().ui().selectedInactive();

			@JsonProperty WelcomeScreen.Projects.Actions actions = new WelcomeScreen.Projects.Actions();

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
