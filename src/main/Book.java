package main;

import java.util.Arrays;
import java.util.logging.Logger;

public class Book {
    private static final Logger LOGGER = Logger.getLogger(Book.class.getName());

    private String title;
    private Author[] authors;
    private String isbn;
    private BookStatus bookStatus;
    public Book(String title, Author[] authors, String isbn, BookStatus bookStatus) {
        this.setTitle(title);
        this.setAuthors(authors);
        this.setIsbn(isbn);
        this.setBookStatus(bookStatus);

        LOGGER.info("Book (with status) created: " + title + " " + authors.toString());
    }

    public Book(String title, Author[] authors, String isbn) {
        this.setTitle(title);
        this.setAuthors(authors);
        this.setIsbn(isbn);

        LOGGER.info("Book (without status) created: " + title + " " + authors.toString());

    }

    public static Book of(String title, Author[] authors, String isbn) {
        return new Book(title, authors, isbn);
    }

    public static Book withSingleAuthor(String title, Author author, String isbn) {
        return new Book(title, new Author[]{author}, isbn);
    }

    public static Book withoutIsbn(String title, Author[] authors) {
        return new Book(title, authors, null);
    }



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if (title.length()<3) {
            throw new IllegalArgumentException("Title must have at least 3 characters");
        }
        this.title = title;
    }
    public Author[] getAuthors() {
        return authors;
    }
    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "main.Book title: " + title + ", authors: " + Arrays.toString(authors) + ", isbn: " + isbn;
    }
}
