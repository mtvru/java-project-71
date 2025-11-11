package hexlet.code.formatters;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import java.util.Comparator;
import java.util.List;

public final class Json implements Formatter {
    /**
     * Object mapper used for serializing.
     */
    private final ObjectMapper mapper;

    /**
     *  Default constructor.
     */
    public Json() {
        this.mapper = new ObjectMapper()
            .setVisibility(
                PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE
            );
    }

    @Override
    public String format(final List<DiffNode> differList)
        throws JsonProcessingException {
        List<DiffNode> list = differList.stream()
            .sorted(Comparator
                .comparing(DiffNode::getKey)
                .thenComparing(e -> e.isStatusAdded() ? 1 : 0))
            .toList();

        return mapper.writeValueAsString(list);
    }
}
