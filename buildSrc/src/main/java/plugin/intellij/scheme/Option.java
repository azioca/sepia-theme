package plugin.intellij.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public interface Option {

	@JacksonXmlProperty(isAttribute = true) String name();
	@JacksonXmlProperty(isAttribute = true) String value();

	class Number implements Option {
		private final String name;
		private final int number;

		Number(String name, int number) {
			this.name = Objects.requireNonNull(name);
			this.number = number;
		}

		@JacksonXmlProperty(isAttribute = true) public String name() { return name; }
		@JacksonXmlProperty(isAttribute = true) public String value() { return Integer.toString(number); }
	}

	class Color implements Option {
		private final String name;
		private final plugin.domain.Color color;

		Color(String name, plugin.domain.Color value) {
			this.name = Objects.requireNonNull(name);
			this.color = Objects.requireNonNull(value);
		}

		@JacksonXmlProperty(isAttribute = true) public String name() { return name; }
		@JacksonXmlProperty(isAttribute = true) public String value() { return color.string(); }
	}
}
