package plugin.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import plugin.domain.Color;

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

	Attribute foreground(Color foreground) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().foreground(foreground));
	}

	Attribute background(Color background) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().background(background));
	}

	Attribute bold() {
		return fontType(1);
	}

	Attribute italic() {
		return fontType(2);
	}

	private Attribute fontType(Integer fontType) {
		Value value = atLeastEmptyValue();
		return new Attribute(name, baseAttributes, value.fontType(atLeastEmptyValue().fontType() != null ? value.fontType() + fontType : fontType));
	}

	Attribute errorStripeColorAsForeground() {
		return errorStripeColor(value().foreground());
	}

	Attribute errorStripeColorAsBackground() {
		return errorStripeColor(value().background());
	}

	Attribute errorStripeColor(Color errorStripeColor) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().errorStripeColor(errorStripeColor));
	}

	Attribute underscored(Color effectColor) {
		return effectType(1).effectColor(effectColor);
	}

	Attribute boldUnderscored(Color effectColor) {
		return effectType(4).effectColor(effectColor);
	}

	Attribute underwaved(Color effectColor) {
		return effectType(2).effectColor(effectColor);
	}

	Attribute bordered(Color effectColor) {
		return effectType(6).effectColor(effectColor);
	}

	Attribute strikeout(Color effectColor) {
		return effectType(3).effectColor(effectColor);
	}

	Attribute dottedLine(Color effectColor) {
		return effectType(5).effectColor(effectColor);
	}

	private Attribute effectType(Integer effectType) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().effectType(effectType));
	}

	private Attribute effectColor(Color effectColor) {
		return new Attribute(name, baseAttributes, atLeastEmptyValue().effectColor(effectColor));
	}

	private Value atLeastEmptyValue() {
		return value != null ? value : new Value();
	}

	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlProperty(isAttribute = true) String baseAttributes() { return baseAttributes; }
	@JacksonXmlProperty Value value() { return value; }

	private static class Value {

		private final Color foreground;
		private final Color background;
		private final Integer fontType;
		private final Color errorStripeColor;
		private final Color effectColor;
		private final Integer effectType;

		Value() {
			this.foreground = null;
			this.background = null;
			this.fontType = null;
			this.errorStripeColor = null;
			this.effectColor = null;
			this.effectType = null;
		}

		Value(Color foreground, Color background, Integer fontType, Color errorStripeColor, Color effectColor, Integer effectType) {
			this.foreground = foreground;
			this.background = background;
			this.fontType = fontType;
			this.errorStripeColor = errorStripeColor;
			this.effectColor = effectColor;
			this.effectType = effectType;
		}

		Value foreground(Color foreground) {
			return new Value(foreground, this.background, this.fontType, this.errorStripeColor, this.effectColor, this.effectType);
		}

		Value background(Color background) {
			return new Value(foreground, background, this.fontType, this.errorStripeColor, this.effectColor, this.effectType);
		}

		Value fontType(Integer fontType) {
			return new Value(foreground, this.background, fontType, this.errorStripeColor, this.effectColor, this.effectType);
		}

		Value errorStripeColor(Color errorStripeColor) {
			return new Value(foreground, this.background, this.fontType, errorStripeColor, this.effectColor, this.effectType);
		}

		Value effectColor(Color effectColor) {
			return new Value(foreground, this.background, this.fontType, this.errorStripeColor, effectColor, this.effectType);
		}

		Value effectType(Integer effectType) {
			return new Value(foreground, this.background, this.fontType, this.errorStripeColor, this.effectColor, effectType);
		}

		private Color foreground() { return foreground; }
		private Color background() { return background; }
		private Integer fontType() { return fontType; }
		private Color errorStripeColor() { return errorStripeColor; }
		private Color effectColor() { return effectColor; }
		private Integer effectType() { return effectType; }

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
