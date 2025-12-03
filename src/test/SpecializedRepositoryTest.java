package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.*;

import java.util.List;

public class SpecializedRepositoryTest {

    private BookRepository bookRepository;
    private ReaderRepository readerRepository;

    private Author authorTaras;
    private Author authorIvan;

    private Reader reader1;
    private Reader reader2;
    private Reader reader3;

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository(Book::getIsbn);
        readerRepository = new ReaderRepository(reader -> String.valueOf(reader.readerId()));

        authorTaras = Author.withName("Тарас", "Шевченко");
        authorIvan = Author.withName("Іван", "Котляревський");

        reader1 = new Reader("Олексій", "Пономаренко", 201);
        reader2 = new Reader("Марія", "Литвиненко", 202);
        reader3 = new Reader("Петро", "Пономаренко", 203);

        book1 = new Book("Кобзар: Поезії", new Author[]{authorTaras}, "111", BookStatus.AVAILABLE);
        book2 = new Book("Енеїда", new Author[]{authorIvan}, "222", BookStatus.RESERVED);
        book3 = new Book("Збірка гуморесок", new Author[]{authorTaras, authorIvan}, "333", BookStatus.CHECKED_OUT);
        book4 = new Book("Садок вишневий", new Author[]{authorTaras}, "444", BookStatus.AVAILABLE);

        bookRepository.add(book1);
        bookRepository.add(book2);
        bookRepository.add(book3);
        bookRepository.add(book4);

        readerRepository.add(reader1);
        readerRepository.add(reader2);
        readerRepository.add(reader3);
    }

    @Test
    void findByTitleContaining_shouldFindPartialMatchesCaseInsensitive() {
        List<Book> result = bookRepository.findByTitleContaining("КОБЗАР");
        assertEquals(1, result.size());
        assertEquals("Кобзар: Поезії", result.get(0).getTitle());

        List<Book> result2 = bookRepository.findByTitleContaining("Збірка");
        assertEquals(1, result2.size());

        List<Book> result3 = bookRepository.findByTitleContaining("вишневий");
        assertEquals(1, result3.size());
    }

    @Test
    void findByStatus_shouldReturnCorrectBooks() {
        List<Book> availableBooks = bookRepository.findByStatus(BookStatus.AVAILABLE);
        assertEquals(2, availableBooks.size());

        List<Book> checkedOutBooks = bookRepository.findByStatus(BookStatus.CHECKED_OUT);
        assertEquals(1, checkedOutBooks.size());
    }

    @Test
    void findByAuthor_shouldReturnBooksBySingleAuthor() {
        List<Book> tarasBooks = bookRepository.findByAuthor(authorTaras);
        assertEquals(3, tarasBooks.size());

        List<Book> ivanBooks = bookRepository.findByAuthor(authorIvan);
        assertEquals(2, ivanBooks.size());
        assertTrue(ivanBooks.stream().anyMatch(b -> b.getTitle().equals("Енеїда")));
    }

    @Test
    void getTitlesOfCheckedOutBooks_shouldUseMapAndForEach() {
        List<String> titles = bookRepository.getTitlesOfCheckedOutBooks();
        assertEquals(1, titles.size());
        assertEquals("Збірка гуморесок", titles.get(0));
    }

    @Test
    void findByLastName_shouldReturnCorrectReaders() {
        // Точне співпадіння прізвища
        List<Reader> ponomarenko = readerRepository.findByLastName("Пономаренко");
        assertEquals(2, ponomarenko.size());

        List<Reader> lytvynenko = readerRepository.findByLastName("Литвиненко");
        assertEquals(1, lytvynenko.size());
    }

    @Test
    void sumOfAllReaderIds_shouldReturnCorrectTotal() {
        int totalIdSum = readerRepository.sumOfAllReaderIds();
        assertEquals(606, totalIdSum);
    }
}