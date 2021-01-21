package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

@JsonPropertyOrder({"*", "ActionButton", "Button", "ComboBox", "EditorTabs", "ToolWindow", "Table", "FileColor", "Link"})
class UI {
	private final Palette palette;
	private final Color background;
	private final Color foreground;

	UI(Palette palette, Color background, Color foreground) {
		this.palette = Objects.requireNonNull(palette);
		this.background = Objects.requireNonNull(background);
		this.foreground = Objects.requireNonNull(foreground);
	}

	@JsonProperty("*") public Asterisk asterisk() { return new Asterisk(); }
	@JsonProperty public ActionButton ActionButton() { return new ActionButton(); }
	@JsonProperty public Button Button() { return new Button(); }
	@JsonProperty public ComboBox ComboBox() { return new ComboBox(); }
	@JsonProperty public EditorTabs EditorTabs() { return new EditorTabs(); }
	@JsonProperty public ToolWindow ToolWindow() { return new ToolWindow(); }
	@JsonProperty public Table Table() { return new Table(); }
	@JsonProperty public FileColor FileColor() { return new FileColor(); }
	@JsonProperty public Link Link() { return new Link(); }

	class Asterisk {
		@JsonProperty Color background = UI.this.background;
		@JsonProperty Color foreground = UI.this.foreground;
		@JsonProperty Color infoForeground = foreground;
		@JsonProperty Color lightSelectionBackground = background.darker();
		@JsonProperty Color selectionBackground = background.darker(3);
		@JsonProperty Color selectionForeground = foreground;
		@JsonProperty Color selectionBackgroundInactive = background.darker();
		@JsonProperty Color selectionInactiveBackground = background.darker(2);
		@JsonProperty Color selectedInactiveBackground = background.darker();
		@JsonProperty Color selectedBackgroundInactive = background.darker();
		@JsonProperty Color selectedBackground = background.darker(2);
		@JsonProperty Color selectedForeground = foreground;
		@JsonProperty Color hoverBackground = background.darker();
		@JsonProperty Color borderColor = background.darker(2);
		@JsonProperty Color disabledBorderColor = background.darker();
		@JsonProperty Color separatorColor = background.darker(2);
	}

	class ComboBox {
		@JsonProperty Color nonEditableBackground = background;
		@JsonProperty ComboBox.ArrowButton ArrowButton = new ComboBox.ArrowButton();

		class ArrowButton {
			@JsonProperty Color iconColor = foreground;
			@JsonProperty Color disabledIconColor = foreground;
			@JsonProperty Color nonEditableBackground = background;
		}
	}

	class EditorTabs {
		@JsonProperty Color underlinedTabBackground = background;
		@JsonProperty Color underlineColor = palette.blue().darker();
	}

	class ToolWindow {
		@JsonProperty ToolWindow.Header Header = new ToolWindow.Header();
		@JsonProperty ToolWindow.HeaderTab HeaderTab = new ToolWindow.HeaderTab();

		class Header {
			@JsonProperty Color inactiveBackground = UI.this.background.darker();
			@JsonProperty Color background = inactiveBackground.darker(2);
		}

		class HeaderTab {
			@JsonProperty Color selectedInactiveBackground = background;
			@JsonProperty Color hoverInactiveBackground = background;
		}
	}

	class Table {
		@JsonProperty Color stripeColor = palette.sepia().brighter();
		@JsonProperty Color lightSelectionForeground = foreground;
		@JsonProperty Color lightSelectionInactiveForeground = palette.black().brighter().brighter().brighter();
		@JsonProperty Color lightSelectionBackground = palette.sepia().brighter();
		@JsonProperty Color lightSelectionInactiveBackground = background;
	}

	class FileColor {
		@JsonProperty Color Yellow = palette.yellow().brighter().transparent("10");
		@JsonProperty Color Green = palette.green().brighter().transparent("30");
		@JsonProperty Color Blue = palette.blue().brighter();
		@JsonProperty Color Violet = palette.purple().brighter();
		@JsonProperty Color Orange = palette.orange().brighter();
		@JsonProperty Color Rose = palette.red().brighter();
	}

	class Link {
		@JsonProperty Color activeForeground = palette.blue().darker();
		@JsonProperty Color hoverForeground = palette.blue().darker();
		@JsonProperty Color pressedForeground = palette.blue().darker();
		@JsonProperty Color visitedForeground = palette.blue().darker();
	}

	class ActionButton {
		@JsonProperty Color hoverBackground = palette.sepia().darker();
	}

	class Button {
		@JsonProperty Color startBackground = background;
		@JsonProperty Color endBackground = background;
		@JsonProperty Color startBorderColor = palette.sepia().darker();
		@JsonProperty Color endBorderColor = palette.sepia().darker();
		@JsonProperty("default") Button.Default defaultt = new Button.Default();

		class Default {
			@JsonProperty Color foreground = UI.this.foreground;
			@JsonProperty Color startBackground = palette.sepia().brighter();
			@JsonProperty Color endBackground = palette.sepia().brighter();
			@JsonProperty Color startBorderColor = palette.black().brighter().brighter();
			@JsonProperty Color endBorderColor = palette.black().brighter().brighter();
			@JsonProperty Color focusedBorderColor = background;
		}
	}
}
