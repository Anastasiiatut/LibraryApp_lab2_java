package test;

import main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class RecordValidationTest {
    @Test
    void author_creation_valid() {
        assertDoesNotThrow(() -> new Author("Іван", "Франко", new Date()));
    }

    @Test
    void author_creation_invalid_firstName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Author("О", "Коваль", new Date());
        });
        assertTrue(thrown.getMessage().contains("First name must be non-null and have at least 3 characters"));
    }

    @Test
    void author_creation_invalid_lastName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Author("Олена", "М", new Date());
        });
        assertTrue(thrown.getMessage().contains("Last name must be non-null and have at least 3 characters"));
    }

    @Test
    void reader_creation_valid() {
        assertDoesNotThrow(() -> new Reader("Петро", "Іванов", 100));
    }

    @Test
    void reader_creation_invalid_readerId() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Reader("Петро", "Іванов", 0);
        });
        assertTrue(thrown.getMessage().contains("Reader ID must be positive"));
    }
}