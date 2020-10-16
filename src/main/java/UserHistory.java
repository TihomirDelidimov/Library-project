import exceptions.MissingBookReferenceException;
import exceptions.MissingLocalDateReferenceException;

import java.time.LocalDate;

/**
 * This class is represents a single unit of a user history, which means an instance of this class will contain
 * information about a book and date.
 */
public class UserHistory {

    private LocalDate date;
    private Book book;

    public UserHistory(Book book, LocalDate date) {
        validateBook(book);
        validateDate(date);
        this.book = book;
        this.date = date;
    }

    private void validateBook(Book book) {
        if(book == null) {
            throw new MissingBookReferenceException("Missing book reference!");
        }
    }

    private void validateDate(LocalDate date) {
        if(date == null) {
            throw new MissingLocalDateReferenceException("Missing borrow date reference!");
        }
    }
}
