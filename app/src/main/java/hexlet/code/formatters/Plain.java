package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain implements Formatter {
    @Override
    public String format(final List<DiffNode> differList) {
        Map<String, List<DiffNode>> grouppedDifferList = differList
            .stream()
            .filter(DiffNode::isStatusChanged)
            .collect(Collectors.groupingBy(DiffNode::getKey));

        return grouppedDifferList
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> formatEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatEntry(final String key, final List<DiffNode> nodes) {
        if (nodes.size() == 1) {
            DiffNode node = nodes.getFirst();

            return node.isStatusAdded()
                    ? "Property '" + key + "' was added with value: "
                        + this.getRenderedValue(node)
                    : "Property '" + key + "' was removed";
        }

        boolean addedFirst = nodes.getFirst().isStatusAdded();
        String oldValue = this.getRenderedValue(nodes.get(addedFirst ? 1 : 0));
        String newValue = this.getRenderedValue(nodes.get(addedFirst ? 0 : 1));

        return "Property '" + key + "' was updated. From "
                + oldValue + " to " + newValue;
    }

    private String getRenderedValue(DiffNode node) {
        Object value = node.getValue();

        if (this.isObjectOrArray(value)) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value == null ? "null" : value.toString();
    }

    private boolean isObjectOrArray(final Object value) {
        return value instanceof Map<?, ?> || value instanceof List<?>;
    }
}
