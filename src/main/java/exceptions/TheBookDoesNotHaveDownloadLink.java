package exceptions;

/**
 * This exception is thrown when a book does not have download link
 */
public class TheBookDoesNotHaveDownloadLink extends RuntimeException {
    public TheBookDoesNotHaveDownloadLink(String errorMessage) {
        super(errorMessage);
    }
}

