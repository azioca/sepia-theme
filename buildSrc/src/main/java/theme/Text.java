package theme;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class Text {

	private final ObjectMapper objectMapper;
	private final Object object;

	public Text(ObjectMapper objectMapper, Object object) {
		this.objectMapper = objectMapper
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE)
			.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE)
			.enable(SerializationFeature.INDENT_OUTPUT);
		this.object = object;
	}

	public String text() throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
}
