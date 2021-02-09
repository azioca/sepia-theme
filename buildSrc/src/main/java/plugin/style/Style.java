package plugin.style;

import plugin.domain.Color;
import plugin.domain.Palette;
import plugin.style.scheme.Scheme;
import plugin.style.theme.Button;
import plugin.style.theme.Checkbox;
import plugin.style.theme.Scrollbar;
import plugin.style.theme.Tab;
import plugin.style.theme.Table;
import plugin.style.theme.Theme;

import static java.util.Objects.requireNonNull;

public class Style {

	private final Palette palette;

	public Style(Palette palette) {
		this.palette = requireNonNull(palette);
	}

	public Theme theme() {
		return new Theme(palette);
	}

	public Scheme scheme() {
		return new Scheme(palette);
	}

	public Color searchBackground() {
		return palette.aqua().brighter();
	}

	public Color borderColor() {
		return theme().background().base().darker(3);
	}

	public Color editorLines() {
		return palette.gray().brighter(3);
	}

	public Color success() {
		return palette.aqua().brighter();
	}

	public Color warning() {
		return palette.yellow().brighter();
	}

	public Color error() {
		return palette.red();
	}

	public Button button() {
		return new Button(this);
	}

	public Checkbox checkbox() {
		return new Checkbox(this);
	}

	public Scrollbar scrollbar() {
		return new Scrollbar(theme().background().base());
	}

	public Color link() {
		return palette.blue();
	}

	public Tab tab() {
		return new Tab(this);
	}

	public Table table() {
		return new Table(this);
	}
}
