package plugin.intellij.scheme;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class Group {

	abstract protected Set<Option.Color> colors();
	abstract protected Set<Attribute> attributes();

	@SafeVarargs
	protected final <T> Set<T> union(Set<T>... sets) {
		return Stream.of(sets).flatMap(Collection::stream).collect(Collectors.toSet());
	}

	static class Union extends Group {

		private final Set<Group> groups;

		public Union(Group... groups) {
			this(new HashSet<>(Arrays.asList(groups)));
		}

		public Union(Set<Group> groups) {
			this.groups = Objects.requireNonNull(groups);
		}

		@Override
		public Set<Option.Color> colors() {
			return groups.stream().map(Group::colors).flatMap(Collection::stream).collect(Collectors.toSet());
		}

		@Override
		public Set<Attribute> attributes() {
			return groups.stream().map(Group::attributes).flatMap(Collection::stream).collect(Collectors.toSet());
		}
	}
}
