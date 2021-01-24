package plugin;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class AsString {

	static {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
	}

	private final Object object;

	public AsString(Object object) {
		this.object = object;
	}

	public String string() {
		return "{" + ReflectionToStringBuilder.toString(object) + "}";
	}
}
