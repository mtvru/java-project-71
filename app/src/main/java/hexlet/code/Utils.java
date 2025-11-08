package hexlet.code;

import java.util.List;
import java.util.Map;

public final class Utils {
    private Utils() {
        throw new UnsupportedOperationException(
            "Instantiation of " + Utils.class.getName() + " is not allowed"
        );
    }

    /**
     * Checks whether the given value is an object (Map) or an array (List).
     * Used for top-level JSON/YAML structures.
     *
     * @param value the value associated with a key from a Map.
     * @return true if the value is an object or an array.
     */
    public static boolean isObjectOrArray(final Object value) {
        return value instanceof Map<?, ?> || value instanceof List<?>;
    }
}
