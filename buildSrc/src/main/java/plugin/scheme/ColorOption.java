package plugin.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import plugin.domain.Color;

import java.util.Objects;

class ColorOption {
	private final String name;
	private final Color color;

	ColorOption(String name, Color value) {
		this.name = Objects.requireNonNull(name);
		this.color = Objects.requireNonNull(value);
	}

	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlProperty(isAttribute = true) Color value() { return color; }
}
