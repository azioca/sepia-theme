package plugin.domain;

import java.util.Objects;

public interface Palette {
	Color sepia();
	Color black();
	Color gray();
	Color red();
	Color green();
	Color yellow();
	Color blue();
	Color purple();
	Color aqua();
	Color orange();

	class Hex implements Palette {

		private final Palette palette;

		public Hex(Palette palette) {this.palette = Objects.requireNonNull(palette);}

		@Override
		public Color sepia() {
			return palette.sepia().hex();
		}

		@Override
		public Color black() {
			return palette.black().hex();
		}

		@Override
		public Color gray() {
			return palette.gray().hex();
		}

		@Override
		public Color red() {
			return palette.red().hex();
		}

		@Override
		public Color green() {
			return palette.green().hex();
		}

		@Override
		public Color yellow() {
			return palette.yellow().hex();
		}

		@Override
		public Color blue() {
			return palette.blue().hex();
		}

		@Override
		public Color purple() {
			return palette.purple().hex();
		}

		@Override
		public Color aqua() {
			return palette.aqua().hex();
		}

		@Override
		public Color orange() {
			return palette.orange().hex();
		}
	}

	class Plain implements Palette {

		private final Palette palette;

		Plain(Palette palette) {this.palette = palette;}

		@Override
		public Color sepia() {
			return palette.sepia().plain();
		}

		@Override
		public Color black() {
			return palette.black().plain();
		}

		@Override
		public Color gray() {
			return palette.gray().plain();
		}

		@Override
		public Color red() {
			return palette.red().plain();
		}

		@Override
		public Color green() {
			return palette.green().plain();
		}

		@Override
		public Color yellow() {
			return palette.yellow().plain();
		}

		@Override
		public Color blue() {
			return palette.blue().plain();
		}

		@Override
		public Color purple() {
			return palette.purple().plain();
		}

		@Override
		public Color aqua() {
			return palette.aqua().plain();
		}

		@Override
		public Color orange() {
			return palette.orange().plain();
		}
	}
}
