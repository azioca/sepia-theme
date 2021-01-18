import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

class AsMap {

	private final Object object;

	AsMap(Object object) {this.object = object;}

	Map map() {
		ObjectMapper objectMapper = new ObjectMapper()
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE)
			.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
		return objectMapper.convertValue(object, Map.class);
	}
}
