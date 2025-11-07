package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DifferTest {
    @Test
    void testGenerate() throws Exception {
        String result = Differ.generate("file1.json", "file2.json");
        Path path = Paths.get("src/test/resources/fixtures/result.txt");
        String expected = Files.readString(path).trim();
        assertEquals(expected, result);
    }
}
