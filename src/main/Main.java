package main;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        IdentityExtractor<Book> bookIdentity = Book::getIsbn;
        IdentityExtractor<Reader> readerIdentity = reader -> String.valueOf(reader.readerId());

        BookRepository bookRepository = new BookRepository(bookIdentity);
        ReaderRepository readerRepository = new ReaderRepository(readerIdentity);

        System.out.println("Спеціалізовані репозиторії ініціалізовані.");

        Author authorTaras = Author.withName("Тарас", "Шевченко");
        Author authorIvan = Author.withName("Іван", "Котляревський");

        // Створення Читачів
        Reader readerOlexiy = new Reader("Олексій", "Пономаренко", 201);
        Reader readerMaria = new Reader("Марія", "Литвиненко", 202);
        Reader readerPetro = new Reader("Петро", "Пономаренко", 203);

        readerRepository.add(readerOlexiy);
        readerRepository.add(readerMaria);
        readerRepository.add(readerPetro);

        Book book1 = new Book("Кобзар: Поезії", new Author[]{authorTaras}, "111-A", BookStatus.AVAILABLE);
        Book book2 = new Book("Енеїда", new Author[]{authorIvan}, "222-B", BookStatus.RESERVED);
        Book book3 = new Book("Збірка гуморесок", new Author[]{authorTaras, authorIvan}, "333-C", BookStatus.CHECKED_OUT);
        Book book4 = new Book("Садок вишневий", new Author[]{authorTaras}, "444-D", BookStatus.AVAILABLE);

        bookRepository.add(book1);
        bookRepository.add(book2);
        bookRepository.add(book3);
        bookRepository.add(book4);

        System.out.println("\nДані додано до спеціалізованих репозиторіїв.");

        System.out.println("\n--- Пошук в BookRepository ---");

        List<Book> foundByTitle = bookRepository.findByTitleContaining("КОБЗАР");
        System.out.println("Знайдено за назвою ('КОБЗАР'): " + foundByTitle.size());

        List<Book> available = bookRepository.findByStatus(BookStatus.AVAILABLE);
        System.out.println("Кількість доступних книг: " + available.size());

        bookRepository.getTitlesOfCheckedOutBooks();

        List<Book> booksByTaras = bookRepository.findByAuthor(authorTaras);
        System.out.println("Книги Шевченка: " + booksByTaras.size());

        System.out.println("\n--- Пошук в ReaderRepository ---");

        List<Reader> ponomarenkos = readerRepository.findByLastName("Пономаренко");
        System.out.println("Знайдено читачів з прізвищем 'Пономаренко': " + ponomarenkos.size());

        int totalIdSum = readerRepository.sumOfAllReaderIds();
        System.out.println("Сума всіх Reader ID: " + totalIdSum); // 606

        System.out.println("\n--- Демонстрація Дублікатів (базовий метод add) ---");
        try {
            bookRepository.add(new Book("Дублікат", new Author[]{authorIvan}, "111-A", BookStatus.AVAILABLE));
        } catch (IllegalArgumentException e) {
            System.out.println("Успішно перехоплено помилку дублікату книги: " + e.getMessage());
        }
    }
}