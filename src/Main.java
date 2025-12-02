import java.util.Date;
import java.util.Calendar;
import java.util.Arrays;

// Припускаємо, що всі класи (Author, Reader, Book, Membership, Loan)
// були оновлені з приватними конструкторами та статичними factory-методами.

public class Main {

    public static void main(String[] args) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -30);
        Date issueDate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 10);
        Date returnDate = cal.getTime();
        Author author1 = Author.bornIn("Іван", "Франко", 1856);
        Author author2 = Author.withName("Леся", "Українка");
        Author author3 = Author.of("Джордж", "Оруелл", new Date(104, 6, 25)); // 25.06.1903

        System.out.println("--- Автори ---");
        System.out.println(author1);
        System.out.println(author2);
        System.out.println(author3);
        Reader reader1 = new Reader ("Олена", "Ковальчук", 1001);
        Reader reader2 = new Reader("Андрій", "Мельник", 1002);
        System.out.println("\n--- Читачі ---");
        System.out.println(reader1);
        System.out.println(reader2);
        Book book1 = Book.withSingleAuthor("Земля", author1, "978-617-690-348-1");
        Book book2 = Book.withoutIsbn("Лісова пісня", new Author[]{author2});
        Book book3 = Book.of("1984", new Author[]{author3}, "978-014-118-776-1");
        System.out.println("\n--- Книги ---");
        System.out.println(book1.getTitle() + " by " + author1.lastName());
        System.out.println(book2.getTitle() + " by " + author2.lastName());
        System.out.println(book3.getTitle() + " by " + author3.lastName());
        Membership membership1 = Membership.createStandardAnnual(reader1);
        Membership membership2 = Membership.createLifetime(reader2, issueDate);
        System.out.println("\n--- Членство ---");
        System.out.println(membership1);
        System.out.println(membership2);
        Loan loan1 = Loan.createNew(book1, reader1);
        Loan loan2 = new Loan(book3, reader2, issueDate, returnDate);
        System.out.println("\n--- Позики ---");
        System.out.println(loan1);
        System.out.println(loan2);
        System.out.println("\n--- Демонстрація валідації (помилки) ---");
        try {
            // Спроба створити автора з коротким ім'ям
            Author.withName("А", "В");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації (очікувано): " + e.getMessage());
        }

        try {
            // Спроба створити книгу з коротким заголовком
            Book.withoutIsbn("А", new Author[]{author1});
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації (очікувано): " + e.getMessage());
        }

        try {
            // Спроба створити завершену позику, де дата повернення раніше дати видачі
            new Loan (book1, reader1, today, issueDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації (очікувано): " + e.getMessage());
        }
    }
}