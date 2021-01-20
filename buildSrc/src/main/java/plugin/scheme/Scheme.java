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
			new Attribute("BAD_CHARACTER").effectColor(palette.red()).effectType(2)
		);
	}
}
