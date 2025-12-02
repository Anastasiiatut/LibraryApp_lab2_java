import java.util.Calendar;
import java.util.Date;

public record Author(
        String firstName,
        String lastName,
        Date birthDate
) {
    public Author {
        if (firstName == null || firstName.length() < 3) {
            throw new IllegalArgumentException("First name must be non-null and have at least 3 characters");
        }
        if (lastName == null || lastName.length() < 3) {
            throw new IllegalArgumentException("Last name must be non-null and have at least 3 characters");
        }
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