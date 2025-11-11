package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class DiffCollector {
    private DiffCollector() {
        throw new UnsupportedOperationException(
            "Instantiation of " + DiffCollector.class.getName()
            + " is not allowed"
        );
    }

    /**
     * Collects a list of DiffNode objects.
     *
     * @param map the first map.
     * @param map2 the second map.
     * @return a list of DiffNode objects.
     */
    public static List<DiffNode> collect(
        final Map<String, Object> map,
        final Map<String, Object> map2
    ) {
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

        return differList;
    }
}
