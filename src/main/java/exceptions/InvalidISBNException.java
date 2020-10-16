package exceptions;

/**
 * This exception is thrown when an invalid ISBN is found
 */
public class InvalidISBNException extends RuntimeException {
    public InvalidISBNException(String errorMessage) {
        super(errorMessage);
    }
}
