package exceptions;

/**
 * This exception is thrown when common properties of a class are not valid
 */
public class CommonValidationException extends RuntimeException {

    public CommonValidationException(String errorMessage) {
        super(errorMessage);
    }
}
