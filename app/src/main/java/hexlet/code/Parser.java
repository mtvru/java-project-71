package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public final class Parser {
    private static final String FORMAT_YAML = "yaml";
    private static final String FORMAT_YML = "yml";
    private static final String FORMAT_JSON = "json";

    private Parser() {
        throw new UnsupportedOperationException(
            "Instantiation of " + Parser.class.getName() + " is not allowed"
        );
    }

    /**
     * YAML mapper used for parsing files.
     */
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();
    /**
     * JSON mapper used for parsing files.
     */
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    /**
     * @param content string.
     * @param format string.
     * @return map representation of the parsed content.
     * @throws JsonProcessingException if the content cannot be parsed.
     * @throws IllegalArgumentException if a file format is unsupported.
     */
    public static Map<String, Object> parse(
        final String content,
        final String format
    ) throws JsonProcessingException {
        ObjectMapper mapper = switch (format) {
            case FORMAT_YAML, FORMAT_YML -> YAML_MAPPER;
            case FORMAT_JSON -> JSON_MAPPER;
            default -> throw new IllegalArgumentException(
                    "Unknown format: " + format
            );
        };

        return mapper.readValue(
            content,
            new TypeReference<>() {
            }
        );
    }
}
