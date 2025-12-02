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
        if (node.isStatusAdded()) {
            return "  + " + node.getKey() + ": " + node.getNewValue();
        }

        if (node.isStatusRemoved()) {
            return "  - " + node.getKey() + ": " + node.getOldValue();
        }

        if (node.isStatusUnchanged()) {
            return "    " + node.getKey() + ": " + node.getOldValue();
        }

        return "  - " + node.getKey() + ": " + node.getOldValue() + System.lineSeparator()
               + "  + " + node.getKey() + ": " + node.getNewValue();
    }
}
