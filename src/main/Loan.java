package main;

import java.util.Date;
import java.util.logging.Logger;

public class Loan {
    private static final Logger LOGGER = Logger.getLogger(Loan.class.getName());

    private Book book;
    private Reader reader;
    private Date issueDate;
    private Date returnDate;
    public Loan(Book book, Reader reader, Date issueDate, Date returnDate) {
        this.setBook(book);
        this.setReader(reader);
        this.setIssueDate(issueDate);
        this.setReturnDate(returnDate);
        LOGGER.info(String.format("Created Loan with Book: %s and Reader: %s", book, reader));
    }

    public static Loan of(Book book, Reader reader, Date issueDate, Date returnDate) {
        return new Loan(book, reader, issueDate, returnDate);
    }

    public static Loan createNew(Book book, Reader reader) {
        return new Loan(book, reader, new Date(), null);
    }


    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "main.Loan book=" + book + ", reader=" + reader + ", issueDate=" + issueDate;
    }

}
