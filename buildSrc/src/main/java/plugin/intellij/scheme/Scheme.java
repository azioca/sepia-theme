package plugin.intellij.scheme;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.style.Style;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonPropertyOrder({"metaInfo", "colors", "attributes"})
@JacksonXmlRootElement(localName = "scheme")
public class Scheme {
	private final String name;
	private final Palette palette;
	private final Style style;

	public Scheme(String name, Palette palette, Style style) {
		this.name = Objects.requireNonNull(name);
		this.palette = Objects.requireNonNull(palette);
		this.style = Objects.requireNonNull(style);
	}

	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlProperty(isAttribute = true) String version() { return "142"; }
	@JacksonXmlProperty(isAttribute = true) String parent_scheme() { return "Default"; }
	@JacksonXmlProperty MetaInfo metaInfo() { return new MetaInfo(); }
	@JacksonXmlProperty Colors colors() {return new Colors(); }
	@JacksonXmlProperty Attributes attributes() {return new Attributes(); }

	class MetaInfo {
		@JacksonXmlElementWrapper(useWrapping = false)
		List<Property> property = List.of(
			new Property("ide", "Idea"),
			new Property("ideVersion", "2020.3.1.0.0"),
			new Property("originalScheme", name)
		);
	}

	class Colors {
		private final Color added = palette.green().brighter();
		private final Color modified = palette.blue().brighter();
		private final Color deleted = palette.gray();
		private final Color guidesAndLineNumbers = palette.gray().brighter(2);

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Option.Color> option = List.of(
			new Option.Color("CARET_COLOR", style.foreground()),
			new Option.Color("CARET_ROW_COLOR", style.background().editor().selectedLine()),
			new Option.Color("SELECTION_FOREGROUND", style.foreground()),
			new Option.Color("SELECTION_BACKGROUND", palette.blue().brighter()),
			new Option.Color("READONLY_BACKGROUND", style.background().readOnly()),

			new Option.Color("ADDED_LINES_COLOR", added),
			new Option.Color("IGNORED_ADDED_LINES_BORDER_COLOR", added),
			// new Option.Color("FILESTATUS_ADDED", added.darker()),
			// new Option.Color("FILESTATUS_COPIED", added.darker()),
			// new Option.Color("FILESTATUS_addedOutside", added.darker()),

			new Option.Color("MODIFIED_LINES_COLOR", modified),
			new Option.Color("IGNORED_MODIFIED_LINES_BORDER_COLOR", modified),
			new Option.Color("WHITESPACES_MODIFIED_LINES_COLOR", style.background().editor().selectedLine().darker()),
			// new Option.Color("FILESTATUS_MODIFIED", modified.darker()),
			// new Option.Color("FILESTATUS_NOT_CHANGED_IMMEDIATE", modified.darker()),
			// new Option.Color("FILESTATUS_NOT_CHANGED_RECURSIVE", modified.darker()),
			// new Option.Color("FILESTATUS_modifiedOutside", modified.darker()),

			new Option.Color("DELETED_LINES_COLOR", deleted),
			new Option.Color("IGNORED_DELETED_LINES_BORDER_COLOR", deleted),
			new Option.Color("FILESTATUS_DELETED", deleted),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_DELETED_FROM_FILE_SYSTEM", deleted),

			new Option.Color("ScrollBar.Transparent.trackColor", style.scrollbar().trackColor()),
			new Option.Color("ScrollBar.Transparent.thumbColor", style.scrollbar().thumbColor()),
			new Option.Color("ScrollBar.Transparent.thumbBorderColor", (style.scrollbar()).thumbBorderColor()),
			new Option.Color("ScrollBar.Transparent.hoverTrackColor", (style.scrollbar()).hoverTrackColor()),
			new Option.Color("ScrollBar.Transparent.hoverThumbColor", (style.scrollbar()).hoverThumbColor()),
			new Option.Color("ScrollBar.Transparent.hoverThumbBorderColor", (style.scrollbar()).hoverThumbBorderColor()),

			new Option.Color("ScrollBar.Mac.Transparent.trackColor", (style.scrollbar()).trackColor()),
			new Option.Color("ScrollBar.Mac.Transparent.thumbColor", (style.scrollbar()).thumbColor()),
			new Option.Color("ScrollBar.Mac.Transparent.thumbBorderColor", (style.scrollbar()).thumbBorderColor()),
			new Option.Color("ScrollBar.Mac.Transparent.hoverTrackColor", (style.scrollbar()).hoverTrackColor()),
			new Option.Color("ScrollBar.Mac.Transparent.hoverThumbColor", (style.scrollbar()).hoverThumbColor()),
			new Option.Color("ScrollBar.Mac.Transparent.hoverThumbBorderColor", (style.scrollbar()).hoverThumbBorderColor()),

			new Option.Color("ANNOTATIONS_COLOR", style.foreground()),
			new Option.Color("CONSOLE_BACKGROUND_KEY", style.background().editor().base()),
			new Option.Color("DOCUMENTATION_COLOR", style.background().editor().base()),
			new Option.Color("GUTTER_BACKGROUND", style.background().editor().base()),
			new Option.Color("INDENT_GUIDE", guidesAndLineNumbers),
			new Option.Color("LINE_NUMBERS_COLOR", guidesAndLineNumbers),
			new Option.Color("RIGHT_MARGIN_COLOR", style.background().editor().base().darker(2)),
			new Option.Color("SELECTED_INDENT_GUIDE", style.foreground()),
			new Option.Color("SELECTED_TEARLINE_COLOR", style.foreground()),
			new Option.Color("TEARLINE_COLOR", guidesAndLineNumbers),

			new Option.Color("VCS_ANNOTATIONS_COLOR_1", style.background().editor().base()),
			new Option.Color("VCS_ANNOTATIONS_COLOR_2", style.background().editor().base().darker()),
			new Option.Color("VCS_ANNOTATIONS_COLOR_3", style.background().editor().base().darker(2)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_4", style.background().editor().base().darker(3)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_5", style.background().editor().base().darker(4))
		).stream().sorted(Comparator.comparing(Option.Color::name)).collect(Collectors.toList());
	}

