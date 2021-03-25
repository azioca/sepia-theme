package plugin.intellij.scheme;

import java.util.Collection;
import java.util.Set;

interface Group {

	default Collection<Option.Color> colors() {
		return Set.of();
	}

	default Collection<Attribute> attributes() {
		return Set.of();
	}
}
