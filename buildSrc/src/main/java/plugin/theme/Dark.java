package plugin.theme;

import com.fasterxml.jackson.annotation.JsonProperty;
import plugin.domain.Color;

class Dark {
	@JsonProperty Color Dark;
	Dark(Color dark) {
		Dark = java.util.Objects.requireNonNull(dark);
	}
}
