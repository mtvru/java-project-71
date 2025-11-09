package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public interface Formatter {
    /**
     * Formats a list of DiffNode objects into a string
     * according to a specific style.
     *
     * @param differList the list of DiffNode objects representing differences.
     * @return a formatted string representing the diff.
     * @throws RuntimeException if formatting fails.
     */
    String format(List<DiffNode> differList);
}
