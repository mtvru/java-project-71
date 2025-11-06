/**
 * Contains the main application tests.
 */
package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testMain() {
        App.main(new String[] {"file1.json", "file2.json"});
        String ls = System.lineSeparator();
        assertEquals("{"
            + ls + "  - follow: false"
            + ls + "    host: hexlet.io"
            + ls + "  - proxy: 123.234.53.22"
            + ls + "  - timeout: 50"
            + ls + "  + timeout: 20"
            + ls + "  + verbose: true"
            + ls + "}",
        output.toString(StandardCharsets.UTF_8).trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
