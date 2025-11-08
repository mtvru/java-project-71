package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Stylish implements Formatter {
    @Override
    public String format(final List<DiffNode> differList) {
        return "{"
                + System.lineSeparator()
                + differList
                .stream()
                .sorted(
                    Comparator
                        .comparing(DiffNode::getKey)
                        .thenComparing(e ->
                            e.isStatusAdded() ? 1 : 0)
                )
                .map(node -> {
                    String prefix = switch (node.getStatus()) {
                        case ADDED -> "+ ";
                        case REMOVED -> "- ";
                        default -> "  ";
                    };
                    return "  " + prefix + node.getKey()
                            + ": " + node.getValue();
                })
                .collect(Collectors.joining(System.lineSeparator()))
                + System.lineSeparator() + "}";
    }
}
