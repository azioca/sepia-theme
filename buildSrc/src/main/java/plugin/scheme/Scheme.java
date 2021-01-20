package plugin.scheme;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import plugin.domain.Palette;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"metaInfo", "colors"})
@JacksonXmlRootElement(localName = "scheme")
public class Scheme {
	private final String name;
	private final Palette palette;
	private final plugin.domain.Color background;
	private final plugin.domain.Color foreground;

	public Scheme(String name, Palette palette, plugin.domain.Color background, plugin.domain.Color foreground) {
		this.name = Objects.requireNonNull(name);
		this.palette = new Palette.Plain(Objects.requireNonNull(palette));
		this.background = Objects.requireNonNull(background).plain();
		this.foreground = Objects.requireNonNull(foreground).plain();
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
		@JacksonXmlElementWrapper(useWrapping = false)
		List<Option.Color> option = List.of(
			new Option.Color("ADDED_LINES_COLOR", palette.green().brighter()),
			new Option.Color("ANNOTATIONS_COLOR", foreground),
			new Option.Color("CARET_COLOR", foreground),
			new Option.Color("CARET_ROW_COLOR", background.darker(2)),
			new Option.Color("CONSOLE_BACKGROUND_KEY", background),
			new Option.Color("DELETED_LINES_COLOR", palette.gray()),
			new Option.Color("DOCUMENTATION_COLOR", background),
			new Option.Color("GUTTER_BACKGROUND", background),
			new Option.Color("IGNORED_ADDED_LINES_BORDER_COLOR", palette.green().brighter()),
			new Option.Color("IGNORED_DELETED_LINES_BORDER_COLOR", palette.gray()),
			new Option.Color("IGNORED_MODIFIED_LINES_BORDER_COLOR", palette.blue().brighter()),
			new Option.Color("INDENT_GUIDE", background.darker(5)),
			new Option.Color("LINE_NUMBERS_COLOR", palette.gray()),
			new Option.Color("MODIFIED_LINES_COLOR", palette.blue().brighter()),
			new Option.Color("RIGHT_MARGIN_COLOR", background.darker().darker()),
			new Option.Color("SELECTED_INDENT_GUIDE", background.darker(5)),
			new Option.Color("SELECTED_TEARLINE_COLOR", palette.aqua().darker()),
			new Option.Color("SELECTION_BACKGROUND", background.darker(4)),
			new Option.Color("SELECTION_FOREGROUND", foreground),
			new Option.Color("TEARLINE_COLOR", background.darker(5)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_1", background),
			new Option.Color("VCS_ANNOTATIONS_COLOR_2", background.darker()),
			new Option.Color("VCS_ANNOTATIONS_COLOR_3", background.darker(2)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_4", background.darker(3)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_5", background.darker(4)),
			new Option.Color("WHITESPACES_MODIFIED_LINES_COLOR", background.darker(3))
		);
	}

	class Attributes {
		@JacksonXmlElementWrapper(useWrapping = false)
		List<Attribute> option = List.of(
			new Attribute("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES").foreground(palette.purple()),
			new Attribute("ANNOTATION_NAME_ATTRIBUTES").baseAttributes("DEFAULT_METADATA"),
			new Attribute("BAD_CHARACTER").effectColor(palette.red()).effectType(2),
			new Attribute("BOOKMARKS_ATTRIBUTES").errorStripeColor(palette.green().darker()),
			new Attribute("BREAKPOINT_ATTRIBUTES").background(background.darker(3)),
			new Attribute("Block comment").foreground(palette.gray()),

			new Attribute("CONSOLE_BLACK_OUTPUT").foreground(foreground),
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
			new Attribute("CONSOLE_NORMAL_OUTPUT").foreground(foreground),
			new Attribute("CONSOLE_RED_BRIGHT_OUTPUT").foreground(palette.red().brighter()),
			new Attribute("CONSOLE_RED_OUTPUT").foreground(palette.red()),
			new Attribute("CONSOLE_SYSTEM_OUTPUT").foreground(palette.yellow().darker()),
			new Attribute("CONSOLE_USER_INPUT").foreground(foreground),
			new Attribute("CONSOLE_WHITE_OUTPUT").foreground(foreground),
			new Attribute("CONSOLE_YELLOW_BRIGHT_OUTPUT").foreground(palette.yellow().brighter()),
			new Attribute("CONSOLE_YELLOW_OUTPUT").foreground(palette.yellow()),
			new Attribute("CTRL_CLICKABLE").foreground(palette.blue().darker()).effectColor(palette.blue().darker()).effectType(1),

			new Attribute("DEFAULT_ATTRIBUTE").foreground(palette.purple().brighter()),
			new Attribute("DEFAULT_BLOCK_COMMENT").foreground(palette.gray()).fontType(2),
			new Attribute("DEFAULT_BRACES"),
			new Attribute("DEFAULT_BRACKETS"),
			new Attribute("DEFAULT_CONSTANT").foreground(palette.purple().brighter()),
			new Attribute("DEFAULT_DOC_COMMENT").foreground(palette.gray()),
			new Attribute("DEFAULT_DOC_COMMENT_TAG").foreground(palette.gray()),
			new Attribute("DEFAULT_DOC_COMMENT_TAG_VALUE").foreground(palette.gray().darker(2)).fontType(2),
			new Attribute("DEFAULT_DOC_MARKUP").foreground(palette.gray().darker(2)),
			new Attribute("DEFAULT_DOT"),
			new Attribute("DEFAULT_ENTITY").foreground(palette.yellow()),
			new Attribute("DEFAULT_FUNCTION_DECLARATION").foreground(foreground),
			new Attribute("DEFAULT_GLOBAL_VARIABLE").foreground(palette.blue().darker()),
			new Attribute("DEFAULT_IDENTIFIER").foreground(foreground),
			new Attribute("DEFAULT_INSTANCE_FIELD").foreground(palette.purple().brighter()).fontType(1),
			new Attribute("DEFAULT_INTERFACE_NAME").foreground(foreground).fontType(2),
			new Attribute("DEFAULT_INVALID_STRING_ESCAPE").foreground(foreground).effectColor(palette.red()).effectType(2),
			new Attribute("DEFAULT_KEYWORD").foreground(palette.blue()).fontType(1),
			new Attribute("DEFAULT_LABEL").foreground(palette.blue()).fontType(1),
			new Attribute("DEFAULT_LABEL").foreground(palette.blue()).fontType(1),
			new Attribute("DEFAULT_LINE_COMMENT").foreground(palette.gray()).fontType(2),
			new Attribute("DEFAULT_LOCAL_VARIABLE").foreground(palette.purple()),
			new Attribute("DEFAULT_METADATA").foreground(palette.green()),
			new Attribute("DEFAULT_NUMBER").foreground(palette.blue()),
			new Attribute("DEFAULT_OPERATION_SIGN"),
			new Attribute("DEFAULT_PARAMETER").foreground(palette.purple()).fontType(1),
			new Attribute("DEFAULT_REASSIGNED_LOCAL_VARIABLE").foreground(palette.purple().brighter()).effectColor(palette.purple().brighter()).effectType(1),
			new Attribute("DEFAULT_REASSIGNED_PARAMETER").foreground(palette.purple()).fontType(1).effectColor(palette.purple()).effectType(1),
			new Attribute("DEFAULT_STATIC_FIELD").foreground(palette.purple().brighter()).fontType(3),
			new Attribute("DEFAULT_STATIC_METHOD").foreground(foreground).fontType(2),
			new Attribute("DEFAULT_STRING").foreground(palette.aqua()),
			new Attribute("DEFAULT_TAG").foreground(palette.blue().darker()),
			new Attribute("DEFAULT_TEMPLATE_LANGUAGE_COLOR").foreground(foreground.brighter(3)),
			new Attribute("DEFAULT_VALID_STRING_ESCAPE").foreground(palette.aqua()).fontType(1),
			new Attribute("DELETED_TEXT_ATTRIBUTES"),
			new Attribute("DEPRECATED_ATTRIBUTES").foreground(palette.red().darker()).fontType(2).effectType(1),
			new Attribute("DOC_COMMENT_TAG_VALUE").baseAttributes("DEFAULT_DOC_COMMENT_TAG_VALUE"),
			new Attribute("ERRORS_ATTRIBUTES").foreground(palette.red()).errorStripeColor(palette.red()).effectType(2),
			new Attribute("FOLDED_TEXT_ATTRIBUTES").foreground(foreground).background(palette.aqua().brighter()),
			new Attribute("FOLLOWED_HYPERLINK_ATTRIBUTES").foreground(palette.blue()).effectColor(palette.blue()).effectType(1),
			new Attribute("DELETED_TEXT_ATTRIBUTES").effectColor(palette.red()).errorStripeColor(palette.red()).effectType(5),
			new Attribute("IDENTIFIER_UNDER_CARET_ATTRIBUTES").foreground(foreground).background(palette.blue().brighter()).errorStripeColor(palette.blue().brighter()),
			new Attribute("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES").baseAttributes("CLASS_NAME_ATTRIBUTES"),
			new Attribute("INFO_ATTRIBUTES").effectColor(palette.yellow().darker()).errorStripeColor(palette.yellow().darker()).effectType(5),
			new Attribute("INJECTED_LANGUAGE_FRAGMENT").foreground(foreground.darker()),
			new Attribute("INLINE_PARAMETER_HINT").foreground(foreground).background(palette.aqua().darker()),
			new Attribute("INSTANCE_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_INSTANCE_FIELD"),

			new Attribute("LOG_ERROR_OUTPUT").foreground(palette.red().darker()),
			new Attribute("LOG_EXPIRED_ENTRY").foreground(palette.gray()),
			new Attribute("LOG_WARNING_OUTPUT").foreground(palette.yellow().brighter()),

			new Attribute("List/map to object conversion").baseAttributes("JAVA_NUMBER"),
			new Attribute("MARKED_FOR_REMOVAL_ATTRIBUTES").effectColor(palette.red().darker()).effectType(3),
			new Attribute("MATCHED_BRACE_ATTRIBUTES").foreground(foreground).fontType(1),
			new Attribute("NOT_USED_ELEMENT_ATTRIBUTES").foreground(palette.gray()).effectType(2),
			new Attribute("RUNTIME_ERROR").effectColor(palette.red().brighter()).errorStripeColor(palette.red().brighter()).effectType(2),
			new Attribute("SEARCH_RESULT_ATTRIBUTES").foreground(foreground).background(palette.blue().brighter()).errorStripeColor(palette.blue().brighter()),
			new Attribute("STATIC_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_STATIC_FIELD"),
			new Attribute("STATIC_FINAL_FIELD_ATTRIBUTES").baseAttributes("STATIC_FIELD_ATTRIBUTES"),
			new Attribute("TEMPLATE_VARIABLE_ATTRIBUTES").foreground(foreground.brighter(3)),
			new Attribute("TEXT").foreground(foreground).background(background),
			new Attribute("TEXT_SEARCH_RESULT_ATTRIBUTES").foreground(foreground).background(palette.blue().brighter()).errorStripeColor(palette.blue().brighter()),
			new Attribute("TODO_DEFAULT_ATTRIBUTES").foreground(palette.green().darker()).fontType(2).errorStripeColor(palette.green().darker()),
			new Attribute("TYPE_PARAMETER_NAME_ATTRIBUTES").baseAttributes("DEFAULT_PARAMETER"),
			new Attribute("TYPO").effectColor(palette.red().brighter()).effectType(2),
			new Attribute("UNMATCHED_BRACE_ATTRIBUTES").background(palette.red().brighter()).errorStripeColor(palette.red().brighter()),
			new Attribute("Unresolved reference access").baseAttributes("DEFAULT_IDENTIFIER"),
			new Attribute("WARNING_ATTRIBUTES").effectColor(palette.yellow().darker()).errorStripeColor(palette.yellow().darker()).effectType(2),
			new Attribute("WRITE_IDENTIFIER_UNDER_CARET_ATTRIBUTES").foreground(foreground).background(palette.aqua().brighter()).errorStripeColor(palette.aqua().brighter()),
			new Attribute("WRITE_SEARCH_RESULT_ATTRIBUTES").foreground(foreground).background(palette.aqua().brighter()).errorStripeColor(palette.aqua().brighter()),
			new Attribute("WRONG_REFERENCES_ATTRIBUTES").foreground(palette.red()).errorStripeColor(palette.red()).effectType(2)
		);
	}
}
