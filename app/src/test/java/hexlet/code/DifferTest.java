package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public final class DifferTest {
    @Test
    void testGenerate() throws Exception {
        String result = Differ.generate("file1.json", "file2.json");
        String ls = System.lineSeparator();
        assertEquals("{"
            + ls + "  - follow: false"
            + ls + "    host: hexlet.io"
            + ls + "  - proxy: 123.234.53.22"
            + ls + "  - timeout: 50"
            + ls + "  + timeout: 20"
            + ls + "  + verbose: true"
            + ls + "}",
        result);
    }
}
