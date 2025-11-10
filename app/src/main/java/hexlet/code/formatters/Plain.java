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
                        + node.getRenderedValue()
                    : "Property '" + key + "' was removed";
        }

        boolean addedFirst = nodes.getFirst().isStatusAdded();
        String oldValue = nodes.get(addedFirst ? 1 : 0).getRenderedValue();
        String newValue = nodes.get(addedFirst ? 0 : 1).getRenderedValue();

        return "Property '" + key + "' was updated. From "
                + oldValue + " to " + newValue;
    }
}
