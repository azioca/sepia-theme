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

	@JsonProperty ActionButton ActionButton() { return new ActionButton(); }
	@JsonProperty Borders Borders() { return new Borders(); }
	@JsonProperty Button Button() { return new Button(); }
	@JsonProperty CheckBox CheckBox() { return new CheckBox(); }
	@JsonProperty ComboBox ComboBox() { return new ComboBox(); }
	@JsonProperty ComboBoxButton ComboBoxButton() { return new ComboBoxButton(); }
	@JsonProperty CompletionPopup CompletionPopup() { return new CompletionPopup(); }
	@JsonProperty Component Component() { return new Component(); }
	@JsonProperty DefaultTabs DefaultTabs() { return new DefaultTabs(); }
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
	@JsonProperty NewClass NewClass() { return new NewClass(); }
	@JsonProperty Notification Notification() { return new Notification(); }
	@JsonProperty OptionPane OptionPane() { return new OptionPane(); }
	@JsonProperty Panel Panel() { return new Panel(); }
	@JsonProperty ParameterInfo ParameterInfo() { return new ParameterInfo(); }
	@JsonProperty Plugins Plugins() { return new Plugins(); }
	@JsonProperty Popup Popup() { return new Popup(); }
	@JsonProperty PopupMenu PopupMenu() { return new PopupMenu(); }
	@JsonProperty PopupMenuSeparator PopupMenuSeparator() { return new PopupMenuSeparator(); }
	@JsonProperty ProgressBar ProgressBar() { return new ProgressBar(); }
	@JsonProperty RadioButton RadioButton() { return new RadioButton(); }
	@JsonProperty ScrollBar ScrollBar() { return new ScrollBar(); }
	@JsonProperty SearchEverywhere SearchEverywhere() { return new SearchEverywhere(); }
	@JsonProperty SearchMatch SearchMatch() { return new SearchMatch(); }
	@JsonProperty Separator Separator() { return new Separator(); }
	@JsonProperty Settings Settings() { return new Settings(); }
	@JsonProperty SidePanel SidePanel() { return new SidePanel(); }
	@JsonProperty Slider Slider() { return new Slider(); }
	@JsonProperty SpeedSearch SpeedSearch() { return new SpeedSearch(); }
	@JsonProperty StatusBar StatusBar() { return new StatusBar(); }
	@JsonProperty TabbedPane TabbedPane() { return new TabbedPane(); }
	@JsonProperty Table Table() { return new Table(); }
	@JsonProperty TableHeader TableHeader() { return new TableHeader(); }
	@JsonProperty TextArea TextArea() { return new TextArea(); }
	@JsonProperty TextField TextField() { return new TextField(); }
	@JsonProperty TextPane TextPane() { return new TextPane(); }
	@JsonProperty ToolBar ToolBar() { return new ToolBar(); }
	@JsonProperty("Toolbar.Floating.background") Color ToolBarFloatingBackground() { return style.theme().background().base().darker(); }
	@JsonProperty ToolTip ToolTip() { return new ToolTip(); }
	@JsonProperty ToolWindow ToolWindow() { return new ToolWindow(); }
	@JsonProperty Tree Tree() { return new Tree(); }
	@JsonProperty ValidationTooltip ValidationTooltip() { return new ValidationTooltip(); }
	@JsonProperty VersionControl VersionControl() { return new VersionControl(); }
	@JsonProperty Viewport Viewport() { return new Viewport(); }
	@JsonProperty WelcomeScreen WelcomeScreen() { return new WelcomeScreen(); }

	class ActionButton {
		@JsonProperty Color hoverBackground = style.theme().background().hover();
		@JsonProperty Color hoverBorderColor = hoverBackground.darker();
		@JsonProperty Color pressedBackground = style.theme().background().selected();
		@JsonProperty Color pressedBorderColor = pressedBackground.darker();
	}

	class Borders {
		@JsonProperty Color color = style.borderColor();
		@JsonProperty Color ContrastBorderColor = style.borderColor();
	}

	class Button {
		@JsonProperty Integer ark;
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color background = style.button().around();
		@JsonProperty Color disabledText = style.theme().foreground().disabled();
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
			@JsonProperty Color foreground = style.theme().foreground().base();
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
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color disabledForeground = style.theme().foreground().disabled();
		@JsonProperty Color modifiedItemForeground = style.theme().foreground().modified();
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color disabledBackground = background; // deprecated but it works, no other ways of setting this value in code
		@JsonProperty Color nonEditableBackground = background;
		@JsonProperty Color selectionBackground = style.theme().background().selected();

		@JsonProperty ComboBox.ArrowButton ArrowButton = new ComboBox.ArrowButton();

		class ArrowButton {
			@JsonProperty Color iconColor = style.theme().foreground().base();
			@JsonProperty Color disabledIconColor = style.theme().foreground().disabled();

			@JsonProperty Color background = ComboBox.this.background;
			@JsonProperty Color nonEditableBackground = ComboBox.this.nonEditableBackground;
		}
	}

	class ComboBoxButton {
		@JsonProperty Color background = style.theme().background().base();
	}

	class CompletionPopup {
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color matchForeground = style.scheme().background().selectedText().darker().darker();
		@JsonProperty Color nonFocusedMask = Color.transparent();
		@JsonProperty Color selectionBackground = style.scheme().background().selectedLine();
		@JsonProperty Color selectionInactiveBackground = selectionBackground.brighter();
	}

	class Component {
		@JsonProperty Color focusedBorderColor = style.borderColor();
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Integer focusWidth;
		@JsonProperty Integer arc;
		@JsonProperty Color disabledBorderColor = borderColor.brighter();
		@JsonProperty Color errorFocusColor = style.error();
		@JsonProperty Color focusColor = style.scheme().background().selectedText();
		@JsonProperty Color hoverIconColor;
		@JsonProperty Color iconColor;
		@JsonProperty Color inactiveErrorFocusColor = style.error().opacity(0.5);
		@JsonProperty Color inactiveWarningFocusColor = style.warning().opacity(0.5);
		@JsonProperty Color infoForeground = style.theme().foreground().info();
		@JsonProperty Color warningFocusColor = style.warning();
	}

	class DefaultTabs {
		@JsonProperty Color background = style.tab().inBackground();
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color hoverBackground = style.tab().hover();
		@JsonProperty Color inactiveUnderlineColor = style.tab().underline();
		@JsonProperty Color underlineColor = style.tab().underline();
		@JsonProperty Color underlinedTabBackground = style.tab().selected();
		@JsonProperty Color underlinedTabForeground = style.theme().foreground().base();
		@JsonProperty Integer underlineHeight = style.tab().settingsUnderlineHeight();
	}

	class Editor {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color shortcutForeground = style.link();
	}

	class EditorPane {
		@JsonProperty Color background = style.scheme().background().base();
		@JsonProperty Color caretForeground = style.theme().foreground().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color inactiveBackground = style.scheme().background().base(); // descriptions

		@JsonProperty Color inactiveForeground = style.theme().foreground().base();
		@JsonProperty Color selectionBackground = style.scheme().background().selectedText();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
	}

	class EditorTabs {
		@JsonProperty Color underlinedTabForeground = style.theme().foreground().base();
		@JsonProperty Color underlinedTabBackground = style.scheme().background().base();
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
		@JsonProperty Color Yellow = style.theme().background().readOnly();
		@JsonProperty Color Green = palette.green().brighter().opacity(0.13);
		@JsonProperty Color Blue = palette.blue().brighter();
		@JsonProperty Color Violet = palette.purple().brighter();
		@JsonProperty Color Orange = palette.orange().brighter();
		@JsonProperty Color Rose = palette.red().brighter();
	}

	class FormattedTextField {
		@JsonProperty Color background = style.theme().background().base().brighter();
		@JsonProperty Color caretForeground = style.theme().foreground().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color inactiveBackground = background;
		@JsonProperty Color inactiveForeground = style.theme().foreground().disabled();
	}

	class Group {
		@JsonProperty Color disabledSeparatorColor = style.borderColor();
		@JsonProperty Color separatorColor = style.borderColor();
	}

	class Label {
		@JsonProperty Color background = style.theme().background().base(); // unknown effect

		@JsonProperty Color disabledText = style.theme().foreground().disabled(); // unknown effect

		@JsonProperty Color disabledForeground = style.theme().foreground().disabled(); // unknown effect

		@JsonProperty Color errorForeground = style.error(); // unknown effect
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color infoForeground = style.theme().foreground().info();
		@JsonProperty Color selectedForeground = style.theme().foreground().base(); // unknown effect
	}

	class Link {
		private final Color foreground = style.link();
		@JsonProperty Color activeForeground = foreground;
		@JsonProperty Color hoverForeground = foreground;
		@JsonProperty Color pressedForeground = foreground;
		@JsonProperty Color visitedForeground = foreground;
	}

	class List {
		@JsonProperty Color background = style.theme().background().base(); // used also for branch search field

		@JsonProperty Color dropLineColor = style.borderColor();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color hoverBackground = style.theme().background().hover();
		@JsonProperty Color hoverInactiveBackground = style.theme().background().hover();
		@JsonProperty Color selectionBackground = style.theme().background().selected();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color selectionInactiveBackground = style.theme().background().selectedInactive();
		@JsonProperty Color selectionInactiveForeground = style.theme().foreground().base();
	}

	class MemoryIndicator {
		@JsonProperty Color allocatedBackground = style.theme().background().hover();
		@JsonProperty Color usedBackground = style.theme().background().selected();
	}

	class Menu {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color acceleratorForeground = style.theme().foreground().base();
		@JsonProperty Color acceleratorSelectionForeground = style.theme().foreground().base();
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color disabledBackground;
		@JsonProperty Color disabledForeground;
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color separatorColor = style.borderColor(); // * color works as supposed, this one changes color but not exactly as expected
	}

	class MenuBar {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color disabledBackground = style.theme().background().base();
		@JsonProperty Color disabledForeground = style.theme().foreground().disabled();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color highlight; // unknown effect
		@JsonProperty Color selectionBackground = style.theme().background().selected();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color shadow;
	}

	class MenuItem {
		@JsonProperty Color acceleratorForeground = style.theme().foreground().disabled();
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color disabledBackground = style.theme().background().base();
		@JsonProperty Color disabledForeground = style.theme().foreground().disabled();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color selectionBackground = style.theme().background().selected();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
	}

	class NewClass {
		@JsonProperty Integer separatorWidth;

		@JsonProperty Panel Panel = new Panel();
		@JsonProperty SearchField SearchField = new SearchField();

		class Panel {
			@JsonProperty Color background = style.theme().background().base();
		}

		class SearchField {
			@JsonProperty Color background = style.theme().background().input();
		}
	}

	class Notification {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color errorBackground = style.error();
		@JsonProperty Color errorBorderColor = style.borderColor();
		@JsonProperty Color errorForeground = style.theme().foreground().base();
		@JsonProperty Color foreground = style.theme().foreground().base();

		@JsonProperty MoreButton MoreButton = new MoreButton();
		@JsonProperty ToolWindow ToolWindow = new ToolWindow();

		class MoreButton {
			@JsonProperty Color background = Notification.this.background.darker();
			@JsonProperty Color foreground = style.theme().foreground().disabled();
			@JsonProperty Color innerBorderColor = background.darker(2);
		}

		class ToolWindow {
			@JsonProperty Color errorBackground = style.error().brightest();
			@JsonProperty Color errorBorderColor = errorBackground.darker();
			@JsonProperty Color errorForeground = style.theme().foreground().base();
			@JsonProperty Color informativeBackground = style.success().brightest();
			@JsonProperty Color informativeBorderColor = informativeBackground.darker();
			@JsonProperty Color informativeForeground = style.theme().foreground().base();
			@JsonProperty Color warningBackground = style.warning().brightest();
			@JsonProperty Color warningBorderColor = warningBackground.darker();
			@JsonProperty Color warningForeground = style.theme().foreground().base();
		}
	}

	class OptionPane {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color messageForeground = style.theme().foreground().base();
	}

	class Panel {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
	}

	class ParameterInfo {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color currentOverloadBackground = style.theme().background().selected();
		@JsonProperty Color currentParameterForeground = style.theme().foreground().base();
		@JsonProperty Color disabledForeground = style.theme().foreground().disabled();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color infoForeground = style.theme().foreground().info();
		@JsonProperty Color lineSeparatorColor = style.borderColor();
	}

	class Plugins {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color disabledForeground = style.theme().foreground().disabled();
		@JsonProperty Color hoverBackground = style.theme().background().hover();
		@JsonProperty Color lightSelectionBackground = style.theme().background().selected();
		@JsonProperty Color tagForeground = background;
		@JsonProperty Color tagBackground = palette.blue();
		@JsonProperty Color eapTagBackground = palette.red();

		@JsonProperty Button Button = new Button();
		@JsonProperty SearchField SearchField = new SearchField();
		@JsonProperty SectionHeader SectionHeader = new SectionHeader();
		@JsonProperty Tab Tab = new Tab();

		class Button {
			@JsonProperty Color installForeground = Plugins.this.background;
			@JsonProperty Color installBackground = palette.aqua();
			@JsonProperty Color installFillBackground = installBackground;
			@JsonProperty Color installFocusedBackground = palette.blue();
			@JsonProperty Color installBorderColor = installBackground;

			@JsonProperty Color updateForeground = installForeground;
			@JsonProperty Color updateBackground = installBackground;
			@JsonProperty Color updateBorderColor = installBorderColor;
		}

		class SearchField {
			@JsonProperty Color background = Plugins.this.background.brighter();
			@JsonProperty Color borderColor = style.borderColor();
		}

		class SectionHeader {
			@JsonProperty Color background = Plugins.this.background.darker();
			@JsonProperty Color foreground = style.theme().foreground().base();
		}

		class Tab {
			@JsonProperty Color selectedForeground; // unknown effect
			@JsonProperty Color hoverBackground; // unknown effect
			@JsonProperty Color selectedBackground; // unknown effect
		}
	}

	class Popup {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color inactiveBorderColor = style.borderColor();
		@JsonProperty Color innerBorderColor = style.borderColor();
		@JsonProperty boolean paintBorder; // on Mac
		@JsonProperty Color separatorColor = style.borderColor();
		@JsonProperty Color separatorForeground = style.theme().foreground().base();

		@JsonProperty Popup.Header Header = new Popup.Header();
		@JsonProperty Popup.Advertiser Advertiser = new Popup.Advertiser();
		@JsonProperty Popup.Toolbar Toolbar = new Popup.Toolbar();

		class Header {
			@JsonProperty Color inactiveBackground = style.theme().background().base().darker();
			@JsonProperty Color activeBackground = style.theme().background().base().darker(2);
		}

		class Advertiser {
			@JsonProperty Color background = style.theme().background().base();
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Integer borderInsets;
			@JsonProperty Color foreground = style.theme().foreground().base();
		}

		class Toolbar {
			@JsonProperty Color background = style.theme().background().base();
			@JsonProperty Color borderColor = style.borderColor();
		}
	}

	class PopupMenu {
		@JsonProperty Color background = style.theme().background().base(); // menu separators

		@JsonProperty Integer borderInsets;
		@JsonProperty Integer borderWidth = 0;
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color selectionBackground = style.theme().background().selected();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color translucentBackground = background.opacity(0.7);
	}

	class PopupMenuSeparator {
		@JsonProperty Integer height;
		@JsonProperty Integer stripeIndent;
		@JsonProperty Integer stripeWidth;
	}

	class ProgressBar {
		@JsonProperty Color background; // unknown effect
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color trackColor = style.theme().background().base().darker(2);
		@JsonProperty Color progressColor = trackColor.darker(3);
		@JsonProperty Color indeterminateStartColor = trackColor;
		@JsonProperty Color indeterminateEndColor = progressColor;
		@JsonProperty Color passedColor = style.success();
		@JsonProperty Color passedEndColor = trackColor;
		@JsonProperty Color failedColor = style.error();
		@JsonProperty Color failedEndColor = trackColor;
		@JsonProperty Color selectionBackground; // unknown effect
		@JsonProperty Color selectionForeground; // unknown effect
	}

	class RadioButton {
		@JsonProperty Color background = style.checkbox().textBackground();
		@JsonProperty Color foreground = style.checkbox().textForeground();
		@JsonProperty Color select; // unknown effect
		@JsonProperty Color disabledText = style.checkbox().disabledBorder();
	}

	class ScrollBar {
		@JsonProperty Color background = style.scrollbar().trackColor().opaque();
		@JsonProperty Color track;
		@JsonProperty Color trackColor = style.scrollbar().trackColor().opaque();
		@JsonProperty Color hoverTrackColor = trackColor;
		@JsonProperty Color trackHighlight;
		@JsonProperty Color thumb;
		@JsonProperty Color thumbColor = style.scrollbar().thumbColor();
		@JsonProperty Color thumbBorderColor = thumbColor;
		@JsonProperty Color hoverThumbColor = thumbColor;
		@JsonProperty Color hoverThumbBorderColor = thumbBorderColor;
		@JsonProperty Color thumbShadow;
		@JsonProperty Color thumbDarkShadow;
		@JsonProperty Color thumbHighlight;

		@JsonProperty ScrollBar.Mac Mac = new ScrollBar.Mac();
		@JsonProperty ScrollBar.Transparent Transparent = new ScrollBar.Transparent();

		class Mac {
			@JsonProperty Color hoverThumbBorderColor = ScrollBar.this.hoverThumbBorderColor;
			@JsonProperty Color hoverThumbColor = ScrollBar.this.hoverThumbColor;
			@JsonProperty Color hoverTrackColor = ScrollBar.this.hoverTrackColor;
			@JsonProperty Color thumbBorderColor = ScrollBar.this.thumbBorderColor;
			@JsonProperty Color thumbColor = ScrollBar.this.thumbColor;
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
			@JsonProperty Color background = style.theme().background().base();
			@JsonProperty Integer borderInsets;
			@JsonProperty Color foreground = style.theme().foreground().base();
		}

		class Header {
			@JsonProperty Color background = style.theme().background().base().darker();
		}

		class List {
			@JsonProperty Color separatorColor = style.borderColor();
			@JsonProperty Color separatorForeground = style.theme().foreground().base();
		}

		class SearchField {
			@JsonProperty Color background = style.theme().background().base();
			@JsonProperty Color borderColor = style.borderColor();
			@JsonProperty Color infoForeground = style.theme().foreground().info();
		}

		class Tab {
			@JsonProperty Color selectedBackground = style.theme().background().selected();
			@JsonProperty Color selectedForeground = style.theme().foreground().base();
		}
	}

	class SearchMatch {
		@JsonProperty Color startBackground = style.searchBackground();
		@JsonProperty Color endBackground = startBackground;
	}

	class Separator {
		@JsonProperty Color separatorColor = style.borderColor();
	}

	class Settings {
		@JsonProperty Spotlight Spotlight = new Spotlight();

		class Spotlight {
			@JsonProperty Color borderColor = style.searchBackground();
		}
	}

	class SidePanel {
		@JsonProperty Color background = style.theme().background().base().darker();
	}

	class Slider {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color trackColor = background.darker(2);
		@JsonProperty Color tickColor = trackColor.darker();
		@JsonProperty Color buttonColor = trackColor.darker();
		@JsonProperty Color buttonBorderColor = buttonColor.darker();
		@JsonProperty Color focus; // unknown effect
		@JsonProperty Color highlight; // unknown effect
		@JsonProperty Color shadow; // unknown effect
	}

	class SpeedSearch {
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color errorForeground = style.error();
		@JsonProperty Color background = style.theme().background().input();
		@JsonProperty Color borderColor = style.borderColor();
	}

	class StatusBar {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color hoverBackground = style.theme().background().hover();
	}

	class TabbedPane { // example: editor -> code style
		@JsonProperty Color background = style.tab().inBackground();
		@JsonProperty Color contentAreaColor = style.theme().background().base();
		@JsonProperty Color disabledForeground = style.theme().foreground().base();
		@JsonProperty Color focus = style.tab().selected();
		@JsonProperty Color focusColor = style.tab().selected();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color hoverColor = style.tab().hover();
		@JsonProperty int tabSelectionHeight = style.tab().settingsUnderlineHeight();
		@JsonProperty Color underlineColor = style.tab().underline();
		@JsonProperty Color disabledUnderlineColor = underlineColor;
	}

	class Table {
		@JsonProperty Color alternativeRowBackground = style.table().alternativeRowBackground();
		@JsonProperty Color background = style.table().background();
		@JsonProperty Color dropLineColor; // unknown effect
		@JsonProperty Color dropLineShortColor; // unknown effect
		@JsonProperty Color focusCellBackground = style.table().focusBackground();
		@JsonProperty Color focusCellForeground = style.theme().foreground().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color gridColor = style.table().grid();
		@JsonProperty Color hoverBackground = style.table().hoverBackground();
		@JsonProperty Color hoverInactiveBackground = hoverBackground;
		@JsonProperty Color lightSelectionBackground = style.table().selectionBackground();
		@JsonProperty Color lightSelectionForeground = style.theme().foreground().base();
		@JsonProperty Color lightSelectionInactiveBackground = style.table().selectionInactiveBackground();
		@JsonProperty Color lightSelectionInactiveForeground = style.theme().foreground().base();
		@JsonProperty Color selectionBackground = style.table().selectionBackground();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color selectionInactiveBackground = style.table().selectionInactiveBackground();
		@JsonProperty Color selectionInactiveForeground = style.theme().foreground().base();
		@JsonProperty Color sortIconColor; // unknown effect
		@JsonProperty Color stripeColor = style.table().stripe();
	}

	class TableHeader {
		@JsonProperty Color background = style.table().header().background();
		@JsonProperty Color bottomSeparatorColor = style.table().header().separator();
		@JsonProperty Color cellBorder = Color.transparent();
		@JsonProperty Color focusCellBackground = style.table().header().focusCellBackground();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color separatorColor = style.table().header().separator();
	}

	class TextArea {
		@JsonProperty Color background = style.scheme().background().base();
		@JsonProperty Color caretForeground = style.theme().foreground().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color inactiveBackground = style.scheme().background().base();
		@JsonProperty Color inactiveForeground = style.theme().foreground().base();
		@JsonProperty Color selectionBackground = style.scheme().background().selectedText();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
	}

	class TextField {
		@JsonProperty Color background = style.scheme().background().base();
		@JsonProperty Color caretForeground = style.theme().foreground().base();
		@JsonProperty Color darkShadow;
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color highlight; // unknown effect
		@JsonProperty Color inactiveForeground = style.theme().foreground().disabled();
		@JsonProperty Color selectionBackground = style.scheme().background().selectedText();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
	}

	class TextPane {
		@JsonProperty Color background = style.scheme().background().base();
		@JsonProperty Color caretForeground = style.theme().foreground().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color inactiveBackground = style.theme().background().readOnly();
		@JsonProperty Color inactiveForeground = style.theme().foreground().base();
		@JsonProperty Color selectionBackground = style.scheme().background().selectedLine();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
	}

	class ToolBar { // Find -> soft wrap preview floating icon
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color borderHandleColor; // unknown effect
		@JsonProperty Color darkShadow;
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color highlight; // unknown effect
		@JsonProperty Color light;
		@JsonProperty Color shadow;
		@JsonProperty Color floatingForeground = style.theme().foreground().base();
	}

	class ToolTip {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color borderColor;
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color infoForeground = style.theme().foreground().info();
		@JsonProperty Boolean paintBorder = false;
		@JsonProperty Color shortcutForeground = style.theme().foreground().disabled();

		@JsonProperty ToolTip.Actions Actions = new ToolTip.Actions();

		class Actions {
			@JsonProperty Color background = ToolTip.this.background.darker();
			@JsonProperty Color infoForeground = style.theme().foreground().info();
		}
	}

	class ToolWindow {
		@JsonProperty ToolWindow.Button Button = new ToolWindow.Button();
		@JsonProperty ToolWindow.Header Header = new ToolWindow.Header();
		@JsonProperty ToolWindow.HeaderCloseButton HeaderCloseButton = new ToolWindow.HeaderCloseButton();
		@JsonProperty ToolWindow.HeaderTab HeaderTab = new ToolWindow.HeaderTab();

		class Button {
			@JsonProperty Color hoverBackground = style.theme().background().hover();
			@JsonProperty Color selectedBackground = style.theme().background().selected();
			@JsonProperty Color selectedForeground = style.theme().foreground().base();
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

	class Tree { // settings tree
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color errorForeground = style.error();
		@JsonProperty Color foreground = style.theme().foreground().base();
		@JsonProperty Color hash = style.theme().background().base().darker(2);
		@JsonProperty Color hoverBackground = style.theme().background().hover();
		@JsonProperty Color hoverInactiveBackground = hoverBackground;
		@JsonProperty Color modifiedItemForeground = style.theme().foreground().modified();
		@JsonProperty Boolean paintLines;
		@JsonProperty Color rowHeight;
		@JsonProperty Color selectionBackground = style.theme().background().selected();
		@JsonProperty Color selectionForeground = style.theme().foreground().base();
		@JsonProperty Color selectionInactiveBackground = style.theme().background().selectedInactive();
	}

	class ValidationTooltip {
		@JsonProperty Color errorBackground = style.error();
		@JsonProperty Color errorBorderColor = errorBackground.darker();
		@JsonProperty Color warningBackground = style.warning();
		@JsonProperty Color warningBorderColor = warningBackground.darker();
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
				@JsonProperty Color currentBranchBackground = style.theme().background().base().darker();
				@JsonProperty Color hoveredBackground = currentBranchBackground.darker();
				@JsonProperty Color unmatchedForeground;
			}
		}

		class RefLabel {
			@JsonProperty Color backgroundBase = palette.blue();
			@JsonProperty double backgroundBrightness = 0.50;
			@JsonProperty Color foreground = style.theme().foreground().base();
		}
	}

	class Viewport {
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color foreground = style.theme().foreground().base();
	}

	class WelcomeScreen {
		@JsonProperty Color borderColor = style.borderColor();
		@JsonProperty Color background = style.theme().background().base();
		@JsonProperty Color captionBackground = palette.red();
		@JsonProperty Color captionForeground = style.theme().foreground().base();
		@JsonProperty Color footerBackground;
		@JsonProperty Color footerForeground = style.theme().foreground().base();
		@JsonProperty Color groupIconBorderColor = style.borderColor();
		@JsonProperty Color headerBackground;
		@JsonProperty Color headerForeground = style.theme().foreground().base();
		@JsonProperty Color separatorColor = style.borderColor();

		@JsonProperty WelcomeScreen.Details Details = new WelcomeScreen.Details();
		@JsonProperty WelcomeScreen.Projects Projects = new WelcomeScreen.Projects();
		@JsonProperty WelcomeScreen.SidePanel SidePanel = new WelcomeScreen.SidePanel();

		class Details {
			@JsonProperty Color background = style.theme().background().base();
		}

		class Projects {
			@JsonProperty Color background = style.theme().background().base();
			@JsonProperty Color selectionBackground = style.theme().background().selected();
			@JsonProperty Color selectionInactiveBackground = style.theme().background().selectedInactive();

			@JsonProperty WelcomeScreen.Projects.Actions actions = new WelcomeScreen.Projects.Actions();

			class Actions {
				@JsonProperty Color background = style.theme().background().base();
				@JsonProperty Color selectionBackground = style.theme().background().selected();
			}
		}

		class SidePanel {
			@JsonProperty Color background = style.theme().background().base().darker();
		}
	}
}
