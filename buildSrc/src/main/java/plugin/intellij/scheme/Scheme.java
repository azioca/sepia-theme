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

	private static Collection<Attribute> mergeAttributes(Group... groups) {
		return Arrays.stream(groups).flatMap(group -> group.attributes().stream()).collect(Collectors.toSet());
	}

	@SafeVarargs
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
			return mergeAttributes(general(), languageDefaults(), consoleColors(), diffAndMerge(), language()).stream()
				.sorted(Comparator.comparing(Attribute::name))
				.collect(Collectors.toList());
		}

		class General implements Group {

			Code code() { return new Code(); }
			Editor editor() { return new Editor(); }
			ErrorsAndWarnings errorsAndWarnings() { return new ErrorsAndWarnings(); }
			Hyperlinks hyperlinks() { return new Hyperlinks(); }
			SearchResults searchResults() { return new SearchResults(); }
			Text text() { return new Text(); }

			@Override public Collection<Attribute> attributes() {
				return mergeAttributes(code(), editor(), errorsAndWarnings(), hyperlinks(), searchResults(), text());
			}

			class Code implements Group {

				private final Attribute identifier_under_caret_attributes = new Attribute("IDENTIFIER_UNDER_CARET_ATTRIBUTES")
					.background(style.scheme().background().underCaret())
					.errorStripe(style.scheme().background().underCaret());
				private final Attribute write_identifier_under_caret_attributes = new Attribute("WRITE_IDENTIFIER_UNDER_CARET_ATTRIBUTES")
					.background(style.scheme().background().underCaretWrite())
					.errorStripe(style.scheme().background().underCaretWrite().darker());
				private final Attribute injected_language_fragment = new Attribute("INJECTED_LANGUAGE_FRAGMENT")
					.foreground(style.scheme().foreground().base().darker());
				private final Attribute todo_default_attributes = new Attribute("TODO_DEFAULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(palette.orange().brighter())
					.errorStripeAsBackground();
				private final Attribute matched_brace_attributes = new Attribute("MATCHED_BRACE_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.bold();
				private final Attribute unmatched_brace_attributes = new Attribute("UNMATCHED_BRACE_ATTRIBUTES")
					.background(style.error())
					.errorStripeAsBackground();
				private final Attribute live_template_attributes = new Attribute("LIVE_TEMPLATE_ATTRIBUTES")
					.bordered(palette.red().brighter());
				private final Attribute live_template_inactive_segment = new Attribute("LIVE_TEMPLATE_INACTIVE_SEGMENT")
					.foreground(style.scheme().foreground().disabled());
				private final Attribute template_variable_attributes = new Attribute("TEMPLATE_VARIABLE_ATTRIBUTES")
					.foreground(palette.purple());

				@Override
				public Collection<Attribute> attributes() {
					return Set.of(
						identifier_under_caret_attributes, write_identifier_under_caret_attributes, injected_language_fragment, todo_default_attributes,
						matched_brace_attributes, unmatched_brace_attributes,

						live_template_attributes, live_template_inactive_segment, template_variable_attributes
					);
				}
			}

			class Editor implements Group {

				private final Attribute bookmarks_attributes = new Attribute("BOOKMARKS_ATTRIBUTES")
					.errorStripe(style.scheme().foreground().base());
				private final Attribute default_attribute = new Attribute("DEFAULT_ATTRIBUTE")
					.foreground(palette.purple().darker());
				private final Attribute inline_parameter_hint = new Attribute("INLINE_PARAMETER_HINT")
					.foreground(style.scheme().foreground().base())
					.background(palette.aqua().darker());
				private final Attribute info_attributes = new Attribute("INFO_ATTRIBUTES")
					.dottedLine(style.warning())
					.errorStripeAsEffect();
				private final Attribute breakpoint_attributes = new Attribute("BREAKPOINT_ATTRIBUTES")
					.background(style.scheme().background().base().darker(3));

				@Override public Collection<Attribute> attributes() {
					return merge(
						Set.of(bookmarks_attributes, default_attribute, inline_parameter_hint, info_attributes, breakpoint_attributes),
						new Breadcrumbs().attributes()
					);
				}

				class Breadcrumbs implements Group {

					private final Attribute breadcrumbs_default = new Attribute("BREADCRUMBS_DEFAULT")
						.foreground(style.scheme().foreground().base())
						.background(style.scheme().background().base());
					private final Attribute hovered = new Attribute("BREADCRUMBS_HOVERED")
						.foreground(style.scheme().foreground().base())
						.background(style.scheme().background().base().darker(2));
					private final Attribute current = new Attribute("BREADCRUMBS_CURRENT")
						.foreground(style.scheme().foreground().base())
						.background(style.scheme().background().base().darker(4));
					private final Attribute inactive = new Attribute("BREADCRUMBS_INACTIVE")
						.foreground(style.scheme().foreground().base()); // unknown effect

					@Override public Collection<Attribute> attributes() {
						return Set.of(
							breadcrumbs_default,
							hovered,
							current,
							inactive
						);
					}
				}
			}

			class ErrorsAndWarnings implements Group {
				private final Attribute bad_character = new Attribute("BAD_CHARACTER")
					.underwaved(style.error());
				private final Attribute errors_attributes = new Attribute("ERRORS_ATTRIBUTES")
					.underwaved(style.error())
					.errorStripeAsEffect();
				private final Attribute runtime_error = new Attribute("RUNTIME_ERROR")
					.underwaved(style.error())
					.errorStripeAsEffect();
				private final Attribute typo = new Attribute("TYPO")
					.underwaved(palette.gray().brighter());
				private final Attribute not_used_element_attributes = new Attribute("NOT_USED_ELEMENT_ATTRIBUTES")
					.foreground(palette.gray());
				private final Attribute marked_for_removal_attributes = new Attribute("MARKED_FOR_REMOVAL_ATTRIBUTES")
					.foreground(deprecated).italic()
					.strikeout(deprecated);
				private final Attribute warning_attributes = new Attribute("WARNING_ATTRIBUTES")
					.underwaved(palette.orange().brighter())
					.errorStripe(style.warning());
				private final Attribute wrong_references_attributes = new Attribute("WRONG_REFERENCES_ATTRIBUTES")
					.underwaved(style.error())
					.errorStripeAsEffect();

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						bad_character, errors_attributes, runtime_error, typo, not_used_element_attributes,
						marked_for_removal_attributes, warning_attributes, wrong_references_attributes
					);
				}
			}

			class Hyperlinks implements Group {
				private final Attribute hyperlink_attributes = new Attribute("HYPERLINK_ATTRIBUTES")
					.foreground(style.link()).underscored(style.link());
				private final Attribute ctrl_clickable = new Attribute("CTRL_CLICKABLE")
					.foreground(style.link()).underscored(style.link());
				private final Attribute followed_hyperlink_attributes = new Attribute("FOLLOWED_HYPERLINK_ATTRIBUTES")
					.foreground(palette.purple().darker())
					.underscored(palette.purple().darker());
				private final Attribute inactive_hyperlink_attributes = new Attribute("INACTIVE_HYPERLINK_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.underscored(style.scheme().foreground().base());

				@Override public Collection<Attribute> attributes() {
					return Set.of(hyperlink_attributes, ctrl_clickable, followed_hyperlink_attributes, inactive_hyperlink_attributes);
				}
			}

			class SearchResults implements Group {
				private final Attribute search_result_attributes = new Attribute("SEARCH_RESULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(searchBackground).errorStripeAsBackground();
				private final Attribute write_search_result_attributes = new Attribute("WRITE_SEARCH_RESULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(searchWriteBackground).errorStripeAsBackground();
				private final Attribute text_search_result_attributes = new Attribute("TEXT_SEARCH_RESULT_ATTRIBUTES")
					.foreground(style.scheme().foreground().base())
					.background(searchBackground).errorStripeAsBackground();

				@Override public Collection<Attribute> attributes() {
					return Set.of(search_result_attributes, write_search_result_attributes, text_search_result_attributes);
				}
			}

			class Text implements Group {
				private final Attribute text = new Attribute("TEXT")
					.foreground(style.scheme().foreground().base())
					.background(style.scheme().background().base());
				private final Attribute deleted_text_attributes = new Attribute("DELETED_TEXT_ATTRIBUTES")
					.errorStripe(style.error())
					.dottedLine(style.error());
				private final Attribute folded_text_attributes = new Attribute("FOLDED_TEXT_ATTRIBUTES")
					.foreground(palette.gray())
					.bordered(palette.gray().brighter());

				@Override public Collection<Attribute> attributes() {
					return Set.of(text, deleted_text_attributes, folded_text_attributes);
				}
			}
		}

		class LanguageDefaults implements Group {

			private final Attribute commonKeyword = new Attribute("").foreground(palette.blue()).bold();

			private final Attribute string = new Attribute("DEFAULT_STRING")
				.foreground(palette.aqua())
				.bold();
			private final Attribute valid_string_escape = new Attribute("DEFAULT_VALID_STRING_ESCAPE")
				.foreground(palette.aqua().darker())
				.bold();
			private final Attribute invalid_string_escape = new Attribute("DEFAULT_INVALID_STRING_ESCAPE")
				.foreground(style.error()).underwaved(style.error())
				.bold();
			private final Attribute constant = new Attribute("DEFAULT_CONSTANT")
				.foreground(palette.purple().darker(2))
				.bold();
			private final Attribute line_comment = new Attribute("DEFAULT_LINE_COMMENT")
				.foreground(palette.gray())
				.italic();
			private final Attribute block_comment = new Attribute("DEFAULT_BLOCK_COMMENT")
				.foreground(palette.gray())
				.italic();
			private final Attribute doc_comment = new Attribute("DEFAULT_DOC_COMMENT")
				.foreground(palette.gray())
				.italic();
			private final Attribute doc_comment_tag = new Attribute("DEFAULT_DOC_COMMENT_TAG")
				.foreground(palette.gray())
				.italic()
				.bold();
			private final Attribute doc_comment_tag_value = new Attribute("DEFAULT_DOC_COMMENT_TAG_VALUE")
				.foreground(palette.gray().darker(2))
				.bold();
			private final Attribute doc_markup = new Attribute("DEFAULT_DOC_MARKUP").copy(commonKeyword).removeFontType();
			private final Attribute entity = new Attribute("DEFAULT_ENTITY").foreground(palette.yellow());
			private final Attribute function_declaration = new Attribute("DEFAULT_FUNCTION_DECLARATION").foreground(style.scheme().foreground().base());
			private final Attribute global_variable = new Attribute("DEFAULT_GLOBAL_VARIABLE")
				.foreground(palette.purple().darker(2))
				.bold();
			private final Attribute identifier = new Attribute("DEFAULT_IDENTIFIER").foreground(style.scheme().foreground().base());
			private final Attribute instance_field = new Attribute("DEFAULT_INSTANCE_FIELD")
				.foreground(palette.purple().darker())
				.bold();
			private final Attribute keyword = new Attribute("DEFAULT_KEYWORD").copy(commonKeyword);
			private final Attribute label = new Attribute("DEFAULT_LABEL").foreground(palette.blue()).bold();
			private final Attribute local_variable = new Attribute("DEFAULT_LOCAL_VARIABLE").foreground(palette.purple());
			private final Attribute metadata = new Attribute("DEFAULT_METADATA").foreground(palette.green());
			private final Attribute number = new Attribute("DEFAULT_NUMBER")
				.foreground(palette.blue())
				.bold();
			private final Attribute parameter = new Attribute("DEFAULT_PARAMETER")
				.foreground(palette.purple())
				.bold();
			private final Attribute predefined_symbol = new Attribute("DEFAULT_PREDEFINED_SYMBOL").baseAttributes("DEFAULT_IDENTIFIER");
			private final Attribute reassigned_local_variable = new Attribute("DEFAULT_REASSIGNED_LOCAL_VARIABLE")
				.foreground(palette.purple())
				.dottedLine(palette.purple());
			private final Attribute reassigned_parameter = new Attribute("DEFAULT_REASSIGNED_PARAMETER")
				.foreground(palette.purple())
				.bold()
				.dottedLine(palette.purple());
			private final Attribute static_field = new Attribute("DEFAULT_STATIC_FIELD")
				.foreground(palette.purple().darker()).bold()
				.italic();
			private final Attribute static_method = new Attribute("DEFAULT_STATIC_METHOD")
				.foreground(style.scheme().foreground().base())
				.italic();
			private final Attribute tag = new Attribute("DEFAULT_TAG").foreground(palette.blue().darker());
			private final Attribute template_language_color = new Attribute("DEFAULT_TEMPLATE_LANGUAGE_COLOR").foreground(style.scheme().foreground().base().brighter(3));
			private final Attribute interface_name = new Attribute("DEFAULT_INTERFACE_NAME")
				.foreground(style.scheme().foreground().base())
				.italic();
			@Override public Collection<Attribute> attributes() {
				return merge(
					new BracesAndOperators().attributes(),
					Set.of(
						string, valid_string_escape, invalid_string_escape, constant,

						line_comment, block_comment, doc_comment, doc_comment_tag, doc_comment_tag_value, doc_markup,

						entity, function_declaration, global_variable, identifier, instance_field, interface_name, keyword, label, local_variable, metadata, number, parameter,
						predefined_symbol, reassigned_local_variable, reassigned_parameter, static_field, static_method, tag, template_language_color
					)
				);
			}
		}

		class BracesAndOperators implements Group {

			private final Attribute braces = new Attribute("DEFAULT_BRACES").foreground(style.scheme().foreground().base());
			private final Attribute brackets = new Attribute("DEFAULT_BRACKETS").foreground(style.scheme().foreground().base());
			private final Attribute comma = new Attribute("DEFAULT_COMMA").foreground(style.scheme().foreground().base());
			private final Attribute dot = new Attribute("DEFAULT_DOT").foreground(style.scheme().foreground().base());
			private final Attribute operation_sign = new Attribute("DEFAULT_OPERATION_SIGN").foreground(style.scheme().foreground().base());
			private final Attribute parenths = new Attribute("DEFAULT_PARENTHS").foreground(style.scheme().foreground().base());
			private final Attribute semicolon = new Attribute("DEFAULT_SEMICOLON").foreground(style.scheme().foreground().base());

			@Override public Collection<Attribute> attributes() {
				return Set.of(braces, brackets, comma, dot, operation_sign, parenths, semicolon);
			}
		}

		class ConsoleColors implements Group {

			ANSIColors ansiColors() { return new ANSIColors(); }
			Console console() { return new Console(); }
			LogConsole logConsole() { return new LogConsole(); }

			@Override public Collection<Attribute> attributes() {
				return mergeAttributes(ansiColors(), console(), logConsole());
			}

			class ANSIColors implements Group {
				private final Attribute black = new Attribute("CONSOLE_BLACK_OUTPUT").foreground(style.scheme().foreground().base());
				private final Attribute blue_bright = new Attribute("CONSOLE_BLUE_BRIGHT_OUTPUT").foreground(palette.blue().brighter());
				private final Attribute blue = new Attribute("CONSOLE_BLUE_OUTPUT").foreground(palette.blue());
				private final Attribute cyan_bright = new Attribute("CONSOLE_CYAN_BRIGHT_OUTPUT").foreground(palette.aqua().brighter());
				private final Attribute cyan = new Attribute("CONSOLE_CYAN_OUTPUT").foreground(palette.aqua());
				private final Attribute darkgray = new Attribute("CONSOLE_DARKGRAY_OUTPUT").foreground(palette.gray().darker());
				private final Attribute gray = new Attribute("CONSOLE_GRAY_OUTPUT").foreground(palette.gray());
				private final Attribute green_bright = new Attribute("CONSOLE_GREEN_BRIGHT_OUTPUT").foreground(palette.green().brighter());
				private final Attribute green = new Attribute("CONSOLE_GREEN_OUTPUT").foreground(palette.green());
				private final Attribute magenta_bright = new Attribute("CONSOLE_MAGENTA_BRIGHT_OUTPUT").foreground(palette.purple().brighter());
				private final Attribute magenta = new Attribute("CONSOLE_MAGENTA_OUTPUT").foreground(palette.purple());
				private final Attribute red_bright = new Attribute("CONSOLE_RED_BRIGHT_OUTPUT").foreground(palette.red().brighter());
				private final Attribute red = new Attribute("CONSOLE_RED_OUTPUT").foreground(palette.red());
				private final Attribute white = new Attribute("CONSOLE_WHITE_OUTPUT").foreground(style.scheme().foreground().base());
				private final Attribute yellow_bright = new Attribute("CONSOLE_YELLOW_BRIGHT_OUTPUT").foreground(palette.yellow().brighter());
				private final Attribute yellow = new Attribute("CONSOLE_YELLOW_OUTPUT").foreground(palette.yellow());

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						black, blue_bright, blue, cyan_bright, cyan, darkgray, gray, green_bright, green,
						magenta_bright, magenta, red_bright, red, white, yellow_bright, yellow
					);
				}
			}

			class Console implements Group {
				private final Attribute error = new Attribute("CONSOLE_ERROR_OUTPUT").foreground(style.error());
				private final Attribute system = new Attribute("CONSOLE_SYSTEM_OUTPUT").foreground(palette.yellow().darker());
				private final Attribute user_input = new Attribute("CONSOLE_USER_INPUT").foreground(style.scheme().foreground().base());
				private final Attribute normal = new Attribute("CONSOLE_NORMAL_OUTPUT").foreground(style.scheme().foreground().base());

				@Override public Collection<Attribute> attributes() {
					return Set.of(error, system, user_input, normal);
				}
			}

			class LogConsole implements Group {
				private final Attribute error_output = new Attribute("LOG_ERROR_OUTPUT").foreground(style.error());
				private final Attribute expired_entry = new Attribute("LOG_EXPIRED_ENTRY").foreground(palette.gray());
				private final Attribute warning_output = new Attribute("LOG_WARNING_OUTPUT").foreground(style.warning());

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						error_output,
						expired_entry,
						warning_output
					);
				}
			}
		}

		class DiffAndMerge implements Group {
			private final Attribute inserted = new Attribute("DIFF_INSERTED").background(palette.green().brighter(3)).errorStripe(palette.green().brighter(2));
			private final Attribute modified = new Attribute("DIFF_MODIFIED").background(palette.blue().brighter(3)).errorStripe(palette.blue().brighter(2));
			private final Attribute deleted = new Attribute("DIFF_DELETED").background(palette.silver().brighter()).errorStripe(palette.silver());
			private final Attribute conflict = new Attribute("DIFF_CONFLICT").background(palette.red().brighter(3)).errorStripe(palette.red().brighter(2));

			@Override public Collection<Attribute> attributes() {
				return Set.of(inserted, modified, deleted, conflict);
			}
		}

		class Language implements Group {

			Java java() { return new Java(); }
			Groovy groovy() { return new Groovy(); }
			Go go() { return new Go(); }
			TypeScript typeScript() { return new TypeScript(); }
			JavaScript javaScript() { return new JavaScript(); }
			Python python() { return new Python(); }
			Php php() { return new Php(); }
			UserDefinedFileTypes userDefinedFileTypes() { return new UserDefinedFileTypes(); }

			@Override public Collection<Attribute> attributes() {
				return mergeAttributes(java(), groovy(), go(), typeScript(), javaScript(), python(), php(), userDefinedFileTypes());
			}

			class Java implements Group {
				private final Attribute abstract_class_name_attributes = new Attribute("ABSTRACT_CLASS_NAME_ATTRIBUTES").copy(Attributes.this.languageDefaults().interface_name);
				private final Attribute annotation_attribute_name_attributes = new Attribute("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES").foreground(palette.purple());
				private final Attribute annotation_name_attributes = new Attribute("ANNOTATION_NAME_ATTRIBUTES").baseAttributes("DEFAULT_METADATA");
				private final Attribute deprecated_attributes = new Attribute("DEPRECATED_ATTRIBUTES").foreground(deprecated).strikeout(deprecated).italic();
				private final Attribute implicit_anonymous_class_parameter_attributes = new Attribute("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES").copy(Attributes.this.languageDefaults().parameter);
				private final Attribute instance_field_attributes = new Attribute("INSTANCE_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_INSTANCE_FIELD");
				private final Attribute static_field_attributes = new Attribute("STATIC_FIELD_ATTRIBUTES").baseAttributes("DEFAULT_STATIC_FIELD");
				private final Attribute static_final_field_attributes = new Attribute("STATIC_FINAL_FIELD_ATTRIBUTES").baseAttributes("STATIC_FIELD_ATTRIBUTES");
				private final Attribute type_parameter_name_attributes = new Attribute("TYPE_PARAMETER_NAME_ATTRIBUTES").foreground(style.scheme().foreground().base()).bold();

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						abstract_class_name_attributes,
						annotation_attribute_name_attributes,
						annotation_name_attributes,
						deprecated_attributes,
						implicit_anonymous_class_parameter_attributes,
						instance_field_attributes,
						static_field_attributes,
						static_final_field_attributes,
						type_parameter_name_attributes
					);
				}
			}

			class Groovy implements Group {
				private final Attribute groovy_keyword = new Attribute("GROOVY_KEYWORD").baseAttributes("JAVA_KEYWORD");
				private final Attribute list_map_to_object_conversion = new Attribute("List/map to object conversion").copy(Attributes.this.languageDefaults().identifier);
				private final Attribute static_property_reference_ID = new Attribute("Static property reference ID").baseAttributes("STATIC_FINAL_FIELD_ATTRIBUTES");
				private final Attribute unresolved_reference_access = new Attribute("Unresolved reference access").copy(Attributes.this.general().errorsAndWarnings().not_used_element_attributes);

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						groovy_keyword,
						list_map_to_object_conversion,
						static_property_reference_ID,
						unresolved_reference_access
					);
				}
			}

			class Go implements Group {
				private final Attribute builtin_constant = new Attribute("GO_BUILTIN_CONSTANT").copy(Attributes.this.languageDefaults().keyword);
				private final Attribute builtin_variable = new Attribute("GO_BUILTIN_VARIABLE").copy(Attributes.this.languageDefaults().keyword);
				private final Attribute comment_reference = new Attribute("GO_COMMENT_REFERENCE").copy(Attributes.this.languageDefaults().doc_comment_tag_value);
				private final Attribute function_parameter = new Attribute("GO_FUNCTION_PARAMETER").copy(Attributes.this.languageDefaults().parameter);
				private final Attribute local_constant = new Attribute("GO_LOCAL_CONSTANT").foreground(palette.purple());
				private final Attribute method_receiver = new Attribute("GO_METHOD_RECEIVER").foreground(palette.purple().darker()).bold();
				private final Attribute package_exported_constant = new Attribute("GO_PACKAGE_EXPORTED_CONSTANT").foreground(palette.purple().darker()).bold();
				private final Attribute package_exported_variable = new Attribute("GO_PACKAGE_EXPORTED_VARIABLE").foreground(palette.purple().darker()).bold();
				private final Attribute package_local_constant = new Attribute("GO_PACKAGE_LOCAL_CONSTANT").foreground(palette.purple().darker()).bold();
				private final Attribute package_local_variable = new Attribute("GO_PACKAGE_LOCAL_VARIABLE").foreground(palette.purple().darker()).bold();
				private final Attribute shadowing_variable = new Attribute("GO_SHADOWING_VARIABLE").foreground(palette.purple()).dottedLine(palette.purple());
				private final Attribute struct_exported_member = new Attribute("GO_STRUCT_EXPORTED_MEMBER").copy(Attributes.this.languageDefaults().instance_field);
				private final Attribute struct_local_member = new Attribute("GO_STRUCT_LOCAL_MEMBER").copy(Attributes.this.languageDefaults().instance_field);

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						builtin_constant,
						builtin_variable,
						comment_reference,
						function_parameter,
						local_constant,
						method_receiver,
						package_exported_constant,
						package_exported_variable,
						package_local_constant,
						package_local_variable,
						shadowing_variable,
						struct_exported_member,
						struct_local_member
					);
				}
			}

			class TypeScript implements Group {
				private final Attribute type_parameter = new Attribute("TS.TYPE_PARAMETER").copy(Attributes.this.language().java().type_parameter_name_attributes);
				private final Attribute type_guard = new Attribute("TS.TYPE_GUARD").emptyValue();

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						type_parameter,
						type_guard
					);
				}
			}

			class JavaScript implements Group {
				private final Attribute global_variable = new Attribute("JS.GLOBAL_VARIABLE").baseAttributes("DEFAULT_PARAMETER");
				private final Attribute global_function = new Attribute("JS.GLOBAL_FUNCTION").baseAttributes("DEFAULT_FUNCTION_DECLARATION");
				private final Attribute injected_language_fragment = new Attribute("JavaScript:INJECTED_LANGUAGE_FRAGMENT").baseAttributes("INJECTED_LANGUAGE_FRAGMENT");
				private final Attribute instance_member_function = new Attribute("JS.INSTANCE_MEMBER_FUNCTION").baseAttributes("DEFAULT_INSTANCE_METHOD");
				private final Attribute local_variable = new Attribute("JS.LOCAL_VARIABLE").baseAttributes("DEFAULT_LOCAL_VARIABLE");
				private final Attribute parameter = new Attribute("JS.PARAMETER").baseAttributes("DEFAULT_PARAMETER");
				private final Attribute regex = new Attribute("JS.REGEXP").copy(Attributes.this.languageDefaults().valid_string_escape);

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						global_variable,
						global_function,
						injected_language_fragment,
						instance_member_function,
						local_variable,
						parameter,
						regex
					);
				}
			}

			class Python implements Group {
				private final Attribute decorator = new Attribute("PY.DECORATOR").copy(Attributes.this.languageDefaults().metadata);
				private final Attribute keyword_argument = new Attribute("PY.KEYWORD_ARGUMENT").copy(Attributes.this.languageDefaults().local_variable);
				private final Attribute predefined_definition = new Attribute("PY.PREDEFINED_DEFINITION").baseAttributes("DEFAULT_PREDEFINED_SYMBOL");
				private final Attribute predefined_usage = new Attribute("PY.PREDEFINED_USAGE").baseAttributes("DEFAULT_PREDEFINED_SYMBOL");
				private final Attribute self_parameter = new Attribute("PY.SELF_PARAMETER").baseAttributes("DEFAULT_PARAMETER");
				private final Attribute string_u = new Attribute("PY.STRING.U").baseAttributes("DEFAULT_STRING");

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						decorator,
						keyword_argument,
						predefined_definition,
						predefined_usage,
						self_parameter,
						string_u
					);
				}
			}

			class Php implements Group {
				private final Attribute magic_member_access = new Attribute("MAGIC_MEMBER_ACCESS").copy(Attributes.this.general().errorsAndWarnings().not_used_element_attributes);
				private final Attribute doc_parameter = new Attribute("PHP_DOC_PARAMETER").copy(Attributes.this.languageDefaults().doc_comment_tag_value);
				private final Attribute exec_command_id = new Attribute("PHP_EXEC_COMMAND_ID").background(palette.silver().brighter(3));
				private final Attribute named_argument = new Attribute("PHP_NAMED_ARGUMENT").copy(Attributes.this.languageDefaults().local_variable);
				private final Attribute parameter = new Attribute("PHP_PARAMETER").baseAttributes("DEFAULT_PARAMETER");
				private final Attribute var = new Attribute("PHP_VAR").baseAttributes("DEFAULT_LOCAL_VARIABLE");

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						magic_member_access,
						doc_parameter,
						exec_command_id,
						named_argument,
						parameter,
						var
					);
				}
			}

			class UserDefinedFileTypes implements Group {
				private final Attribute invalid_string_escape_attributes = new Attribute("CUSTOM_INVALID_STRING_ESCAPE_ATTRIBUTES").baseAttributes("DEFAULT_INVALID_STRING_ESCAPE");
				private final Attribute keyword1_attributes = new Attribute("CUSTOM_KEYWORD1_ATTRIBUTES").baseAttributes("DEFAULT_KEYWORD");
				private final Attribute keyword2_attributes = new Attribute("CUSTOM_KEYWORD2_ATTRIBUTES").copy(Attributes.this.languageDefaults().parameter);
				private final Attribute keyword3_attributes = new Attribute("CUSTOM_KEYWORD3_ATTRIBUTES").copy(Attributes.this.languageDefaults().parameter);
				private final Attribute keyword4_attributes = new Attribute("CUSTOM_KEYWORD4_ATTRIBUTES").copy(Attributes.this.languageDefaults().parameter);
				private final Attribute line_comment_attributes = new Attribute("CUSTOM_LINE_COMMENT_ATTRIBUTES").baseAttributes("DEFAULT_LINE_COMMENT");
				private final Attribute multi_line_comment_attributes = new Attribute("CUSTOM_MULTI_LINE_COMMENT_ATTRIBUTES").baseAttributes("DEFAULT_BLOCK_COMMENT");
				private final Attribute number_attributes = new Attribute("CUSTOM_NUMBER_ATTRIBUTES").baseAttributes("DEFAULT_NUMBER");
				private final Attribute string_attributes = new Attribute("CUSTOM_STRING_ATTRIBUTES").baseAttributes("DEFAULT_STRING");
				private final Attribute valid_string_escape_attributes = new Attribute("CUSTOM_VALID_STRING_ESCAPE_ATTRIBUTES").baseAttributes("DEFAULT_VALID_STRING_ESCAPE");

				@Override public Collection<Attribute> attributes() {
					return Set.of(
						invalid_string_escape_attributes,
						keyword1_attributes,
						keyword2_attributes,
						keyword3_attributes,
						keyword4_attributes,
						line_comment_attributes,
						multi_line_comment_attributes,
						number_attributes,
						string_attributes,
						valid_string_escape_attributes
					);
				}
			}
		}
	}
}
