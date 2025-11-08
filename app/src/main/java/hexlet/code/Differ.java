package hexlet.code;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Differ {
    private Differ() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param filePath1 the path to the first file
     * @param filePath2 the path to the second file
     * @return a formatted diff string
     * @throws Exception if either file does not exist or cannot be read
     */
    public static String generate(
            final String filePath1,
            final String filePath2
    ) throws Exception {
        Map<String, Object> map = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        Map<String, Object> differ = new HashMap<>();
        map.forEach((k, v) -> {
            if (map2.containsKey(k) && Objects.equals(map2.get(k), v)) {
                differ.put(k, v);
            } else if (map2.containsKey(k) && !Objects.equals(map2.get(k), v)) {
                String key1 = "- " + k;
                differ.put(key1, v);
                String key2 = "+ " + k;
                differ.put(key2, map2.get(k));
            } else {
                String key1 = "- " + k;
                differ.put(key1, v);
            }
        });
        map2.forEach((k, v) -> {
            if (!map.containsKey(k)) {
                String key1 = "+ " + k;
                differ.put(key1, v);
            }
        });

        return "{"
                + System.lineSeparator()
                + differ
                .entrySet()
                .stream()
                .sorted(
                    Comparator
                        .comparing((Map.Entry<String, Object> e) -> {
                            String k = e.getKey();
                            return k.startsWith("+ ") || k.startsWith("- ")
                                ? k.substring(2) : k;
                        })
                        .thenComparing(e ->
                            e.getKey().startsWith("+ ") ? 1 : 0)
                )
                .map(e -> {
                    String key = e.getKey();
                    String prefix = key.startsWith("+ ") || key.startsWith("- ")
                        ? "" : "  ";
                    return "  " + prefix + key + ": " + e.getValue();
                })
                .collect(Collectors.joining(System.lineSeparator()))
                + System.lineSeparator() + "}";
    }
}
