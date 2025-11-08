package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class DifferTest {
    /**
     * Base path to the test fixture files directory.
     */
    private static final Path FIXTURES = Path.of(
        "src", "test", "resources", "fixtures"
    );

    @ParameterizedTest
    @MethodSource("filePairs")
    void testGenerate(
        final Path file1,
        final Path file2,
        final Path expectedFile
    ) throws Exception {
        String result = Differ.generate(file1.toString(), file2.toString());
        String expected = Files.readString(expectedFile).trim();
        assertEquals(expected, result);
    }

    private static Stream<Arguments> filePairs() {
        return Stream.of(
                Arguments.of(
                        FIXTURES.resolve("file51.json"),
                        FIXTURES.resolve("file52.json"),
                        FIXTURES.resolve("result6.txt")
                ),
                Arguments.of(
                        FIXTURES.resolve("file81.yaml"),
                        FIXTURES.resolve("file82.yml"),
                        FIXTURES.resolve("result6.txt")
                ),
                Arguments.of(
                        FIXTURES.resolve("file91.json"),
                        FIXTURES.resolve("file92.json"),
                        FIXTURES.resolve("result9.txt")
                ),
                Arguments.of(
                        FIXTURES.resolve("file91.yaml"),
                        FIXTURES.resolve("file92.yml"),
                        FIXTURES.resolve("result9.txt")
                )
        );
    }
}
