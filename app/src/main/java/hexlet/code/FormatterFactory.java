package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public final class FormatterFactory {
    private FormatterFactory() {
        throw new UnsupportedOperationException(
            "Instantiation of "
            + FormatterFactory.class.getName()
            + " is not allowed"
        );
    }

    /**
     * @param name the name of the formatter (e.g., "stylish", "plain", "json").
     * @return a corresponding Formatter implementation.
     * @throws IllegalArgumentException if the formatter name is unknown.
     */
    public static Formatter createFormatter(final String name) {
        return switch (name.toLowerCase()) {
            case App.FORMAT_STYLISH -> new Stylish();
            case App.FORMAT_PLAIN -> new Plain();
            default -> throw new IllegalArgumentException(
                "Unknown formatter: " + name
            );
        };
    }
}
