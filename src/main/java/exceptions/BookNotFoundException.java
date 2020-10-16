package exceptions;

/**
 * This exception is thrown when a searching for a book is executed, but no book is found
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
