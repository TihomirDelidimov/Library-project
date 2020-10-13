import java.time.LocalDate;

/**
 * This class represents a record of some action, which might be a borrow request or a record about a borrowed book.
 */
public class UserRecord {

    private String username;
    private PaperBook paperBook;
    private LocalDate dateOfAction;
    private LocalDate dueDate;

    public UserRecord(String username, PaperBook paperBook, int periodInDays) {
        this.username = username;
        this.paperBook = paperBook;
        this.dateOfAction = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(periodInDays);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\nUsername: ")
                .append(username)
                .append("\nPaper book: ")
                .append(paperBook)
                .append("\nDate of action: ")
                .append(dateOfAction)
                .append("\nDue date: ")
                .append(dueDate)
                .toString();
    }

    /**
     * This method is used to check the request by the username
     * @param username - this parameter is the username
     * @return - this method return true if this request has the supplied username, otherwise return false
     */
    public boolean checkRecordByUsername(String username) {
        if(username == null || username.isEmpty()) {
            return false;
        }
        return username.equals(this.username);
    }

    /**
     * This method is used to check the request by the isbn
     * @param isbn - this parameter is the book's ISBN
     * @return - this method return true if this request has the book with the supplied ISBN, otherwise return false
     */
    public boolean checkRecordByISBN(String isbn) {
        if(isbn==null || isbn.isEmpty()) {
            return false;
        }
        return paperBook.checkISBN(isbn);
    }
}