package plugin.intellij.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.Objects;

class Property {
	private final String name;
	private final String text;

	Property(String name, String text) {
		this.name = Objects.requireNonNull(name);
		this.text = Objects.requireNonNull(text);
	}
	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlText public String text() { return text; }
}
