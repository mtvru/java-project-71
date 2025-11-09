package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain implements Formatter {
    @Override
    public String format(final List<DiffNode> differList) {
        Map<String, List<DiffNode>> grouppedDifferList = differList
            .stream()
            .filter(DiffNode::isStatusChanged)
            .sorted(
                Comparator
                    .comparing(DiffNode::getKey)
                    .thenComparing(e ->
                        e.isStatusAdded() ? 1 : 0)
            )
            .collect(Collectors.groupingBy(
                DiffNode::getKey, LinkedHashMap::new, Collectors.toList()
            ));

        return grouppedDifferList
                .entrySet()
                .stream()
                .map(entry -> formatEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatEntry(final String key, final List<DiffNode> nodes) {
        if (nodes.size() == 1) {
            DiffNode node = nodes.getFirst();

            return node.isStatusAdded()
                    ? "Property '" + key + "' was added with value: "
                        + node.getRenderedValue()
                    : "Property '" + key + "' was removed";
        }

        String oldValue = nodes.getFirst().getRenderedValue();
        String newValue = nodes.getLast().getRenderedValue();

        return "Property '" + key + "' was updated. From "
                + oldValue + " to " + newValue;
    }
}
