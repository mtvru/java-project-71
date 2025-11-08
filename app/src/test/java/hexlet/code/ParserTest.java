package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;

class ParserTest {
    @Test
    void testParseThrowsExceptionWhenFileDoesNotExist() {
        String nonExistentFilePath = "nonexistent_file.yml";
        Exception exception = assertThrows(Exception.class, () -> {
            Parser.parse(nonExistentFilePath);
        });
        String expectedMessage = "File '"
            + Path.of(nonExistentFilePath)
                .toAbsolutePath()
                .normalize()
            + "' does not exist.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
