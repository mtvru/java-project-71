package hexlet.code;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist.");
        }

        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist.");
        }

        ObjectMapper mapper = new  ObjectMapper();
        JsonNode jsonNode = mapper.readTree(path);
        JsonNode jsonNode2 = mapper.readTree(path2);

        return jsonNode.toString() + System.lineSeparator() + jsonNode2.toString();
    }
}
