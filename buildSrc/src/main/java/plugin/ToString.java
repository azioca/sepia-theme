package plugin;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class ToString {

	static {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static String toString(Object object) {
		return "{" + ReflectionToStringBuilder.toString(object) + "}";
	}

	public static String toString(Object object, String description) {
		return "{" + ReflectionToStringBuilder.toString(object) + " (" + description + ")}";
	}
}
