package theme;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json extends Text {

	public Json(Object object) {
		super(new ObjectMapper(), object);
	}
}
