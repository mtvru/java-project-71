/**
 * Contains the main application tests.
 */
package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class AppTest {
    /**
     * Captures the standard output stream during test execution.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    /**
     * Stores the original standard output to restore after each test.
     */
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void testMain() throws IOException {
        String[] args = "file1.json file2.json".split(" ");
        new CommandLine(new App()).execute(args);
        Path path = Path.of(
            "src", "test", "resources", "fixtures", "result9.txt"
        );
        String expected = Files.readString(path).trim();
        assertEquals(expected, output.toString(StandardCharsets.UTF_8).trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