	class Attributes {
		private final Color deprecated = style.foreground().brighter(3);

		private final Color underCaretBackground = style.background().editor().selectedLine().darker();
		private final Color underCaretWriteBackground = underCaretBackground.darker(2);

		private final Color searchBackground = palette.aqua().brighter();
		private final Color searchWriteBackground = searchBackground.darker();

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Attribute> option = List.of(
			new Attribute("TEXT").foreground(style.foreground()).background(style.background().editor().base()),

			new Attribute("IDENTIFIER_UNDER_CARET_ATTRIBUTES").foreground(style.foreground()).background(underCaretBackground).errorStripeColorAsBackground(),
			new Attribute("WRITE_IDENTIFIER_UNDER_CARET_ATTRIBUTES").foreground(style.foreground()).background(underCaretWriteBackground).errorStripeColorAsBackground(),
			new Attribute("TEXT_SEARCH_RESULT_ATTRIBUTES").foreground(style.foreground()).background(searchBackground).errorStripeColorAsBackground(),
			new Attribute("SEARCH_RESULT_ATTRIBUTES").foreground(style.foreground()).background(searchBackground).errorStripeColorAsBackground(),
			new Attribute("WRITE_SEARCH_RESULT_ATTRIBUTES").foreground(style.foreground()).background(searchWriteBackground).errorStripeColorAsBackground(),

			new Attribute("MATCHED_BRACE_ATTRIBUTES").foreground(style.foreground()).bold(),
			new Attribute("UNMATCHED_BRACE_ATTRIBUTES").background(palette.red().brighter()).errorStripeColorAsBackground(),

			new Attribute("CTRL_CLICKABLE").foreground(palette.blue().darker()).underscored(palette.blue().darker()),

			new Attribute("DEFAULT_ATTRIBUTE").foreground(palette.purple().darker()),
			new Attribute("DEFAULT_BLOCK_COMMENT").foreground(palette.gray()).italic(),
			new Attribute("DEFAULT_BRACES"),
			new Attribute("DEFAULT_BRACKETS"),
			new Attribute("DEFAULT_CONSTANT").foreground(palette.purple().brighter()),
			new Attribute("DEFAULT_DOC_COMMENT").foreground(palette.gray()),
			new Attribute("DEFAULT_DOC_COMMENT_TAG").foreground(palette.gray()),
			new Attribute("DEFAULT_DOC_COMMENT_TAG_VALUE").foreground(palette.gray().darker(2)).italic(),
			new Attribute("DEFAULT_DOC_MARKUP").foreground(palette.gray().darker(2)),
			new Attribute("DEFAULT_DOT"),
			new Attribute("DEFAULT_ENTITY").foreground(palette.yellow()),
			new Attribute("DEFAULT_FUNCTION_DECLARATION").foreground(style.foreground()),
			new Attribute("DEFAULT_GLOBAL_VARIABLE").foreground(palette.blue().darker()),
			new Attribute("DEFAULT_IDENTIFIER").foreground(style.foreground()),
			new Attribute("DEFAULT_INSTANCE_FIELD").foreground(palette.purple().darker()).bold(),
			new Attribute("DEFAULT_INTERFACE_NAME").foreground(style.foreground()).italic(),
			new Attribute("DEFAULT_INVALID_STRING_ESCAPE").foreground(style.foreground()).underwaved(palette.red()),
			new Attribute("DEFAULT_KEYWORD").foreground(palette.blue()).bold(),
			new Attribute("DEFAULT_LABEL").foreground(palette.blue()).bold(),
			new Attribute("DEFAULT_LINE_COMMENT").foreground(palette.gray()).italic(),
			new Attribute("DEFAULT_LOCAL_VARIABLE").foreground(palette.purple()),
			new Attribute("DEFAULT_METADATA").foreground(palette.green()),
			new Attribute("DEFAULT_NUMBER").foreground(palette.blue()).bold(),
			new Attribute("DEFAULT_OPERATION_SIGN"),
			new Attribute("DEFAULT_PARAMETER").foreground(palette.purple()).bold(),
			new Attribute("DEFAULT_REASSIGNED_LOCAL_VARIABLE").foreground(palette.purple()),
			new Attribute("DEFAULT_REASSIGNED_PARAMETER").foreground(palette.purple()).bold(),
			new Attribute("DEFAULT_STATIC_FIELD").foreground(palette.purple().darker()).bold().italic(),
			new Attribute("DEFAULT_STATIC_METHOD").foreground(style.foreground()).italic(),
			new Attribute("DEFAULT_STRING").foreground(palette.aqua()),
			new Attribute("DEFAULT_TAG").foreground(palette.blue().darker()),
			new Attribute("DEFAULT_TEMPLATE_LANGUAGE_COLOR").foreground(style.foreground().brighter(3)),
			new Attribute("DEFAULT_VALID_STRING_ESCAPE").foreground(palette.aqua()).bold(),
			new Attribute("TYPE_PARAMETER_NAME_ATTRIBUTES").foreground(style.foreground()).bold(),
			new Attribute("DEPRECATED_ATTRIBUTES").foreground(deprecated).italic().strikeout(deprecated),
			new Attribute("DOC_COMMENT_TAG_VALUE").baseAttributes("DEFAULT_DOC_COMMENT_TAG_VALUE"),
			new Attribute("FOLLOWED_HYPERLINK_ATTRIBUTES").foreground(style.link()).boldUnderscored(style.link()),
			new Attribute("DELETED_TEXT_ATTRIBUTES").errorStripeColor(palette.red()).dottedLine(palette.red()),
			new Attribute("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES").baseAttributes("CLASS_NAME_ATTRIBUTES"),
			new Attribute("INSTANCE_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_INSTANCE_FIELD"),
			new Attribute("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES").foreground(palette.purple()),
			new Attribute("ANNOTATION_NAME_ATTRIBUTES").baseAttributes("DEFAULT_METADATA"),
			new Attribute("STATIC_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_STATIC_FIELD"),
			new Attribute("STATIC_FINAL_FIELD_ATTRIBUTES").baseAttributes("STATIC_FIELD_ATTRIBUTES"),
			new Attribute("TEMPLATE_VARIABLE_ATTRIBUTES").foreground(style.foreground().brighter(3)),

			new Attribute("FOLDED_TEXT_ATTRIBUTES").foreground(style.foreground()).background(palette.aqua().brighter()),
			new Attribute("Block comment").foreground(palette.gray()),
			new Attribute("TODO_DEFAULT_ATTRIBUTES").foreground(palette.green().darker()).italic().errorStripeColorAsForeground(),

			new Attribute("INJECTED_LANGUAGE_FRAGMENT").foreground(style.foreground().darker()),
			new Attribute("INLINE_PARAMETER_HINT").foreground(style.foreground()).background(palette.aqua().darker()),
			new Attribute("INFO_ATTRIBUTES").errorStripeColor(palette.yellow().darker()).dottedLine(palette.yellow().darker()),

			new Attribute("ERRORS_ATTRIBUTES").underwaved(palette.red().darker()).errorStripeColorAsEffect(),
			new Attribute("WARNING_ATTRIBUTES").underwaved(palette.yellow().brighter()).errorStripeColorAsEffect(),
			new Attribute("BAD_CHARACTER").underwaved(palette.red().darker()),
			new Attribute("WRONG_REFERENCES_ATTRIBUTES").underwaved(palette.red()).errorStripeColorAsEffect(),
			new Attribute("Unresolved reference access").baseAttributes("DEFAULT_IDENTIFIER"),
			new Attribute("NOT_USED_ELEMENT_ATTRIBUTES").foreground(palette.gray()),
			new Attribute("RUNTIME_ERROR").underwaved(palette.red().brighter()).errorStripeColorAsEffect(),
			new Attribute("TYPO").underwaved(palette.gray().brighter()),
			new Attribute("MARKED_FOR_REMOVAL_ATTRIBUTES").foreground(deprecated).italic().strikeout(deprecated),

			new Attribute("BOOKMARKS_ATTRIBUTES").errorStripeColor(style.foreground()),
			new Attribute("BREAKPOINT_ATTRIBUTES").background(style.background().editor().base().darker(3)),

			new Attribute("List/map to object conversion").baseAttributes("JAVA_NUMBER"),

			new Attribute("LOG_ERROR_OUTPUT").foreground(palette.red().darker()),
			new Attribute("LOG_EXPIRED_ENTRY").foreground(palette.gray()),
			new Attribute("LOG_WARNING_OUTPUT").foreground(palette.yellow().brighter()),

			new Attribute("CONSOLE_BLACK_OUTPUT").foreground(style.foreground()),
			new Attribute("CONSOLE_BLUE_BRIGHT_OUTPUT").foreground(palette.blue().brighter()),
			new Attribute("CONSOLE_BLUE_OUTPUT").foreground(palette.blue()),
			new Attribute("CONSOLE_CYAN_BRIGHT_OUTPUT").foreground(palette.aqua().brighter()),
			new Attribute("CONSOLE_CYAN_OUTPUT").foreground(palette.aqua()),
			new Attribute("CONSOLE_DARKGRAY_OUTPUT").foreground(palette.gray().darker()),
			new Attribute("CONSOLE_ERROR_OUTPUT").foreground(palette.red().darker()),
			new Attribute("CONSOLE_GRAY_OUTPUT").foreground(palette.gray()),
			new Attribute("CONSOLE_GREEN_BRIGHT_OUTPUT").foreground(palette.green().brighter()),
			new Attribute("CONSOLE_GREEN_OUTPUT").foreground(palette.green()),
			new Attribute("CONSOLE_MAGENTA_BRIGHT_OUTPUT").foreground(palette.purple().brighter()),
			new Attribute("CONSOLE_MAGENTA_OUTPUT").foreground(palette.purple()),
			new Attribute("CONSOLE_NORMAL_OUTPUT").foreground(style.foreground()),
			new Attribute("CONSOLE_RED_BRIGHT_OUTPUT").foreground(palette.red().brighter()),
			new Attribute("CONSOLE_RED_OUTPUT").foreground(palette.red()),
			new Attribute("CONSOLE_SYSTEM_OUTPUT").foreground(palette.yellow().darker()),
			new Attribute("CONSOLE_USER_INPUT").foreground(style.foreground()),
			new Attribute("CONSOLE_WHITE_OUTPUT").foreground(style.foreground()),
			new Attribute("CONSOLE_YELLOW_BRIGHT_OUTPUT").foreground(palette.yellow().brighter()),
			new Attribute("CONSOLE_YELLOW_OUTPUT").foreground(palette.yellow())
		).stream().sorted(Comparator.comparing(Attribute::name)).collect(Collectors.toList());
	}
}
