package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class DifferTest {
    @ParameterizedTest
    @MethodSource("filePairs")
    void testGenerate(final String file1, final String file2) throws Exception {
        String result = Differ.generate(file1, file2);
        Path path = Paths.get("src/test/resources/fixtures/result.txt");
        String expected = Files.readString(path).trim();
        assertEquals(expected, result);
    }

    private static Stream<Arguments> filePairs() {
        return Stream.of(
                Arguments.of("file1.json", "file2.json"),
                Arguments.of("file1.yaml", "file2.yml")
        );
    }
}
