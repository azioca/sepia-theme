package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.Objects;

class UI {
	private final Palette palette;
	private final Color background;
	private final Color schemeBackground;
	private final Color foreground;
	private final Color hoverBackground;
	private final Color selectedInactiveBackground;
	private final Color selectedBackground;
	private final Color nonProjectFilesBackground;
	private final Color testFilesBackground;

	UI(Palette palette, Color background, Color schemeBackground, Color foreground) {
		this.palette = Objects.requireNonNull(palette);
		this.background = Objects.requireNonNull(background);
		this.schemeBackground = Objects.requireNonNull(schemeBackground);
		this.foreground = Objects.requireNonNull(foreground);
		this.hoverBackground = this.background.darker();
		this.selectedInactiveBackground = hoverBackground.darker();
		this.selectedBackground = selectedInactiveBackground.darker();
		this.nonProjectFilesBackground = palette.yellow().brighter().transparent("10");
		this.testFilesBackground = palette.green().brighter().transparent("30");
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
	@JsonProperty public List List() { return new List(); }
	@JsonProperty public VersionControl VersionControl() { return new VersionControl(); }

	class Asterisk {
		@JsonProperty Color background = UI.this.background;

		@JsonProperty Color hoverBackground = UI.this.hoverBackground;

		@JsonProperty Color selectedBackgroundInactive = UI.this.selectedInactiveBackground;
		@JsonProperty Color selectionBackgroundInactive = UI.this.selectedInactiveBackground;
		@JsonProperty Color selectionInactiveBackground = UI.this.selectedInactiveBackground;
		@JsonProperty Color selectedInactiveBackground = UI.this.selectedInactiveBackground;

		@JsonProperty Color lightSelectionBackground = UI.this.selectedBackground;
		@JsonProperty Color selectedBackground = UI.this.selectedBackground;
		@JsonProperty Color selectionBackground = UI.this.selectedBackground;

		@JsonProperty Color foreground = UI.this.foreground;
		@JsonProperty Color selectionForeground = UI.this.foreground;
		@JsonProperty Color selectedForeground = UI.this.foreground;
		@JsonProperty Color infoForeground = UI.this.foreground;

		@JsonProperty Color borderColor = UI.this.background.darker(3);
		@JsonProperty Color disabledBorderColor = borderColor.brighter();
		@JsonProperty Color separatorColor = borderColor;
	}

	class ActionButton {
		@JsonProperty Color hoverBackground;
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

	class ComboBox {
		@JsonProperty Color selectionForeground = UI.this.foreground;
		@JsonProperty Color foreground = UI.this.foreground;
		@JsonProperty Color disabledForeground;
		@JsonProperty Color modifiedItemForeground;
		@JsonProperty Color background = UI.this.background.darker();
		@JsonProperty Color nonEditableBackground = background;
		@JsonProperty Color selectionBackground = UI.this.selectedBackground;

		@JsonProperty ComboBox.ArrowButton ArrowButton = new ComboBox.ArrowButton();

		class ArrowButton {
			@JsonProperty Color iconColor = UI.this.foreground;
			@JsonProperty Color disabledIconColor = iconColor.brighter();

			@JsonProperty Color background = ComboBox.this.background;
			@JsonProperty Color nonEditableBackground = ComboBox.this.nonEditableBackground;
		}
	}

	class EditorTabs {
		@JsonProperty Color underlinedTabForeground = UI.this.foreground;
		@JsonProperty Color underlinedTabBackground = UI.this.schemeBackground;
		@JsonProperty Color hoverBackground = underlinedTabBackground;
		@JsonProperty Color background = hoverBackground.darker(3);
		@JsonProperty Color borderColor = underlinedTabBackground;

		@JsonProperty int underlineHeight = 0;
		@JsonProperty Color underlineColor;
		@JsonProperty Color inactiveUnderlineColor;

		@JsonProperty boolean tabInsets;
		@JsonProperty Color inactiveColoredFileBackground;
	}

	class FileColor {
		@JsonProperty Color Yellow = nonProjectFilesBackground;
		@JsonProperty Color Green = testFilesBackground;
		@JsonProperty Color Blue = palette.blue().brighter();
		@JsonProperty Color Violet = palette.purple().brighter();
		@JsonProperty Color Orange = palette.orange().brighter();
		@JsonProperty Color Rose = palette.red().brighter();
	}

	class ToolWindow {
		@JsonProperty ToolWindow.Header Header = new ToolWindow.Header();
		@JsonProperty ToolWindow.HeaderTab HeaderTab = new ToolWindow.HeaderTab();

		class Header {
			@JsonProperty Color inactiveBackground = UI.this.selectedInactiveBackground;
			@JsonProperty Color background = UI.this.selectedBackground;
		}

		class HeaderTab {
			@JsonProperty Color hoverInactiveBackground = UI.this.hoverBackground;
			@JsonProperty Color selectedInactiveBackground = UI.this.selectedInactiveBackground;
		}
	}

	class Table {
		@JsonProperty Color stripeColor;
		@JsonProperty Color lightSelectionForeground = foreground;
		@JsonProperty Color lightSelectionInactiveForeground = foreground;
		@JsonProperty Color lightSelectionBackground = selectedBackground;
		@JsonProperty Color lightSelectionInactiveBackground = selectedInactiveBackground;
	}

	class Link {
		private final Color foreground = palette.green();
		@JsonProperty Color activeForeground = foreground;
		@JsonProperty Color hoverForeground = foreground;
		@JsonProperty Color pressedForeground = foreground;
		@JsonProperty Color visitedForeground = foreground;
	}

	class List {
		@JsonProperty Color background;
		@JsonProperty Color dropLineColor;
		@JsonProperty Color foreground;
		@JsonProperty Color hoverBackground;
		@JsonProperty Color hoverInactiveBackground;
		@JsonProperty Color selectionBackground;
		@JsonProperty Color selectionForeground;
		@JsonProperty Color selectionInactiveBackground;
		@JsonProperty Color selectionInactiveForeground;
	}

	class VersionControl {
		@JsonProperty("FileHistory.Commit.selectedBranchBackground") Color selectedBranchBackground = selectedBackground;

		@JsonProperty GitLog GitLog = new GitLog();
		@JsonProperty Log Log = new Log();
		@JsonProperty RefLabel RefLabel = new RefLabel();

		class GitLog {
			@JsonProperty Color headIconColor;
			@JsonProperty Color localBranchIconColor;
			@JsonProperty Color otherIconColor;
			@JsonProperty Color remoteBranchIconColor;
			@JsonProperty Color tagIconColor;
		}

		class Log {
			@JsonProperty Commit Commit = new Commit();

			class Commit {
				@JsonProperty Color currentBranchBackground = background.darker();
				@JsonProperty Color hoveredBackground = currentBranchBackground.darker();
				@JsonProperty Color unmatchedForeground;
			}
		}

		class RefLabel {
			@JsonProperty Color backgroundBase = palette.blue();
			@JsonProperty double backgroundBrightness = 0.50;
			@JsonProperty Color foreground = UI.this.foreground;
		}
	}
}
