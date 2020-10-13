import java.time.LocalDate;

public class UserHistory {

    private LocalDate borrowDate;
    private Book book;

    public UserHistory(Book book, LocalDate borrowDate) {
        validateBook(book);
        validateDate(borrowDate);
        this.book = book;
        this.borrowDate = borrowDate;
    }

    private void validateBook(Book book) {
        if(book == null) {
            throw new NullPointerException("Missing book reference!");
        }
    }

    private void validateDate(LocalDate date) {
        if(date == null) {
            throw new NullPointerException("Missing borrow date reference!");
        }
    }
}
