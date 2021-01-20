package plugin.domain;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Xml extends Text {

	public Xml(Object object) {
		super(new XmlMapper(), object);
	}
}
