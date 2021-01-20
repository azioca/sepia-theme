package plugin.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import plugin.domain.Palette;

import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "scheme")
public class Scheme {
	private final String name;
	private final Palette palette;

	public Scheme(String name, Palette palette) {
		this.name = Objects.requireNonNull(name);
		this.palette = Objects.requireNonNull(palette);
	}

	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlProperty(isAttribute = true) String version() { return "142"; }
	@JacksonXmlProperty(isAttribute = true) String parent_scheme() { return "Default"; }
	@JacksonXmlProperty public MetaInfo metaInfo() { return new MetaInfo(); }

	static class Property {
		private final String name;
		private final String text;

		Property(String name, String text) {
			this.name = Objects.requireNonNull(name);
			this.text = Objects.requireNonNull(text);
		}
		@JacksonXmlProperty(isAttribute = true) public String name() { return name; }
		@JacksonXmlText public String text() { return text; }
	}

	class MetaInfo {

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Property> property = List.of(
			new Property("ide", "Idea"),
			new Property("ideVersion", "2020.3.1.0.0"),
			new Property("originalScheme", name)
		);
	}
}
