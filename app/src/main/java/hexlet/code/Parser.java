package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Parser {
    private Parser() {
        throw new UnsupportedOperationException();
    }

    /**
     * Yaml mapper used for parsing files.
     */
    private static ObjectMapper mapper = new YAMLMapper();

    /**
     * @param filePath path to the file.
     * @return map representation of the parsed content.
     * @throws Exception if the file does not exist or cannot be parsed.
     */
    public static Map<String, Object> parse(final String filePath)
    throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist.");
        }

        return mapper.readValue(
                path.toFile(),
                new TypeReference<>() {
                }
        );
    }
}
