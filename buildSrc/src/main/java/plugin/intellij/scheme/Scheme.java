package plugin.intellij.scheme;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import plugin.domain.Palette;
import plugin.domain.color.Color;
import plugin.style.Style;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		private final Color added = palette.green();
		private final Color addedFilestatus = palette.aqua();
		private final Color modified = palette.blue();
		private final Color deleted = palette.silver().darker();

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Option.Color> option = List.of(
			new Option.Color("CARET_COLOR", style.scheme().foreground().base()),
			new Option.Color("CARET_ROW_COLOR", style.scheme().background().selectedLine()),
			new Option.Color("SELECTION_FOREGROUND", style.scheme().foreground().selectedText()),
			new Option.Color("SELECTION_BACKGROUND", style.scheme().background().selectedText()),
			new Option.Color("READONLY_BACKGROUND", style.scheme().background().readOnly()),

			new Option.Color("ADDED_LINES_COLOR", added.brighter()),
			new Option.Color("IGNORED_ADDED_LINES_BORDER_COLOR", added.brighter()),
			new Option.Color("MODIFIED_LINES_COLOR", modified.brighter()),
			new Option.Color("IGNORED_MODIFIED_LINES_BORDER_COLOR", modified.brighter()),
			new Option.Color("WHITESPACES_MODIFIED_LINES_COLOR", style.scheme().background().selectedLine().darker(2)),
			new Option.Color("FOLDED_TEXT_BORDER_COLOR", palette.yellow().brighter()),
			new Option.Color("DELETED_LINES_COLOR", deleted),
			new Option.Color("IGNORED_DELETED_LINES_BORDER_COLOR", deleted),

			new Option.Color("FILESTATUS_NOT_CHANGED", style.theme().foreground().base()),
			new Option.Color("FILESTATUS_ADDED", addedFilestatus),
			new Option.Color("FILESTATUS_addedOutside", addedFilestatus.brighter()),
			new Option.Color("FILESTATUS_NOT_CHANGED_RECURSIVE", modified.darker(3)),
			new Option.Color("FILESTATUS_NOT_CHANGED_IMMEDIATE", modified.darker(2)),
			new Option.Color("FILESTATUS_MODIFIED", modified.darker()),
			new Option.Color("FILESTATUS_modifiedOutside", modified.darker()),
			new Option.Color("FILESTATUS_DELETED", deleted),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_DELETED_FROM_FILE_SYSTEM", deleted),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_IGNORED", style.theme().foreground().disabled()),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_MERGED_WITH_BOTH_CONFLICTS", palette.red()),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_MERGED_WITH_CONFLICTS", palette.red()),
			new Option.Color("FILESTATUS_IDEA_FILESTATUS_MERGED_WITH_PROPERTY_CONFLICTS", palette.red()),
			new Option.Color("FILESTATUS_changelistConflict", palette.red()),
			new Option.Color("FILESTATUS_MERGED", palette.red().brighter(2)),
			new Option.Color("FILESTATUS_OBSOLETE", palette.orange()),
			// new Option.Color("FILESTATUS_SUPPRESSED", null),
			new Option.Color("FILESTATUS_SWITCHED", palette.aqua().darker()),
			new Option.Color("FILESTATUS_UNKNOWN", palette.red().darker()),
			// new Option.Color("FILESTATUS_HIJACKED", null),

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

		General general() { return new General(); }
		LanguageDefaults languageDefaults() { return new LanguageDefaults(); }
		ConsoleColors consoleColors() { return new ConsoleColors(); }
		DiffAndMerge diffAndMerge() { return new DiffAndMerge(); }
		Language language() { return new Language(); }

		@JacksonXmlElementWrapper(useWrapping = false)
		public List<Attribute> option() {
			return Stream.of(general(), languageDefaults(), consoleColors(), diffAndMerge(), language())
				.map(Group::attributes)
				.flatMap(Collection::stream)
				.sorted(Comparator.comparing(Attribute::name))
				.collect(Collectors.toList());
		}

		class General extends Group {

			Code code() { return new Code(); }
			Editor editor() { return new Editor(); }
			ErrorsAndWarnings errorsAndWarnings() { return new ErrorsAndWarnings(); }
			Hyperlinks hyperlinks() { return new Hyperlinks(); }
			SearchResults searchResults() { return new SearchResults(); }
			Text text() { return new Text(); }

			@Override
			protected Set<Option.Color> colors() {
				return Set.of();
			}

			@Override
			public Set<Attribute> attributes() {
				return new Group.Union(code(), editor(), errorsAndWarnings(), hyperlinks(), searchResults(), text()).attributes();
			}

			class Code extends Group {

				Attribute identifier_under_caret_attributes() {
					return new Attribute("IDENTIFIER_UNDER_CARET_ATTRIBUTES")
						.background(style.scheme().background().underCaret())
						.errorStripe(style.scheme().background().underCaret());
				}
				Attribute write_identifier_under_caret_attributes() {
					return new Attribute("WRITE_IDENTIFIER_UNDER_CARET_ATTRIBUTES")
						.background(style.scheme().background().underCaretWrite())
						.errorStripe(style.scheme().background().underCaretWrite().darker());
				}
				Attribute injected_language_fragment() {
					return new Attribute("INJECTED_LANGUAGE_FRAGMENT")
						.foreground(style.scheme().foreground().base().darker());
				}
				Attribute todo_default_attributes() {
					return new Attribute("TODO_DEFAULT_ATTRIBUTES")
						.foreground(style.scheme().foreground().base())
						.background(palette.orange().brighter()).errorStripeAsBackground();
				}
				Attribute matched_brace_attributes() {
					return new Attribute("MATCHED_BRACE_ATTRIBUTES")
						.foreground(style.scheme().foreground().base()).bold();
				}
				Attribute unmatched_brace_attributes() {
					return new Attribute("UNMATCHED_BRACE_ATTRIBUTES")
						.background(style.error()).errorStripeAsBackground();
				}
				Attribute live_template_attributes() {
					return new Attribute("LIVE_TEMPLATE_ATTRIBUTES")
						.bordered(palette.red().brighter());
				}
				Attribute live_template_inactive_segment() {
					return new Attribute("LIVE_TEMPLATE_INACTIVE_SEGMENT")
						.foreground(style.scheme().foreground().disabled());
				}
				Attribute template_variable_attributes() {
					return new Attribute("TEMPLATE_VARIABLE_ATTRIBUTES")
						.foreground(palette.purple());
				}

				@Override
				protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override
				public Set<Attribute> attributes() {
					return Set.of(
						identifier_under_caret_attributes(), write_identifier_under_caret_attributes(), injected_language_fragment(), todo_default_attributes(),
						matched_brace_attributes(), unmatched_brace_attributes(),

						live_template_attributes(), live_template_inactive_segment(), template_variable_attributes()
					);
				}
			}

			class Editor extends Group {

				Attribute bookmarks_attributes() {
					return new Attribute("BOOKMARKS_ATTRIBUTES")
						.errorStripe(style.scheme().foreground().base());
				}
				Attribute default_attribute() {
					return new Attribute("DEFAULT_ATTRIBUTE")
						.foreground(palette.purple().darker());
				}
				Attribute inline_parameter_hint() {
					return new Attribute("INLINE_PARAMETER_HINT")
						.foreground(style.scheme().foreground().base())
						.background(palette.aqua().darker());
				}
				Attribute info_attributes() {
					return new Attribute("INFO_ATTRIBUTES")
						.dottedLine(style.warning())
						.errorStripeAsEffect();
				}
				Attribute breakpoint_attributes() {
					return new Attribute("BREAKPOINT_ATTRIBUTES")
						.background(style.scheme().background().base().darker(3));
				}

				@Override
				protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override
				public Set<Attribute> attributes() {
					return union(
						Set.of(bookmarks_attributes(), default_attribute(), inline_parameter_hint(), info_attributes(), breakpoint_attributes()),
						new Breadcrumbs().attributes()
					);
				}

				class Breadcrumbs extends Group {

					Attribute breadcrumbs_default() {
						return new Attribute("BREADCRUMBS_DEFAULT")
							.foreground(style.scheme().foreground().base())
							.background(style.scheme().background().base());
					}
					Attribute hovered() {
						return new Attribute("BREADCRUMBS_HOVERED")
							.foreground(style.scheme().foreground().base())
							.background(style.scheme().background().base().darker(2));
					}
					Attribute current() {
						return new Attribute("BREADCRUMBS_CURRENT")
							.foreground(style.scheme().foreground().base())
							.background(style.scheme().background().base().darker(4));
					}
					Attribute inactive() {
						return new Attribute("BREADCRUMBS_INACTIVE")
							.foreground(style.scheme().foreground().base()); // unknown effect
					}

					@Override
					protected Set<Option.Color> colors() {
						return Set.of();
					}

					@Override
					public Set<Attribute> attributes() {
						return Set.of(
							breadcrumbs_default(),
							hovered(),
							current(),
							inactive()
						);
					}
				}
			}

			class ErrorsAndWarnings extends Group {
				Attribute bad_character() {
					return new Attribute("BAD_CHARACTER")
						.underwaved(style.error());
				}
				Attribute errors_attributes() {
					return new Attribute("ERRORS_ATTRIBUTES")
						.underwaved(style.error())
						.errorStripeAsEffect();
				}
				Attribute runtime_error() {
					return new Attribute("RUNTIME_ERROR")
						.underwaved(style.error())
						.errorStripeAsEffect();
				}
				Attribute typo() {
					return new Attribute("TYPO")
						.underwaved(palette.gray().brighter());
				}
				Attribute not_used_element_attributes() {
					return new Attribute("NOT_USED_ELEMENT_ATTRIBUTES")
						.foreground(palette.gray());
				}
				Attribute marked_for_removal_attributes() {
					return new Attribute("MARKED_FOR_REMOVAL_ATTRIBUTES")
						.foreground(deprecated).italic()
						.strikeout(deprecated);
				}
				Attribute warning_attributes() {
					return new Attribute("WARNING_ATTRIBUTES")
						.underwaved(palette.orange().brighter())
						.errorStripe(style.warning());
				}
				Attribute wrong_references_attributes() {
					return new Attribute("WRONG_REFERENCES_ATTRIBUTES")
						.underwaved(style.error())
						.errorStripeAsEffect();
				}

				@Override
				protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override
				public Set<Attribute> attributes() {
					return Set.of(
						bad_character(), errors_attributes(), runtime_error(), typo(), not_used_element_attributes(),
						marked_for_removal_attributes(), warning_attributes(), wrong_references_attributes()
					);
				}
			}

			class Hyperlinks extends Group {
				Attribute hyperlink_attributes() {
					return new Attribute("HYPERLINK_ATTRIBUTES")
						.foreground(style.link()).underscored(style.link());
				}
				Attribute ctrl_clickable() {
					return new Attribute("CTRL_CLICKABLE")
						.foreground(style.link()).underscored(style.link());
				}
				Attribute followed_hyperlink_attributes() {
					return new Attribute("FOLLOWED_HYPERLINK_ATTRIBUTES")
						.foreground(palette.purple().darker())
						.underscored(palette.purple().darker());
				}
				Attribute inactive_hyperlink_attributes() {
					return new Attribute("INACTIVE_HYPERLINK_ATTRIBUTES")
						.foreground(style.scheme().foreground().base())
						.underscored(style.scheme().foreground().base());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(hyperlink_attributes(), ctrl_clickable(), followed_hyperlink_attributes(), inactive_hyperlink_attributes());
				}
			}

			class SearchResults extends Group {
				Attribute search_result_attributes() {
					return new Attribute("SEARCH_RESULT_ATTRIBUTES")
						.foreground(style.scheme().foreground().base())
						.background(searchBackground).errorStripeAsBackground();
				}
				Attribute write_search_result_attributes() {
					return new Attribute("WRITE_SEARCH_RESULT_ATTRIBUTES")
						.foreground(style.scheme().foreground().base())
						.background(searchWriteBackground).errorStripeAsBackground();
				}
				Attribute text_search_result_attributes() {
					return new Attribute("TEXT_SEARCH_RESULT_ATTRIBUTES")
						.foreground(style.scheme().foreground().base())
						.background(searchBackground).errorStripeAsBackground();
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(search_result_attributes(), write_search_result_attributes(), text_search_result_attributes());
				}
			}

			class Text extends Group {
				Attribute text() {
					return new Attribute("TEXT")
						.foreground(style.scheme().foreground().base())
						.background(style.scheme().background().base());
				}
				Attribute deleted_text_attributes() {
					return new Attribute("DELETED_TEXT_ATTRIBUTES")
						.errorStripe(style.error())
						.dottedLine(style.error());
				}
				Attribute folded_text_attributes() {
					return new Attribute("FOLDED_TEXT_ATTRIBUTES")
						.foreground(palette.gray())
						.bordered(palette.gray().brighter());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}
				@Override public Set<Attribute> attributes() {
					return Set.of(text(), deleted_text_attributes(), folded_text_attributes());
				}
			}
		}

		class LanguageDefaults extends Group {

			Attribute commonKeyword() {
				return new Attribute("")
					.foreground(palette.blue()).bold();
			}

			Attribute string() {
				return new Attribute("DEFAULT_STRING")
					.foreground(palette.aqua())
					.bold();
			}
			Attribute valid_string_escape() {
				return new Attribute("DEFAULT_VALID_STRING_ESCAPE")
					.foreground(palette.aqua().darker())
					.bold();
			}
			Attribute invalid_string_escape() {
				return new Attribute("DEFAULT_INVALID_STRING_ESCAPE")
					.foreground(style.error()).underwaved(style.error())
					.bold();
			}
			Attribute constant() {
				return new Attribute("DEFAULT_CONSTANT")
					.foreground(palette.purple().darker(2))
					.bold();
			}
			Attribute line_comment() {
				return new Attribute("DEFAULT_LINE_COMMENT")
					.foreground(palette.gray())
					.italic();
			}
			Attribute block_comment() {
				return new Attribute("DEFAULT_BLOCK_COMMENT")
					.foreground(palette.gray())
					.italic();
			}
			Attribute doc_comment() {
				return new Attribute("DEFAULT_DOC_COMMENT")
					.foreground(palette.gray())
					.italic();
			}
			Attribute doc_comment_tag() {
				return new Attribute("DEFAULT_DOC_COMMENT_TAG")
					.foreground(palette.gray())
					.italic()
					.bold();
			}
			Attribute doc_comment_tag_value() {
				return new Attribute("DEFAULT_DOC_COMMENT_TAG_VALUE")
					.foreground(palette.gray().darker(2))
					.bold();
			}
			Attribute doc_markup() {
				return new Attribute("DEFAULT_DOC_MARKUP")
					.copy(commonKeyword()).removeFontType();
			}
			Attribute entity() {
				return new Attribute("DEFAULT_ENTITY")
					.foreground(palette.yellow());
			}
			Attribute function_declaration() {
				return new Attribute("DEFAULT_FUNCTION_DECLARATION")
					.foreground(style.scheme().foreground().base());
			}
			Attribute global_variable() {
				return new Attribute("DEFAULT_GLOBAL_VARIABLE")
					.foreground(palette.purple().darker(2))
					.bold();
			}
			Attribute identifier() {
				return new Attribute("DEFAULT_IDENTIFIER")
					.foreground(style.scheme().foreground().base());
			}
			Attribute instance_field() {
				return new Attribute("DEFAULT_INSTANCE_FIELD")
					.foreground(palette.purple().darker())
					.bold();
			}
			Attribute keyword() {
				return new Attribute("DEFAULT_KEYWORD")
					.copy(commonKeyword());
			}
			Attribute label() {
				return new Attribute("DEFAULT_LABEL")
					.foreground(palette.blue()).bold();
			}
			Attribute local_variable() {
				return new Attribute("DEFAULT_LOCAL_VARIABLE")
					.foreground(palette.purple());
			}
			Attribute metadata() {
				return new Attribute("DEFAULT_METADATA")
					.foreground(palette.green());
			}
			Attribute number() {
				return new Attribute("DEFAULT_NUMBER")
					.foreground(palette.blue())
					.bold();
			}
			Attribute parameter() {
				return new Attribute("DEFAULT_PARAMETER")
					.foreground(palette.purple())
					.bold();
			}
			Attribute predefined_symbol() {
				return new Attribute("DEFAULT_PREDEFINED_SYMBOL")
					.baseAttributes("DEFAULT_IDENTIFIER");
			}
			Attribute reassigned_local_variable() {
				return new Attribute("DEFAULT_REASSIGNED_LOCAL_VARIABLE")
					.foreground(palette.purple())
					.dottedLine(palette.purple());
			}
			Attribute reassigned_parameter() {
				return new Attribute("DEFAULT_REASSIGNED_PARAMETER")
					.foreground(palette.purple())
					.bold()
					.dottedLine(palette.purple());
			}
			Attribute static_field() {
				return new Attribute("DEFAULT_STATIC_FIELD")
					.foreground(palette.purple().darker()).bold()
					.italic();
			}
			Attribute static_method() {
				return new Attribute("DEFAULT_STATIC_METHOD")
					.foreground(style.scheme().foreground().base())
					.italic();
			}
			Attribute tag() {
				return new Attribute("DEFAULT_TAG")
					.foreground(palette.blue().darker());
			}
			Attribute template_language_color() {
				return new Attribute("DEFAULT_TEMPLATE_LANGUAGE_COLOR")
					.foreground(style.scheme().foreground().base().brighter(3));
			}
			Attribute interface_name() {
				return new Attribute("DEFAULT_INTERFACE_NAME")
					.foreground(style.scheme().foreground().base())
					.italic();
			}

			@Override protected Set<Option.Color> colors() {
				return Set.of();
			}

			@Override public Set<Attribute> attributes() {
				return union(
					new BracesAndOperators().attributes(),
					Set.of(
						string(), valid_string_escape(), invalid_string_escape(), constant(),

						line_comment(), block_comment(), doc_comment(), doc_comment_tag(), doc_comment_tag_value(), doc_markup(),

						entity(), function_declaration(), global_variable(), identifier(), instance_field(), interface_name(), keyword(), label(), local_variable(), metadata(), number(), parameter(),
						predefined_symbol(), reassigned_local_variable(), reassigned_parameter(), static_field(), static_method(), tag(), template_language_color()
					)
				);
			}
		}

		class BracesAndOperators extends Group {

			Attribute braces() {
				return new Attribute("DEFAULT_BRACES")
					.foreground(style.scheme().foreground().base());
			}
			Attribute brackets() {
				return new Attribute("DEFAULT_BRACKETS")
					.foreground(style.scheme().foreground().base());
			}
			Attribute comma() {
				return new Attribute("DEFAULT_COMMA")
					.foreground(style.scheme().foreground().base());
			}
			Attribute dot() {
				return new Attribute("DEFAULT_DOT")
					.foreground(style.scheme().foreground().base());
			}
			Attribute operation_sign() {
				return new Attribute("DEFAULT_OPERATION_SIGN")
					.foreground(style.scheme().foreground().base());
			}
			Attribute parenths() {
				return new Attribute("DEFAULT_PARENTHS")
					.foreground(style.scheme().foreground().base());
			}
			Attribute semicolon() {
				return new Attribute("DEFAULT_SEMICOLON")
					.foreground(style.scheme().foreground().base());
			}

			@Override protected Set<Option.Color> colors() {
				return Set.of();
			}

			@Override public Set<Attribute> attributes() {
				return Set.of(braces(), brackets(), comma(), dot(), operation_sign(), parenths(), semicolon());
			}
		}

		class ConsoleColors extends Group {

			ANSIColors ansiColors() { return new ANSIColors(); }
			Console console() { return new Console(); }
			LogConsole logConsole() { return new LogConsole(); }

			@Override protected Set<Option.Color> colors() {
				return Set.of();
			}

			@Override public Set<Attribute> attributes() {
				return new Group.Union(ansiColors(), console(), logConsole()).attributes();
			}

			class ANSIColors extends Group {
				Attribute black() {
					return new Attribute("CONSOLE_BLACK_OUTPUT")
						.foreground(style.scheme().foreground().base());
				}
				Attribute blue_bright() {
					return new Attribute("CONSOLE_BLUE_BRIGHT_OUTPUT")
						.foreground(palette.blue().brighter());
				}
				Attribute blue() {
					return new Attribute("CONSOLE_BLUE_OUTPUT")
						.foreground(palette.blue());
				}
				Attribute cyan_bright() {
					return new Attribute("CONSOLE_CYAN_BRIGHT_OUTPUT")
						.foreground(palette.aqua().brighter());
				}
				Attribute cyan() {
					return new Attribute("CONSOLE_CYAN_OUTPUT")
						.foreground(palette.aqua());
				}
				Attribute darkgray() {
					return new Attribute("CONSOLE_DARKGRAY_OUTPUT")
						.foreground(palette.gray().darker());
				}
				Attribute gray() {
					return new Attribute("CONSOLE_GRAY_OUTPUT")
						.foreground(palette.gray());
				}
				Attribute green_bright() {
					return new Attribute("CONSOLE_GREEN_BRIGHT_OUTPUT")
						.foreground(palette.green().brighter());
				}
				Attribute green() {
					return new Attribute("CONSOLE_GREEN_OUTPUT")
						.foreground(palette.green());
				}
				Attribute magenta_bright() {
					return new Attribute("CONSOLE_MAGENTA_BRIGHT_OUTPUT")
						.foreground(palette.purple().brighter());
				}
				Attribute magenta() {
					return new Attribute("CONSOLE_MAGENTA_OUTPUT")
						.foreground(palette.purple());
				}
				Attribute red_bright() {
					return new Attribute("CONSOLE_RED_BRIGHT_OUTPUT")
						.foreground(palette.red().brighter());
				}
				Attribute red() {
					return new Attribute("CONSOLE_RED_OUTPUT")
						.foreground(palette.red());
				}
				Attribute white() {
					return new Attribute("CONSOLE_WHITE_OUTPUT")
						.foreground(style.scheme().foreground().base());
				}
				Attribute yellow_bright() {
					return new Attribute("CONSOLE_YELLOW_BRIGHT_OUTPUT")
						.foreground(palette.yellow().brighter());
				}
				Attribute yellow() {
					return new Attribute("CONSOLE_YELLOW_OUTPUT")
						.foreground(palette.yellow());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						black(), blue_bright(), blue(), cyan_bright(), cyan(), darkgray(), gray(), green_bright(), green(),
						magenta_bright(), magenta(), red_bright(), red(), white(), yellow_bright(), yellow()
					);
				}
			}

			class Console extends Group {
				Attribute error() {
					return new Attribute("CONSOLE_ERROR_OUTPUT")
						.foreground(style.error());
				}
				Attribute system() {
					return new Attribute("CONSOLE_SYSTEM_OUTPUT")
						.foreground(palette.yellow().darker());
				}
				Attribute user_input() {
					return new Attribute("CONSOLE_USER_INPUT")
						.foreground(style.scheme().foreground().base());
				}
				Attribute normal() {
					return new Attribute("CONSOLE_NORMAL_OUTPUT")
						.foreground(style.scheme().foreground().base());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(error(), system(), user_input(), normal());
				}
			}

			class LogConsole extends Group {
				Attribute error_output() {
					return new Attribute("LOG_ERROR_OUTPUT")
						.foreground(style.error());
				}
				Attribute expired_entry() {
					return new Attribute("LOG_EXPIRED_ENTRY")
						.foreground(palette.gray());
				}
				Attribute warning_output() {
					return new Attribute("LOG_WARNING_OUTPUT")
						.foreground(style.warning());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						error_output(),
						expired_entry(),
						warning_output()
					);
				}
			}
		}

		class DiffAndMerge extends Group {
			Attribute inserted() {
				return new Attribute("DIFF_INSERTED")
					.background(palette.green().brighter(3))
					.errorStripe(palette.green().brighter(2));
			}
			Attribute modified() {
				return new Attribute("DIFF_MODIFIED")
					.background(palette.blue().brighter(3))
					.errorStripe(palette.blue().brighter(2));
			}
			Attribute deleted() {
				return new Attribute("DIFF_DELETED")
					.background(palette.silver().brighter())
					.errorStripe(palette.silver());
			}
			Attribute conflict() {
				return new Attribute("DIFF_CONFLICT")
					.background(palette.red().brighter(3))
					.errorStripe(palette.red().brighter(2));
			}

			@Override protected Set<Option.Color> colors() {
				return Set.of();
			}

			@Override public Set<Attribute> attributes() {
				return Set.of(inserted(), modified(), deleted(), conflict());
			}
		}

		class Language extends Group {

			Java java() { return new Java(); }
			Groovy groovy() { return new Groovy(); }
			Go go() { return new Go(); }
			TypeScript typeScript() { return new TypeScript(); }
			JavaScript javaScript() { return new JavaScript(); }
			Python python() { return new Python(); }
			Php php() { return new Php(); }
			UserDefinedFileTypes userDefinedFileTypes() { return new UserDefinedFileTypes(); }

			@Override protected Set<Option.Color> colors() {
				return Set.of();
			}

			@Override public Set<Attribute> attributes() {
				return new Group.Union(java(), groovy(), go(), typeScript(), javaScript(), python(), php(), userDefinedFileTypes()).attributes();
			}

			class Java extends Group {
				Attribute abstract_class_name_attributes() {
					return new Attribute("ABSTRACT_CLASS_NAME_ATTRIBUTES")
						.copy(Attributes.this.languageDefaults().interface_name());
				}
				Attribute annotation_attribute_name_attributes() {
					return new Attribute("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES")
						.foreground(palette.purple());
				}
				Attribute annotation_name_attributes() {
					return new Attribute("ANNOTATION_NAME_ATTRIBUTES")
						.baseAttributes("DEFAULT_METADATA");
				}
				Attribute deprecated_attributes() {
					return new Attribute("DEPRECATED_ATTRIBUTES")
						.foreground(deprecated).strikeout(deprecated).italic();
				}
				Attribute implicit_anonymous_class_parameter_attributes() {
					return new Attribute("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES")
						.copy(Attributes.this.languageDefaults().parameter());
				}
				Attribute instance_field_attributes() {
					return new Attribute("INSTANCE_FIELD_ATTRIBUTES")
						.baseAttributes("DEFAULT_INSTANCE_FIELD");
				}
				Attribute static_field_attributes() {
					return new Attribute("STATIC_FIELD_ATTRIBUTES")
						.baseAttributes("DEFAULT_STATIC_FIELD");
				}
				Attribute static_final_field_attributes() {
					return new Attribute("STATIC_FINAL_FIELD_ATTRIBUTES")
						.baseAttributes("STATIC_FIELD_ATTRIBUTES");
				}
				Attribute type_parameter_name_attributes() {
					return new Attribute("TYPE_PARAMETER_NAME_ATTRIBUTES")
						.foreground(style.scheme().foreground().base()).bold();
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						abstract_class_name_attributes(),
						annotation_attribute_name_attributes(),
						annotation_name_attributes(),
						deprecated_attributes(),
						implicit_anonymous_class_parameter_attributes(),
						instance_field_attributes(),
						static_field_attributes(),
						static_final_field_attributes(),
						type_parameter_name_attributes()
					);
				}
			}

			class Groovy extends Group {
				Attribute groovy_keyword() {
					return new Attribute("GROOVY_KEYWORD")
						.baseAttributes("JAVA_KEYWORD");
				}
				Attribute list_map_to_object_conversion() {
					return new Attribute("List/map to object conversion")
						.copy(Attributes.this.languageDefaults().identifier());
				}
				Attribute static_property_reference_ID() {
					return new Attribute("Static property reference ID")
						.baseAttributes("STATIC_FINAL_FIELD_ATTRIBUTES");
				}
				Attribute unresolved_reference_access() {
					return new Attribute("Unresolved reference access")
						.copy(Attributes.this.general().errorsAndWarnings().not_used_element_attributes());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						groovy_keyword(),
						list_map_to_object_conversion(),
						static_property_reference_ID(),
						unresolved_reference_access()
					);
				}
			}

			class Go extends Group {
				Attribute builtin_constant() {
					return new Attribute("GO_BUILTIN_CONSTANT")
						.copy(Attributes.this.languageDefaults().keyword());
				}
				Attribute builtin_variable() {
					return new Attribute("GO_BUILTIN_VARIABLE")
						.copy(Attributes.this.languageDefaults().keyword());
				}
				Attribute comment_reference() {
					return new Attribute("GO_COMMENT_REFERENCE")
						.copy(Attributes.this.languageDefaults().doc_comment_tag_value());
				}
				Attribute function_parameter() {
					return new Attribute("GO_FUNCTION_PARAMETER")
						.copy(Attributes.this.languageDefaults().parameter());
				}
				Attribute local_constant() {
					return new Attribute("GO_LOCAL_CONSTANT")
						.foreground(palette.purple());
				}
				Attribute method_receiver() {
					return new Attribute("GO_METHOD_RECEIVER")
						.foreground(palette.purple().darker()).bold();
				}
				Attribute package_exported_constant() {
					return new Attribute("GO_PACKAGE_EXPORTED_CONSTANT")
						.foreground(palette.purple().darker()).bold();
				}
				Attribute package_exported_variable() {
					return new Attribute("GO_PACKAGE_EXPORTED_VARIABLE")
						.foreground(palette.purple().darker()).bold();
				}
				Attribute package_local_constant() {
					return new Attribute("GO_PACKAGE_LOCAL_CONSTANT")
						.foreground(palette.purple().darker()).bold();
				}
				Attribute package_local_variable() {
					return new Attribute("GO_PACKAGE_LOCAL_VARIABLE")
						.foreground(palette.purple().darker()).bold();
				}
				Attribute shadowing_variable() {
					return new Attribute("GO_SHADOWING_VARIABLE")
						.foreground(palette.purple()).dottedLine(palette.purple());
				}
				Attribute struct_exported_member() {
					return new Attribute("GO_STRUCT_EXPORTED_MEMBER")
						.copy(Attributes.this.languageDefaults().instance_field());
				}
				Attribute struct_local_member() {
					return new Attribute("GO_STRUCT_LOCAL_MEMBER")
						.copy(Attributes.this.languageDefaults().instance_field());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						builtin_constant(),
						builtin_variable(),
						comment_reference(),
						function_parameter(),
						local_constant(),
						method_receiver(),
						package_exported_constant(),
						package_exported_variable(),
						package_local_constant(),
						package_local_variable(),
						shadowing_variable(),
						struct_exported_member(),
						struct_local_member()
					);
				}
			}

			class TypeScript extends Group {
				Attribute type_parameter() {
					return new Attribute("TS.TYPE_PARAMETER")
						.copy(Attributes.this.language().java().type_parameter_name_attributes());
				}
				Attribute type_guard() {
					return new Attribute("TS.TYPE_GUARD")
						.emptyValue();
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						type_parameter(),
						type_guard()
					);
				}
			}

			class JavaScript extends Group {
				Attribute global_variable() {
					return new Attribute("JS.GLOBAL_VARIABLE")
						.baseAttributes("DEFAULT_PARAMETER");
				}
				Attribute global_function() {
					return new Attribute("JS.GLOBAL_FUNCTION")
						.baseAttributes("DEFAULT_FUNCTION_DECLARATION");
				}
				Attribute injected_language_fragment() {
					return new Attribute("JavaScript:INJECTED_LANGUAGE_FRAGMENT")
						.baseAttributes("INJECTED_LANGUAGE_FRAGMENT");
				}
				Attribute instance_member_function() {
					return new Attribute("JS.INSTANCE_MEMBER_FUNCTION")
						.baseAttributes("DEFAULT_INSTANCE_METHOD");
				}
				Attribute local_variable() {
					return new Attribute("JS.LOCAL_VARIABLE")
						.baseAttributes("DEFAULT_LOCAL_VARIABLE");
				}
				Attribute parameter() {
					return new Attribute("JS.PARAMETER")
						.baseAttributes("DEFAULT_PARAMETER");
				}
				Attribute regex() {
					return new Attribute("JS.REGEXP")
						.copy(Attributes.this.languageDefaults().valid_string_escape());
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						global_variable(),
						global_function(),
						injected_language_fragment(),
						instance_member_function(),
						local_variable(),
						parameter(),
						regex()
					);
				}
			}

			class Python extends Group {
				Attribute decorator() {
					return new Attribute("PY.DECORATOR")
						.copy(Attributes.this.languageDefaults().metadata());
				}
				Attribute keyword_argument() {
					return new Attribute("PY.KEYWORD_ARGUMENT")
						.copy(Attributes.this.languageDefaults().local_variable());
				}
				Attribute predefined_definition() {
					return new Attribute("PY.PREDEFINED_DEFINITION")
						.baseAttributes("DEFAULT_PREDEFINED_SYMBOL");
				}
				Attribute predefined_usage() {
					return new Attribute("PY.PREDEFINED_USAGE")
						.baseAttributes("DEFAULT_PREDEFINED_SYMBOL");
				}
				Attribute self_parameter() {
					return new Attribute("PY.SELF_PARAMETER")
						.baseAttributes("DEFAULT_PARAMETER");
				}
				Attribute string_u() {
					return new Attribute("PY.STRING.U")
						.baseAttributes("DEFAULT_STRING");
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						decorator(),
						keyword_argument(),
						predefined_definition(),
						predefined_usage(),
						self_parameter(),
						string_u()
					);
				}
			}

			class Php extends Group {
				Attribute magic_member_access() {
					return new Attribute("MAGIC_MEMBER_ACCESS")
						.copy(Attributes.this.general().errorsAndWarnings().not_used_element_attributes());
				}
				Attribute doc_parameter() {
					return new Attribute("PHP_DOC_PARAMETER")
						.copy(Attributes.this.languageDefaults().doc_comment_tag_value());
				}
				Attribute exec_command_id() {
					return new Attribute("PHP_EXEC_COMMAND_ID")
						.background(palette.silver().brighter(3));
				}
				Attribute named_argument() {
					return new Attribute("PHP_NAMED_ARGUMENT")
						.copy(Attributes.this.languageDefaults().local_variable());
				}
				Attribute parameter() {
					return new Attribute("PHP_PARAMETER")
						.baseAttributes("DEFAULT_PARAMETER");
				}
				Attribute var() {
					return new Attribute("PHP_VAR")
						.baseAttributes("DEFAULT_LOCAL_VARIABLE");
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}
				@Override public Set<Attribute> attributes() {
					return Set.of(
						magic_member_access(),
						doc_parameter(),
						exec_command_id(),
						named_argument(),
						parameter(),
						var()
					);
				}
			}

			class UserDefinedFileTypes extends Group {
				Attribute invalid_string_escape_attributes() {
					return new Attribute("CUSTOM_INVALID_STRING_ESCAPE_ATTRIBUTES")
						.baseAttributes("DEFAULT_INVALID_STRING_ESCAPE");
				}
				Attribute keyword1_attributes() {
					return new Attribute("CUSTOM_KEYWORD1_ATTRIBUTES")
						.baseAttributes("DEFAULT_KEYWORD");
				}
				Attribute keyword2_attributes() {
					return new Attribute("CUSTOM_KEYWORD2_ATTRIBUTES")
						.copy(Attributes.this.languageDefaults().parameter());
				}
				Attribute keyword3_attributes() {
					return new Attribute("CUSTOM_KEYWORD3_ATTRIBUTES")
						.copy(Attributes.this.languageDefaults().parameter());
				}
				Attribute keyword4_attributes() {
					return new Attribute("CUSTOM_KEYWORD4_ATTRIBUTES")
						.copy(Attributes.this.languageDefaults().parameter());
				}
				Attribute line_comment_attributes() {
					return new Attribute("CUSTOM_LINE_COMMENT_ATTRIBUTES")
						.baseAttributes("DEFAULT_LINE_COMMENT");
				}
				Attribute multi_line_comment_attributes() {
					return new Attribute("CUSTOM_MULTI_LINE_COMMENT_ATTRIBUTES")
						.baseAttributes("DEFAULT_BLOCK_COMMENT");
				}
				Attribute number_attributes() {
					return new Attribute("CUSTOM_NUMBER_ATTRIBUTES")
						.baseAttributes("DEFAULT_NUMBER");
				}
				Attribute string_attributes() {
					return new Attribute("CUSTOM_STRING_ATTRIBUTES")
						.baseAttributes("DEFAULT_STRING");
				}
				Attribute valid_string_escape_attributes() {
					return new Attribute("CUSTOM_VALID_STRING_ESCAPE_ATTRIBUTES")
						.baseAttributes("DEFAULT_VALID_STRING_ESCAPE");
				}

				@Override protected Set<Option.Color> colors() {
					return Set.of();
				}

				@Override public Set<Attribute> attributes() {
					return Set.of(
						invalid_string_escape_attributes(),
						keyword1_attributes(),
						keyword2_attributes(),
						keyword3_attributes(),
						keyword4_attributes(),
						line_comment_attributes(),
						multi_line_comment_attributes(),
						number_attributes(),
						string_attributes(),
						valid_string_escape_attributes()
					);
				}
			}
		}
	}
}
