package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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
    )
            throws Exception {
        Path path = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist.");
        }

        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist.");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(
            path,
            new TypeReference<>() {
            }
        );
        Map<String, Object> map2 = mapper.readValue(
            path2,
            new TypeReference<>() {
            }
        );
        Map<String, Object> differ = new HashMap<>();
        map.forEach((k, v) -> {
            if (map2.containsKey(k) && map2.get(k).equals(v)) {
                differ.put(k, v);
            } else if (map2.containsKey(k) && !map2.get(k).equals(v)) {
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
