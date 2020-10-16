package exceptions;

/**
 * This exception indicates that an attempt for returning a Non-Borrowed book is made
 */
public class ReturningANonBorrowedBookException extends RuntimeException{

    public ReturningANonBorrowedBookException(String errorMessage) {
        super(errorMessage);
    }
}
