package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain implements Formatter {
    @Override
    public String format(final List<DiffNode> differList) {
        return differList
                .stream()
                .filter(DiffNode::isStatusChanged)
                .map(this::formatNode)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatNode(final DiffNode node) {
        if (node.isStatusAdded()) {
            return "Property '" + node.getKey() + "' was added with value: "
                    + this.getRenderedValue(node.getNewValue());
        }

        if (node.isStatusRemoved()) {
            return "Property '" + node.getKey() + "' was removed";
        }

        String oldValue = this.getRenderedValue(node.getOldValue());
        String newValue = this.getRenderedValue(node.getNewValue());

        return "Property '" + node.getKey() + "' was updated. From "
                + oldValue + " to " + newValue;
    }

    private String getRenderedValue(Object value) {
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
