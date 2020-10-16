package exceptions;

/**
 * This exception is thrown when the user is not gdpr compliant
 */
public class UserDoesNotComplyForEUGDPRException extends RuntimeException {

    public UserDoesNotComplyForEUGDPRException(String errorMessage) {
        super(errorMessage);
    }
}
