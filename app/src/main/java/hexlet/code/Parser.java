package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public final class Parser {
    private Parser() {
        throw new UnsupportedOperationException(
            "Instantiation of " + Parser.class.getName() + " is not allowed"
        );
    }

    /**
     * Yaml mapper used for parsing files.
     */
    private static ObjectMapper mapper = new YAMLMapper();

    /**
     * @param content string.
     * @return map representation of the parsed content.
     * @throws JsonProcessingException if the content cannot be parsed.
     */
    public static Map<String, Object> parse(final String content)
        throws JsonProcessingException {
        return mapper.readValue(
                content,
                new TypeReference<>() {
                }
        );
    }
}
