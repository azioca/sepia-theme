package plugin.scheme;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import plugin.domain.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class AttributeOption {

	private final String name;
	private final Value value;

	public AttributeOption(String name) {
		this(name, new Value());
	}

	private AttributeOption(String name, Value value) {
		this.name = Objects.requireNonNull(name);
		this.value = Objects.requireNonNull(value);
	}

	AttributeOption foreground(Color foreground) {
		return new AttributeOption(name, value.foreground(foreground));
	}

	AttributeOption background(Color background) {
		return new AttributeOption(name, value.background(background));
	}

	AttributeOption fontType(Integer fontType) {
		return new AttributeOption(name, value.fontType(fontType));
	}

	AttributeOption errorStripeColor(Color errorStripeColor) {
		return new AttributeOption(name, value.errorStripeColor(errorStripeColor));
	}

	AttributeOption effectColor(Color effectColor) {
		return new AttributeOption(name, value.effectColor(effectColor));
	}

	AttributeOption effectType(Integer effectType) {
		return new AttributeOption(name, value.effectType(effectType));
	}

	@JacksonXmlProperty(isAttribute = true) String name() { return name; }
	@JacksonXmlProperty Value value() { return value; }

	private static class Value {

		Color foreground;
		Color background;
		Integer fontType;
		Color errorStripeColor;
		Color effectColor;
		Integer effectType;

		Value() {}

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

		@JacksonXmlElementWrapper(useWrapping = false)
		List<Object> option() {
			ArrayList<Object> options = new ArrayList<>();
			if (foreground != null) options.add(new ColorOption("FOREGROUND", foreground));
			if (background != null) options.add(new ColorOption("BACKGROUND", background));
			if (fontType != null) options.add(new Value.NumberOption("FONT_TYPE", fontType));
			if (errorStripeColor != null) options.add(new ColorOption("ERROR_STRIPE_COLOR", errorStripeColor));
			if (effectColor != null) options.add(new ColorOption("EFFECT_COLOR", effectColor));
			if (effectType != null) options.add(new Value.NumberOption("EFFECT_TYPE", effectType));
			return options;
		}

		static class NumberOption {
			private final String name;
			private final int number;

			NumberOption(String name, int number) {
				this.name = Objects.requireNonNull(name);
				this.number = number;
			}

			@JacksonXmlProperty(isAttribute = true) String name() { return name; }
			@JacksonXmlProperty(isAttribute = true) String number() { return Integer.toString(number); }
		}
	}
}
