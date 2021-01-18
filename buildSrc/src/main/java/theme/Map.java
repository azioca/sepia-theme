package theme;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Map {

	private final Object object;

	public Map(Object object) {this.object = object;}

	public java.util.Map string() {
		ObjectMapper objectMapper = new ObjectMapper()
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE)
			.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE)
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return objectMapper.convertValue(object, java.util.Map.class);
	}
}
