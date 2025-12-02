package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class DiffCollector {
    private DiffCollector() {
        throw new UnsupportedOperationException(
            "Instantiation of " + DiffCollector.class.getName()
            + " is not allowed"
        );
    }

    /**
     * Collects a sorted list of DiffNode objects.
     *
     * @param map1 the first map.
     * @param map2 the second map.
     * @return a list of DiffNode objects.
     */
    public static List<DiffNode> collect(
        final Map<String, Object> map1,
        final Map<String, Object> map2
    ) {
        Set<String> set = new TreeSet<>(map1.keySet());
        set.addAll(map2.keySet());
        List<DiffNode> differList = new ArrayList<>();

        for (String key : set) {
            boolean key1Exists =  map1.containsKey(key);
            boolean key2Exists =  map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (key1Exists && key2Exists && Objects.equals(value1, value2)) {
                differList.add(new DiffNode(key, value1, value2, DiffNode.Status.UNCHANGED));
            } else if (key1Exists && key2Exists && !Objects.equals(value1, value2)) {
                differList.add(new DiffNode(key, value1, value2, DiffNode.Status.UPDATED));
            } else if (key1Exists && !key2Exists) {
                differList.add(new DiffNode(key, value1, null, DiffNode.Status.REMOVED));
            } else if (!key1Exists && key2Exists) {
                differList.add(new DiffNode(key, null, value2, DiffNode.Status.ADDED));
            }
        }

        return differList;
    }
}
