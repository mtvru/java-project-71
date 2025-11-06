package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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
        Map<String, Object> map = mapper.readValue(path, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> map2 = mapper.readValue(path2, new TypeReference<Map<String,Object>>(){});

        return map.toString() + System.lineSeparator() + map2.toString();
    }
}
