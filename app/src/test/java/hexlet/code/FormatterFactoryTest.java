package hexlet.code;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatterFactoryTest {
    @Test
    void testCreateFormatterWithUnknownNameThrowsException() {
        String unknownFormat = "unknown";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> FormatterFactory.createFormatter(unknownFormat)
        );
        assertTrue(exception.getMessage().contains("Unknown formatter"));
    }

    @Test
    void testPrivateConstructorThrowsException() throws Exception {
        Constructor<FormatterFactory> constructor =
            FormatterFactory.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(
            InvocationTargetException.class,
            constructor::newInstance
        );
        Throwable cause = exception.getCause();
        assertEquals(UnsupportedOperationException.class, cause.getClass());
        String expectedMessage = "Instantiation of "
            + FormatterFactory.class.getName()
            + " is not allowed";
        assertEquals(expectedMessage, cause.getMessage());
    }
}
