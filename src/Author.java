import java.util.Calendar;
import java.util.Date;

public class Author {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Author(String firstName, String lastName, Date birthDate) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDate(birthDate);
    }

    public static Author of(String firstName, String lastName, Date birthDate) {
        return new Author(firstName, lastName, birthDate);
    }

    public static Author withName(String firstName, String lastName) {
        return new Author(firstName, lastName, null);
    }

    public static Author bornIn(String firstName, String lastName, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1); // Встановлюємо 1 січня вказаного року
        return new Author(firstName, lastName, calendar.getTime());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length()<3) {
            throw new IllegalArgumentException("First name must have at least 3 characters");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (firstName.length()<3) {
            throw new IllegalArgumentException("Last name must have at least 3 characters");
        }
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {

        return "Author " + firstName + " " + lastName;
    }


}