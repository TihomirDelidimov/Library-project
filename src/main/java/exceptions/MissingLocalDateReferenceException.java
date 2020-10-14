package exceptions;

/**
 * This exception is thrown when a LocalDate reference is missing
 */
public class MissingLocalDateReferenceException extends RuntimeException{
    public MissingLocalDateReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
