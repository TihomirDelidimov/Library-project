import java.awt.print.Paper;
import java.time.LocalDate;
import static java.time.LocalDate.*;
import static CommonStringValidation.CommonStringValidator.*;

/**
 * This class represents a record of some action, which might be a borrow request or a record about a borrowed book.
 */
public class UserRecord {

    private final static int MAXIMUM_POSTPONEMENT_DAYS = 14;
    private String username;
    private PaperBook paperBook;
    private LocalDate dateOfAction;
    private LocalDate dueDate;
    private LocalDate maximumPostponementDate;

    public UserRecord(String username, PaperBook paperBook, int periodInDays) {
        this.username = username;
        this.paperBook = paperBook;
        this.dateOfAction = now();
        dueDate = now().plusDays(periodInDays);
        maximumPostponementDate = dueDate.plusDays(MAXIMUM_POSTPONEMENT_DAYS);
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public PaperBook getPaperBook() {
        return paperBook;
    }

    public String getUsername() {
        return username;
    }

    /**
     * This method is used to check the request by the username
     * @param username - this parameter is the username
     * @return - this method return true if this request has the supplied username, otherwise return false
     */
    public boolean checkRecordByUsername(String username) {
        if(isStringValid(username)) {
            return username.equals(this.username);
        }
        return false;
    }

    /**
     * This method is used to check the request by the isbn
     * @param isbn - this parameter is the book's ISBN
     * @return - this method return true if this request has the book with the supplied ISBN, otherwise return false
     */
    public boolean checkRecordByISBN(String isbn) {
        if(isStringValid(isbn)) {
            return paperBook.isISBNEqual(isbn);
        }
        return false;
    }

    /**
     * This method is used only when UserRecord is used for borrowed books
     * @return - this method return true if the method has created a new due date based on the parameter, otherwise
     * return false
     */
    public boolean createPostponement(int periodInDays) {
        if(periodInDays < 0 || periodInDays > 14) {
            return false;
        }

        LocalDate requestedPostponementDate = dueDate.plusDays(periodInDays);
        if(requestedPostponementDate.isBefore(maximumPostponementDate)) {
            dueDate = requestedPostponementDate;
            return true;
        }
        return false;
    }
}
