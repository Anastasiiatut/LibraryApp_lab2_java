package main;

import java.util.Date;
import java.util.Calendar;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -10);
        Date issueDate = cal.getTime();
        IdentityExtractor<Book> bookIdentity = Book::getIsbn;
        IdentityExtractor<Reader> readerIdentity = reader -> String.valueOf(reader.readerId());
        GenericRepository<Book> bookRepository = new GenericRepository<>(bookIdentity);
        GenericRepository<Reader> readerRepository = new GenericRepository<>(readerIdentity);
        Author authorTaras = Author.withName("Тарас", "Шевченко");
        Author authorIvan = Author.withName("Іван", "Котляревський");
        Reader readerOlexiy = new Reader("Олексій", "Пономаренко", 201);
        Reader readerMaria = new Reader("Марія", "Литвиненко", 202);
        readerRepository.add(readerOlexiy);
        readerRepository.add(readerMaria);
        System.out.println("\nЧитачів додано: " + readerRepository.size()); // 2
        Book bookKobzar = new Book("Кобзар", new Author[]{authorTaras}, "978-617-7429-10-1", BookStatus.AVAILABLE);
        Book bookEneida = new Book("Енеїда", new Author[]{authorIvan}, "978-966-03-8756-1", BookStatus.RESERVED);
        Book bookTest = new Book("Тестова книга", new Author[]{authorIvan}, "999-000-1111-2", BookStatus.AVAILABLE);
        bookRepository.add(bookKobzar);
        bookRepository.add(bookEneida);
        bookRepository.add(bookTest);
        System.out.println("Книг додано: " + bookRepository.size()); // 3
        Membership mariaMembership = Membership.createStandardAnnual(readerMaria);
        Loan olexiyLoan = Loan.createNew(bookKobzar, readerOlexiy);

        System.out.println("\n--- Додаткові об'єкти ---");
        System.out.println(mariaMembership);
        System.out.println(olexiyLoan);

        System.out.println("\n--- Результати Пошуку ---");

        String searchIsbn = "978-617-7429-10-1";
        Optional<Book> foundBook = bookRepository.findByIdentity(searchIsbn);
        if (foundBook.isPresent()) {
            System.out.println("Знайдено книгу: " + foundBook.get().getTitle() +
                    " (Статус: " + foundBook.get().getBookStatus() + ")");
        } else {
            System.out.println("Книга з ISBN " + searchIsbn + " не знайдена.");
        }

        String searchReaderId = "202";
        Optional<Reader> foundReader = readerRepository.findByIdentity(searchReaderId);
        foundReader.ifPresent(r -> System.out.println("👤 Знайдено читача: " + r.lastName() +
                ", ID: " + r.readerId()));

        Optional<Book> notFoundBook = bookRepository.findByIdentity("999-999-9999-9");
        System.out.println("Книгу з неіснуючим ISBN знайдено? " + notFoundBook.isPresent()); // false

        System.out.println("\n--- Демонстрація Дублікатів ---");

        Book duplicateBook = new Book("Дублікат", new Author[]{authorIvan}, "978-617-7429-10-1", BookStatus.AVAILABLE);
        try {
            bookRepository.add(duplicateBook);
        } catch (IllegalArgumentException e) {
            System.out.println("Успішно перехоплено помилку дублікату книги: " + e.getMessage());
        }

        Reader duplicateReader = new Reader("Дублікат", "ID1", 201);
        try {
            readerRepository.add(duplicateReader);
        } catch (IllegalArgumentException e) {
            System.out.println("Успішно перехоплено помилку дублікату читача: " + e.getMessage());
        }

        System.out.println("Кількість книг після спроби дублікату: " + bookRepository.size()); // 3
        System.out.println("Кількість читачів після спроби дублікату: " + readerRepository.size()); // 2
    }
}