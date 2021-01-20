package plugin.scheme;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import plugin.domain.Color;
import plugin.domain.Palette;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"metaInfo", "colors"})
@JacksonXmlRootElement(localName = "scheme")
public class Scheme {
	private final String name;
	private final Palette palette;
	private final Color background;
	private final Color foreground;

	public Scheme(String name, Palette palette, Color background, Color foreground) {
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
		List<ColorOption> option = List.of(
			new ColorOption("ADDED_LINES_COLOR", palette.green().brighter()),
			new ColorOption("ANNOTATIONS_COLOR", foreground),
			new ColorOption("CARET_COLOR", foreground),
			new ColorOption("CARET_ROW_COLOR", background.darker(2)),
			new ColorOption("CONSOLE_BACKGROUND_KEY", background),
			new ColorOption("DELETED_LINES_COLOR", palette.gray()),
			new ColorOption("DOCUMENTATION_COLOR", background),
			new ColorOption("GUTTER_BACKGROUND", background),
			new ColorOption("IGNORED_ADDED_LINES_BORDER_COLOR", palette.green().brighter()),
			new ColorOption("IGNORED_DELETED_LINES_BORDER_COLOR", palette.gray()),
			new ColorOption("IGNORED_MODIFIED_LINES_BORDER_COLOR", palette.blue().brighter()),
			new ColorOption("INDENT_GUIDE", background.darker(5)),
			new ColorOption("LINE_NUMBERS_COLOR", palette.gray()),
			new ColorOption("MODIFIED_LINES_COLOR", palette.blue().brighter()),
			new ColorOption("RIGHT_MARGIN_COLOR", background.darker().darker()),
			new ColorOption("SELECTED_INDENT_GUIDE", background.darker(5)),
			new ColorOption("SELECTED_TEARLINE_COLOR", palette.aqua().darker()),
			new ColorOption("SELECTION_BACKGROUND", background.darker(4)),
			new ColorOption("SELECTION_FOREGROUND", foreground),
			new ColorOption("TEARLINE_COLOR", background.darker(5)),
			new ColorOption("VCS_ANNOTATIONS_COLOR_1", background),
			new ColorOption("VCS_ANNOTATIONS_COLOR_2", background.darker()),
			new ColorOption("VCS_ANNOTATIONS_COLOR_3", background.darker(2)),
			new ColorOption("VCS_ANNOTATIONS_COLOR_4", background.darker(3)),
			new ColorOption("VCS_ANNOTATIONS_COLOR_5", background.darker(4)),
			new ColorOption("WHITESPACES_MODIFIED_LINES_COLOR", background.darker(3))
		);
	}

	class Attributes {
		@JacksonXmlElementWrapper(useWrapping = false)
		List<AttributeOption> option = List.of(
			new AttributeOption("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES").foreground(palette.purple())
		);
	}
}
