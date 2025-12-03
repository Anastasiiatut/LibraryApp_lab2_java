package main;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public record Author(
        String firstName,
        String lastName,
        Date birthDate
) {
    public Author {
        final Logger LOGGER = Logger.getLogger(Author.class.getName());
        if (firstName == null || firstName.length() < 3) {
            LOGGER.severe("First name is required or is less than 3 characters.");
            throw new IllegalArgumentException("First name must be non-null and have at least 3 characters");
        }
        if (lastName == null || lastName.length() < 3) {
            LOGGER.severe("Last name is required or is less than 3 characters.");
            throw new IllegalArgumentException("Last name must be non-null and have at least 3 characters");
        }
        LOGGER.info("Author created: " + firstName + " " + lastName);

    }
    public static Author withName(String firstName, String lastName) {
        return new Author(firstName, lastName, null);
    }
    public static Author bornIn(String firstName, String lastName, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1); // Встановлюємо 1 січня вказаного року
        return new Author(firstName, lastName, calendar.getTime());
    }
    public static Author of(String firstName, String lastName, Date birthDate) {
        return new Author(firstName, lastName, birthDate);
    }

}