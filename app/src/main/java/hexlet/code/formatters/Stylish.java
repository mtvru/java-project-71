package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.stream.Collectors;

public final class Stylish implements Formatter {
    @Override
    public String format(final List<DiffNode> differList) {
        return "{" + System.lineSeparator()
                + differList
                .stream()
                .map(this::formatNode)
                .collect(Collectors.joining(System.lineSeparator()))
                + System.lineSeparator() + "}";
    }

    private String formatNode(final DiffNode node) {
        String formatted = null;

        if (node.isStatusAdded()) {
            formatted = "  + " + node.getKey() + ": " + node.getNewValue();
        } else if (node.isStatusRemoved()) {
            formatted = "  - " + node.getKey() + ": " + node.getOldValue();
        } else if (node.isStatusUnchanged()) {
            formatted = "    " + node.getKey() + ": " + node.getOldValue();
        } else if (node.isStatusUpdated()) {
            formatted = "  - " + node.getKey() + ": " + node.getOldValue() + System.lineSeparator()
                    + "  + " + node.getKey() + ": " + node.getNewValue();
        }

        return formatted;
    }
}
