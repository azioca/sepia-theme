package plugin.intellij.scheme;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import plugin.domain.Palette;
import plugin.domain.color.Color;
import plugin.style.Style;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

	private static <T> Collection<T> merge(Collection<T>... collections) {
		Collection<T> merged = new ArrayList<>();
		Arrays.stream(collections).forEach(merged::addAll);
		return merged;
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
		private final Color added = palette.green().brighter(2);
		private final Color modified = palette.blue().brighter(2);
		private final Color deleted = palette.silver();

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Option.Color> option = List.of(
			new Option.Color("CARET_COLOR", style.scheme().foreground().base()),
			new Option.Color("CARET_ROW_COLOR", style.scheme().background().selectedLine()),
			new Option.Color("SELECTION_FOREGROUND", style.scheme().foreground().selectedText()),
			new Option.Color("SELECTION_BACKGROUND", style.scheme().background().selectedText()),
			new Option.Color("READONLY_BACKGROUND", style.scheme().background().readOnly()),

			new Option.Color("ADDED_LINES_COLOR", added),
			new Option.Color("IGNORED_ADDED_LINES_BORDER_COLOR", added),
			// new Option.Color("FILESTATUS_ADDED", added.darker()),
			// new Option.Color("FILESTATUS_COPIED", added.darker()),
			// new Option.Color("FILESTATUS_addedOutside", added.darker()),

			new Option.Color("MODIFIED_LINES_COLOR", modified),
			new Option.Color("IGNORED_MODIFIED_LINES_BORDER_COLOR", modified),
			new Option.Color("WHITESPACES_MODIFIED_LINES_COLOR", style.scheme().background().selectedLine().darker()),
			// new Option.Color("FILESTATUS_MODIFIED", modified.darker()),
			// new Option.Color("FILESTATUS_NOT_CHANGED_IMMEDIATE", modified.darker()),
			// new Option.Color("FILESTATUS_NOT_CHANGED_RECURSIVE", modified.darker()),
			// new Option.Color("FILESTATUS_modifiedOutside", modified.darker()),

			new Option.Color("FOLDED_TEXT_BORDER_COLOR", palette.yellow().brighter()),

			new Option.Color("DELETED_LINES_COLOR", deleted),
			new Option.Color("IGNORED_DELETED_LINES_BORDER_COLOR", deleted),
			new Option.Color("FILESTATUS_DELETED", deleted),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_DELETED_FROM_FILE_SYSTEM", deleted),

			new Option.Color("DIFF_SEPARATORS_BACKGROUND", style.scheme().background().readOnly().darker(2)),

			new Option.Color("ScrollBar.Transparent.trackColor", style.scrollBar().trackColor()),
			new Option.Color("ScrollBar.Transparent.thumbColor", style.scrollBar().thumbColor()),
			new Option.Color("ScrollBar.Transparent.thumbBorderColor", style.scrollBar().thumbBorderColor()),
			new Option.Color("ScrollBar.Transparent.hoverTrackColor", style.scrollBar().hoverTrackColor()),
			new Option.Color("ScrollBar.Transparent.hoverThumbColor", style.scrollBar().hoverThumbColor()),
			new Option.Color("ScrollBar.Transparent.hoverThumbBorderColor", style.scrollBar().hoverThumbBorderColor()),

			new Option.Color("ScrollBar.Mac.Transparent.trackColor", style.scrollBar().trackColor()),
			new Option.Color("ScrollBar.Mac.Transparent.thumbColor", style.scrollBar().thumbColor()),
			new Option.Color("ScrollBar.Mac.Transparent.thumbBorderColor", style.scrollBar().thumbBorderColor()),
			new Option.Color("ScrollBar.Mac.Transparent.hoverTrackColor", style.scrollBar().hoverTrackColor()),
			new Option.Color("ScrollBar.Mac.Transparent.hoverThumbColor", style.scrollBar().hoverThumbColor()),
			new Option.Color("ScrollBar.Mac.Transparent.hoverThumbBorderColor", style.scrollBar().hoverThumbBorderColor()),

			new Option.Color("SELECTED_INDENT_GUIDE", style.scheme().foreground().base()),
			new Option.Color("SELECTED_TEARLINE_COLOR", style.scheme().foreground().base()),
			new Option.Color("INDENT_GUIDE", style.scheme().lines()),
			new Option.Color("RIGHT_MARGIN_COLOR", style.scheme().lines()),
			new Option.Color("TEARLINE_COLOR", style.scheme().lines().darker()),
			new Option.Color("LINE_NUMBERS_COLOR", style.scheme().lines().darker(2)),

			// General -> Popups and Hints
			new Option.Color("DOCUMENTATION_COLOR", style.scheme().background().documentation()),
			new Option.Color("ERROR_HINT", style.scheme().background().hints()),
			new Option.Color("INFORMATION_HINT", style.scheme().background().hints()),
			new Option.Color("PROMOTION_PANE", style.scheme().background().hints()),
			new Option.Color("QUESTION_HINT", style.scheme().background().hints()),
			new Option.Color("RECENT_LOCATIONS_SELECTION", style.scheme().background().base().darker()),
			new Option.Color("TOOLTIP", style.scheme().background().hints()),

			new Option.Color("ANNOTATIONS_COLOR", style.scheme().foreground().base()),
			new Option.Color("CONSOLE_BACKGROUND_KEY", style.scheme().background().base()),
			new Option.Color("GUTTER_BACKGROUND", style.scheme().background().base()),

			new Option.Color("VCS_ANNOTATIONS_COLOR_1", style.scheme().background().base()),
			new Option.Color("VCS_ANNOTATIONS_COLOR_2", style.scheme().background().base().darker()),
			new Option.Color("VCS_ANNOTATIONS_COLOR_3", style.scheme().background().base().darker(2)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_4", style.scheme().background().base().darker(3)),
			new Option.Color("VCS_ANNOTATIONS_COLOR_5", style.scheme().background().base().darker(4))
		).stream().sorted(Comparator.comparing(Option.Color::name)).collect(Collectors.toList());
	}

	class Attributes {
		private final Color deprecated = style.scheme().foreground().base().brighter(3);

		private final Color searchBackground = style.theme().background().search();
		private final Color searchWriteBackground = searchBackground.darker();

		@JacksonXmlElementWrapper(useWrapping = false)
		public Collection<Attribute> option() {
			return merge(general(), languageDefaults(), consoleColors(), diffAndMerge(), java(), go()).stream().sorted(Comparator.comparing(Attribute::name)).collect(Collectors.toList());
		}

		// todo convert to objects with colors() and attributes()
		private Collection<Attribute> general() {
			return merge(code(), editor(), errorsAndWarnings(), searchResults(), text());
		}

		private Collection<Attribute> code() {
			return Set.of(
				new Attribute("IDENTIFIER_UNDER_CARET_ATTRIBUTES")
					.background(style.scheme().background().underCaret())
					.errorStripe(style.scheme().background().underCaret()),
				new Attribute("WRITE_IDENTIFIER_UNDER_CARET_ATTRIBUTES")
					.background(style.scheme().background().underCaretWrite())
					.errorStripe(style.scheme().background().underCaretWrite().darker()),
				new Attribute("INJECTED_LANGUAGE_FRAGMENT")
					.foreground(style.scheme().foreground().base().darker()),
				new Attribute("TODO_DEFAULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(palette.orange().brighter())
					.errorStripeAsBackground(),
				new Attribute("MATCHED_BRACE_ATTRIBUTES").foreground(style.scheme().foreground().base()).bold(),
				new Attribute("UNMATCHED_BRACE_ATTRIBUTES").background(style.error()).errorStripeAsBackground(),

				new Attribute("LIVE_TEMPLATE_ATTRIBUTES").bordered(palette.red().brighter()),
				new Attribute("LIVE_TEMPLATE_INACTIVE_SEGMENT").foreground(style.scheme().foreground().disabled()),
				new Attribute("TEMPLATE_VARIABLE_ATTRIBUTES").foreground(palette.purple()),

				new Attribute("CTRL_CLICKABLE").foreground(style.link()).underscored(style.link())
			);
		}

		private Collection<Attribute> editor() {
			return merge(
				Set.of(
					new Attribute("BOOKMARKS_ATTRIBUTES").errorStripe(style.scheme().foreground().base()),
					new Attribute("DEFAULT_ATTRIBUTE").foreground(palette.purple().darker()),
					new Attribute("DEFAULT_BRACES").foreground(style.scheme().foreground().base()),
					new Attribute("DEFAULT_BRACKETS").foreground(style.scheme().foreground().base()),
					new Attribute("DEFAULT_OPERATION_SIGN").foreground(style.scheme().foreground().base()),
					new Attribute("DEFAULT_DOT").foreground(style.scheme().foreground().base()),
					new Attribute("INLINE_PARAMETER_HINT").foreground(style.scheme().foreground().base()).background(palette.aqua().darker()),
					new Attribute("INFO_ATTRIBUTES").dottedLine(style.warning()).errorStripeAsEffect(),
					new Attribute("BREAKPOINT_ATTRIBUTES").background(style.scheme().background().base().darker(3)),
					new Attribute("FOLLOWED_HYPERLINK_ATTRIBUTES").foreground(style.link()).boldUnderscored(style.link())
				),
				breadcrumbs()
			);
		}

		private Collection<Attribute> breadcrumbs() {
			return Set.of(
				new Attribute("BREADCRUMBS_DEFAULT")
					.foreground(style.scheme().foreground().base())
					.background(style.scheme().background().base()),
				new Attribute("BREADCRUMBS_HOVERED")
					.foreground(style.scheme().foreground().base())
					.background(style.scheme().background().base().darker(2)),
				new Attribute("BREADCRUMBS_CURRENT")
					.foreground(style.scheme().foreground().base())
					.background(style.scheme().background().base().darker(4)),
				new Attribute("BREADCRUMBS_INACTIVE")
					.foreground(style.scheme().foreground().base()) // unknown effect
			);
		}

		private Collection<Attribute> errorsAndWarnings() {
			return Set.of(
				new Attribute("BAD_CHARACTER").underwaved(style.error()),
				new Attribute("ERRORS_ATTRIBUTES")
					.underwaved(style.error())
					.errorStripeAsEffect(),
				new Attribute("RUNTIME_ERROR")
					.underwaved(style.error())
					.errorStripeAsEffect(),
				new Attribute("TYPO").underwaved(palette.gray().brighter()),
				new Attribute("NOT_USED_ELEMENT_ATTRIBUTES").foreground(palette.gray()),
				new Attribute("Unresolved reference access").baseAttributes("DEFAULT_IDENTIFIER"),
				new Attribute("MARKED_FOR_REMOVAL_ATTRIBUTES")
					.foreground(deprecated).italic()
					.strikeout(deprecated),
				new Attribute("WARNING_ATTRIBUTES")
					.underwaved(palette.orange().brighter())
					.errorStripe(style.warning()),
				new Attribute("WRONG_REFERENCES_ATTRIBUTES")
					.underwaved(style.error())
					.errorStripeAsEffect()
			);
		}

		private Collection<Attribute> searchResults() {
			return Set.of(
				new Attribute("SEARCH_RESULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(searchBackground).errorStripeAsBackground(),
				new Attribute("WRITE_SEARCH_RESULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(searchWriteBackground).errorStripeAsBackground(),
				new Attribute("TEXT_SEARCH_RESULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(searchBackground).errorStripeAsBackground()
			);
		}

		private Collection<Attribute> text() {
			return Set.of(
				new Attribute("TEXT")
					.foreground(style.scheme().foreground().base())
					.background(style.scheme().background().base()),
				new Attribute("DELETED_TEXT_ATTRIBUTES")
					.errorStripe(style.error())
					.dottedLine(style.error()),
				new Attribute("FOLDED_TEXT_ATTRIBUTES")
					.foreground(palette.gray())
					.bordered(palette.gray().brighter())
			);
		}

		private Collection<Attribute> languageDefaults() {
			// todo create value instead of empty Attribute and then setValue()
			Attribute.Value keyword = new Attribute("").foreground(palette.blue()).bold().value();

			return Set.of(
				new Attribute("DEFAULT_STRING")
					.foreground(palette.aqua())
					.bold(),
				new Attribute("DEFAULT_VALID_STRING_ESCAPE")
					.foreground(palette.aqua().darker())
					.bold(),
				new Attribute("DEFAULT_INVALID_STRING_ESCAPE")
					.foreground(style.error())
					.underwaved(style.error())
					.bold(),
				new Attribute("DEFAULT_CONSTANT").foreground(palette.purple()),

				new Attribute("DEFAULT_LINE_COMMENT")
					.foreground(palette.gray())
					.italic(),
				new Attribute("DEFAULT_BLOCK_COMMENT")
					.foreground(palette.gray())
					.italic(),
				new Attribute("DEFAULT_DOC_COMMENT")
					.foreground(palette.gray())
					.italic(),
				new Attribute("DEFAULT_DOC_COMMENT_TAG")
					.foreground(palette.gray())
					.italic()
					.bold(),
				new Attribute("DEFAULT_DOC_COMMENT_TAG_VALUE")
					.foreground(palette.gray().darker(2))
					.bold(),
				new Attribute("DEFAULT_DOC_MARKUP").setValue(keyword).removeFontType(),

				new Attribute("DEFAULT_ENTITY").foreground(palette.yellow()),
				new Attribute("DEFAULT_FUNCTION_DECLARATION").foreground(style.scheme().foreground().base()),
				new Attribute("DEFAULT_GLOBAL_VARIABLE").foreground(palette.blue().darker()),
				new Attribute("DEFAULT_IDENTIFIER").foreground(style.scheme().foreground().base()),
				new Attribute("DEFAULT_INSTANCE_FIELD")
					.foreground(palette.purple().darker())
					.bold(),
				new Attribute("DEFAULT_INTERFACE_NAME")
					.foreground(style.scheme().foreground().base())
					.italic(),
				new Attribute("DEFAULT_KEYWORD").setValue(keyword),
				new Attribute("DEFAULT_LABEL")
					.foreground(palette.blue())
					.bold(),
				new Attribute("DEFAULT_LOCAL_VARIABLE").foreground(palette.purple()),
				new Attribute("DEFAULT_METADATA").foreground(palette.green()),
				new Attribute("DEFAULT_NUMBER")
					.foreground(palette.blue())
					.bold(),
				new Attribute("DEFAULT_PARAMETER")
					.foreground(palette.purple())
					.bold(),
				new Attribute("DEFAULT_REASSIGNED_LOCAL_VARIABLE").foreground(palette.purple()),
				new Attribute("DEFAULT_REASSIGNED_PARAMETER")
					.foreground(palette.purple())
					.bold(),
				new Attribute("DEFAULT_STATIC_FIELD")
					.foreground(palette.purple().darker())
					.bold()
					.italic(),
				new Attribute("DEFAULT_STATIC_METHOD")
					.foreground(style.scheme().foreground().base())
					.italic(),
				new Attribute("DEFAULT_TAG").foreground(palette.blue().darker()),
				new Attribute("DEFAULT_TEMPLATE_LANGUAGE_COLOR").foreground(style.scheme().foreground().base().brighter(3))
			);
		}

		private Collection<Attribute> consoleColors() {
			return merge(ANSIColors(), console(), logConsole());
		}

		private Collection<Attribute> ANSIColors() {
			return Set.of(
				new Attribute("CONSOLE_BLACK_OUTPUT").foreground(style.scheme().foreground().base()),
				new Attribute("CONSOLE_BLUE_BRIGHT_OUTPUT").foreground(palette.blue().brighter()),
				new Attribute("CONSOLE_BLUE_OUTPUT").foreground(palette.blue()),
				new Attribute("CONSOLE_CYAN_BRIGHT_OUTPUT").foreground(palette.aqua().brighter()),
				new Attribute("CONSOLE_CYAN_OUTPUT").foreground(palette.aqua()),
				new Attribute("CONSOLE_DARKGRAY_OUTPUT").foreground(palette.gray().darker()),
				new Attribute("CONSOLE_GRAY_OUTPUT").foreground(palette.gray()),
				new Attribute("CONSOLE_GREEN_BRIGHT_OUTPUT").foreground(palette.green().brighter()),
				new Attribute("CONSOLE_GREEN_OUTPUT").foreground(palette.green()),
				new Attribute("CONSOLE_MAGENTA_BRIGHT_OUTPUT").foreground(palette.purple().brighter()),
				new Attribute("CONSOLE_MAGENTA_OUTPUT").foreground(palette.purple()),
				new Attribute("CONSOLE_RED_BRIGHT_OUTPUT").foreground(palette.red().brighter()),
				new Attribute("CONSOLE_RED_OUTPUT").foreground(palette.red()),
				new Attribute("CONSOLE_WHITE_OUTPUT").foreground(style.scheme().foreground().base()),
				new Attribute("CONSOLE_YELLOW_BRIGHT_OUTPUT").foreground(palette.yellow().brighter()),
				new Attribute("CONSOLE_YELLOW_OUTPUT").foreground(palette.yellow())
			);
		}

		private Collection<Attribute> console() {
			return Set.of(
				new Attribute("CONSOLE_ERROR_OUTPUT").foreground(style.error()),
				new Attribute("CONSOLE_SYSTEM_OUTPUT").foreground(palette.yellow().darker()),
				new Attribute("CONSOLE_USER_INPUT").foreground(style.scheme().foreground().base()),
				new Attribute("CONSOLE_NORMAL_OUTPUT").foreground(style.scheme().foreground().base())
			);
		}

		private Collection<Attribute> logConsole() {
			return Set.of(
				new Attribute("LOG_ERROR_OUTPUT").foreground(style.error()),
				new Attribute("LOG_EXPIRED_ENTRY").foreground(palette.gray()),
				new Attribute("LOG_WARNING_OUTPUT").foreground(style.warning())
			);
		}

		private Collection<Attribute> diffAndMerge() {
			return Set.of(
				new Attribute("DIFF_INSERTED").background(palette.green().brighter(3)).errorStripe(palette.green().brighter(2)),
				new Attribute("DIFF_MODIFIED").background(palette.blue().brighter(3)).errorStripe(palette.blue().brighter(2)),
				new Attribute("DIFF_DELETED").background(palette.silver().brighter()).errorStripe(palette.silver()),
				new Attribute("DIFF_CONFLICT").background(palette.red().brighter(3)).errorStripe(palette.red().brighter(2))
			);
		}

		private Collection<Attribute> java() {
			return Set.of(
				new Attribute("TYPE_PARAMETER_NAME_ATTRIBUTES").foreground(style.scheme().foreground().base()).bold(),
				new Attribute("DEPRECATED_ATTRIBUTES")
					.foreground(deprecated)
					.strikeout(deprecated)
					.italic(),

				new Attribute("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES").baseAttributes("CLASS_NAME_ATTRIBUTES"),
				new Attribute("INSTANCE_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_INSTANCE_FIELD"),
				new Attribute("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES").foreground(palette.purple()),
				new Attribute("ANNOTATION_NAME_ATTRIBUTES").baseAttributes("DEFAULT_METADATA"),
				new Attribute("STATIC_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_STATIC_FIELD"),
				new Attribute("STATIC_FINAL_FIELD_ATTRIBUTES").baseAttributes("STATIC_FIELD_ATTRIBUTES"),

				new Attribute("List/map to object conversion").baseAttributes("JAVA_NUMBER")
			);
		}

		private Collection<Attribute> go() {
			return Set.of(
				new Attribute("GO_METHOD_RECEIVER")
					.foreground(palette.purple().darker())
					.bold(),
				new Attribute("GO_FUNCTION_PARAMETER")
					.foreground(palette.purple())
					.bold(),
				new Attribute("GO_STRUCT_LOCAL_MEMBER").foreground(palette.purple())
			);
		}
	}
}
