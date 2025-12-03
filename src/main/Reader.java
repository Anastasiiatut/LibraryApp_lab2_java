package main;

import java.util.logging.Logger;

public record Reader(
        String firstName,
        String lastName,
        int readerId
) {
    public Reader {
        final Logger LOGGER = Logger.getLogger(Author.class.getName());
        if (firstName == null || firstName.length() < 3) {
            LOGGER.severe("First name is required or is too short");
            throw new IllegalArgumentException("First name must be non-null and have at least 3 characters");
        }
        if (lastName == null || lastName.length() < 3) {
            LOGGER.severe("Last name is required or is too short");
            throw new IllegalArgumentException("Last name must be non-null and have at least 3 characters");
        }
        if (readerId <= 0) {
            LOGGER.severe("Reader id must be positive");
            throw new IllegalArgumentException("main.Reader ID must be positive.");
        }
    }
    @Override
    public String toString() {
        return "main.Reader @" + readerId + " " + firstName + " " + lastName;
    }

    public static Reader newReaderWithoutId(String firstName, String lastName) {
        throw new UnsupportedOperationException("ID must be known when creating a main.Reader record.");
    }
}