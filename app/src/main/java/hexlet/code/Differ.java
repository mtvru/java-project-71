package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.List;
import java.util.Map;

public final class Differ {
    /**
     * Default stylish format.
     */
    public static final String FORMAT_STYLISH = "stylish";
    /**
     * Plain format.
     */
    public static final String FORMAT_PLAIN = "plain";
    /**
     * Json format.
     */
    public static final String FORMAT_JSON = "json";

    private Differ() {
        throw new UnsupportedOperationException(
            "Instantiation of " + Differ.class.getName() + " is not allowed"
        );
    }

    /**
     * @param filePath1 the path to the first file.
     * @param filePath2 the path to the second file.
     * @return a formatted diff string.
     * @throws Exception if either file does not exist or cannot be read.
     */
    public static String generate(
        final String filePath1,
        final String filePath2
    ) throws Exception {
        return generate(filePath1, filePath2, FORMAT_STYLISH);
    }

    /**
     * @param filePath1 the path to the first file.
     * @param filePath2 the path to the second file.
     * @param formatName the name of the output format.
     * @return a formatted diff string.
     * @throws Exception if either file does not exist or cannot be read.
     */
    public static String generate(
            final String filePath1,
            final String filePath2,
            final String formatName
    ) throws Exception {
        Map<String, Object> map = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        List<DiffNode> differList = DiffCollector.collect(map, map2);
        Formatter formatter = FormatterFactory.createFormatter(formatName);

        return formatter.format(differList);
    }
}
