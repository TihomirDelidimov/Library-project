package exceptions;

/**
 * This exception indicates that a user has already created a borrow request and cannot create new
 */
public class UserAlreadyHasBorrowRequestException extends RuntimeException {

    public UserAlreadyHasBorrowRequestException(String errorMessage) {
        super(errorMessage);
    }
}
