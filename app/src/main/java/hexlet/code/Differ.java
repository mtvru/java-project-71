package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Stylish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        List<DiffNode> differList = new ArrayList<>();
        map.forEach((k, v) -> {
            if (map2.containsKey(k) && Objects.equals(map2.get(k), v)) {
                differList.add(new DiffNode(k, v, DiffNode.Status.UNCHANGED));
            } else if (map2.containsKey(k) && !Objects.equals(map2.get(k), v)) {
                differList.add(new DiffNode(k, v, DiffNode.Status.REMOVED));
                differList.add(new DiffNode(
                    k, map2.get(k), DiffNode.Status.ADDED
                ));
            } else {
                differList.add(new DiffNode(k, v, DiffNode.Status.REMOVED));
            }
        });
        map2.forEach((k, v) -> {
            if (!map.containsKey(k)) {
                differList.add(new DiffNode(k, v, DiffNode.Status.ADDED));
            }
        });
        Formatter formatter = new Stylish();

        return formatter.format(differList);
    }
}
