package exceptions;

/**
 * This exception indicates that a borrow request for a book does not exist
 */
public class BorrowRequestDoesNotExistException extends RuntimeException {
    public BorrowRequestDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
