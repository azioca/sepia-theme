package plugin.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class Text {

	private final ObjectMapper objectMapper;
	private final Object object;

	public Text(ObjectMapper objectMapper, Object object) {
		this.objectMapper = objectMapper
			.disable(MapperFeature.AUTO_DETECT_FIELDS)
			.disable(MapperFeature.AUTO_DETECT_GETTERS)
			.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.enable(SerializationFeature.INDENT_OUTPUT);
		this.object = object;
	}

	public String asString() throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
}
