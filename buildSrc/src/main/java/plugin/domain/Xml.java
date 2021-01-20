package plugin.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Xml extends Text {

	public Xml(Object object) {
		super(new XmlMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY), object);
	}
}
