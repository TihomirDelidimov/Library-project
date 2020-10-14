package exceptions;

/**
 * This exception is thrown when a Library reference is missing
 */
public class MissingLibraryReferenceException extends RuntimeException{

    public MissingLibraryReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
