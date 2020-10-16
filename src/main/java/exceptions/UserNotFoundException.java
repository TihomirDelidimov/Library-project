package exceptions;

/**
 * This exception is thrown when a user is not found in the repository
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
