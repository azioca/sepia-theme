package plugin.lang;

import java.util.function.Predicate;

public final class Check {

	public static <T> T argument(T argument, Predicate<T> should, String shouldMessage) {
		if (!should.test(argument)) {
			throw new IllegalArgumentException("Argument: " + argument + ", " + shouldMessage);
		}
		return argument;
	}

	public static void state(boolean expectedState, String shouldMessage) {
		if (!expectedState) {
			throw new IllegalStateException(shouldMessage);
		}
	}
}
