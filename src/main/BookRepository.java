package main;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Date;

public class BookRepository extends GenericRepository<Book> {

    private static final Logger LOGGER = Logger.getLogger(BookRepository.class.getName());

    public BookRepository(IdentityExtractor<Book> identityExtractor) {
        super(identityExtractor);
    }

    public List<Book> findByTitleContaining(String titlePart) {
        LOGGER.info("Searching books that have in name '" + titlePart + "'");

        String lowerCaseTitlePart = titlePart.toLowerCase();

        return findAll().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseTitlePart))
                .collect(Collectors.toList());
    }

    public List<Book> findByStatus(BookStatus status) {
        LOGGER.info("Searching with status: " + status);

        return findAll().stream()
                .filter(book -> book.getBookStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(Author author) {
        LOGGER.info("Searching books with author: " + author.lastName());

        return findAll().stream()
                .filter(book -> {
                    for (Author a : book.getAuthors()) {
                        if (a.equals(author)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<String> getTitlesOfCheckedOutBooks() {
        LOGGER.info("Getting names of checked-out books.");

        List<String> titles = findAll().stream()
                .filter(book -> book.getBookStatus() == BookStatus.CHECKED_OUT)
                .map(Book::getTitle)
                .collect(Collectors.toList());

        titles.forEach(t -> System.out.println("-> Видана: " + t));

        return titles;
    }
}