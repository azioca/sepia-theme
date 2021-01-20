package plugin.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Attribute {

	private final String name;
	private final String baseAttributes;
	private final Value value;

	public Attribute(String name) {
		this(name, null, null);
	}

	private Attribute(String name, String baseAttributes, Value value) {
		this.name = Objects.requireNonNull(name);
		this.baseAttributes = baseAttributes;
		this.value = value;
	}

	Attribute baseAttributes(String baseAttributes) {
		return new Attribute(name, baseAttributes, value);
	}

	Attribute foreground(plugin.domain.Color foreground) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().foreground(foreground));
	}

	Attribute background(plugin.domain.Color background) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().background(background));
	}

	Attribute fontType(Integer fontType) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().fontType(fontType));
	}

	Attribute errorStripeColor(plugin.domain.Color errorStripeColor) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().errorStripeColor(errorStripeColor));
	}

	Attribute effectColor(plugin.domain.Color effectColor) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().effectColor(effectColor));
	}

	Attribute effectType(Integer effectType) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().effectType(effectType));
	}

	private Value atLeastEmptyValue() {
		return value != null ? value : new Value();
	}

	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlProperty(isAttribute = true) String baseAttributes() { return baseAttributes; }
	@JacksonXmlProperty Value value() { return value; }

	private static class Value {

		plugin.domain.Color foreground;
		plugin.domain.Color background;
		Integer fontType;
		plugin.domain.Color errorStripeColor;
		plugin.domain.Color effectColor;
		Integer effectType;

		Value() {}

		Value(plugin.domain.Color foreground, plugin.domain.Color background, Integer fontType, plugin.domain.Color errorStripeColor, plugin.domain.Color effectColor, Integer effectType) {
			this.foreground = foreground;
			this.background = background;
			this.fontType = fontType;
			this.errorStripeColor = errorStripeColor;
			this.effectColor = effectColor;
			this.effectType = effectType;
		}

		Value foreground(plugin.domain.Color foreground) {
			return new Value(foreground, this.background, this.fontType, this.errorStripeColor, this.effectColor, this.effectType);
		}

		Value background(plugin.domain.Color background) {
			return new Value(foreground, background, this.fontType, this.errorStripeColor, this.effectColor, this.effectType);
		}

		Value fontType(Integer fontType) {
			return new Value(foreground, this.background, fontType, this.errorStripeColor, this.effectColor, this.effectType);
		}

		Value errorStripeColor(plugin.domain.Color errorStripeColor) {
			return new Value(foreground, this.background, this.fontType, errorStripeColor, this.effectColor, this.effectType);
		}

		Value effectColor(plugin.domain.Color effectColor) {
			return new Value(foreground, this.background, this.fontType, this.errorStripeColor, effectColor, this.effectType);
		}

		Value effectType(Integer effectType) {
			return new Value(foreground, this.background, this.fontType, this.errorStripeColor, this.effectColor, effectType);
		}

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Object> option() {
			ArrayList<Object> options = new ArrayList<>();
			if (foreground != null) options.add(new Option.Color("FOREGROUND", foreground));
			if (background != null) options.add(new Option.Color("BACKGROUND", background));
			if (fontType != null) options.add(new Option.Number("FONT_TYPE", fontType));
			if (errorStripeColor != null) options.add(new Option.Color("ERROR_STRIPE_COLOR", errorStripeColor));
			if (effectColor != null) options.add(new Option.Color("EFFECT_COLOR", effectColor));
			if (effectType != null) options.add(new Option.Number("EFFECT_TYPE", effectType));
			return options;
		}
	}
}
