package exceptions;

/**
 * This exception is thrown when a Book reference is missing
 */
public class MissingBookReferenceException extends RuntimeException {

    public MissingBookReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
