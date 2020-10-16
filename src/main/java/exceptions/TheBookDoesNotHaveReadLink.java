package exceptions;

/**
 * This exception is thrown when a book does not have a read link
 */
public class TheBookDoesNotHaveReadLink extends RuntimeException {

    public TheBookDoesNotHaveReadLink(String errorMessage) {
        super(errorMessage);
    }
}
