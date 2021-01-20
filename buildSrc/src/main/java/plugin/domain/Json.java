package plugin.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json extends Text {

	public Json(Object object) {
		super(new ObjectMapper().setDefaultPrettyPrinter(prettyPrinter()), object);
	}

	private static PrettyPrinter prettyPrinter() {
		DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("\t", DefaultIndenter.SYS_LF);
		DefaultPrettyPrinter base = new DefaultPrettyPrinter().withObjectIndenter(indenter).withArrayIndenter(indenter);
		return new SingleSpacePrettyPrinter(base);
	}

	private static class SingleSpacePrettyPrinter extends DefaultPrettyPrinter {

		SingleSpacePrettyPrinter(DefaultPrettyPrinter base) {
			super(base);
		}

		@Override
		public DefaultPrettyPrinter createInstance() {
			return new SingleSpacePrettyPrinter(this);
		}

		@Override
		public void writeObjectFieldValueSeparator(JsonGenerator g) throws IOException {
			g.writeRaw(_separators.getObjectFieldValueSeparator() + " ");
		}
	}
}
