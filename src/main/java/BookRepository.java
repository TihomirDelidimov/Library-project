import enumerations.BookGenre;
import enumerations.Tag;
import exceptions.BookNotFoundException;

import static CommonStringValidation.CommonStringValidator.*;

import java.util.*;

/**
 * This class represent the storage of the library. It's purpose is to store the books in list, to add new books, to
 * check if a book exist, to return a book by ISBN and to extract information about user's waiting book list
 */
public class BookRepository {

    List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return books.toString();
    }

    /**
     * This method is used to add a new book to the repository
     *
     * @param book
     */
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }

    /**
     * This method checks if a book with the supplied ISBN exist in the repository.
     *
     * @param isbn - this parameter is the book's ISBN
     * @return - this method return true if a book with the given isbn exist in the repository, otherwise return false
     */
    public boolean checkPaperBookExistence(String isbn) {
        if (!isStringValid(isbn)) {
            return false;
        }
        Book book = getBookByISBN(isbn);
        return book instanceof PaperBook;
    }

    /**
     * This method checks if the paper book in the repository with the supplied ISBN can be borrowed. The method is used
     * to indicate if the book is available to be borrowed or the request must be placed in queue
     *
     * @param isbn - this parameter is the book's ISBN
     * @return - this method return true if the book is available for borrowing, otherwise return false
     */
    public boolean canTheBookBeBorrowed(String isbn) {
        Book book = getBookByISBN(isbn);
        if (book instanceof PaperBook) {
            PaperBook paperBook = (PaperBook) book;
            return paperBook.isAvailable();
        }
        throw new BookNotFoundException("No book has been found with this ISBN: " + isbn);
    }

    /**
     * This method is used to get a Book reference to a book by the ISBN
     *
     * @param isbn - this parameter is the book's ISBN
     * @return - this method return the Book's reference if a book with the supplied ISBN exist in the repository, otherwise
     * return null
     */
    public Book getBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.isISBNEqual(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * This method is used to search for user, which might be waiting for several paper books to become available.
     * The method checks the user queue position for every book, if the user position is larger than 0 it means
     * that the user is in the book's queue waiting, otherwise position will be negative value and it means that
     * the user wasn't found in the queue for this book. The method map the result of position and book in hashmap.
     *
     * @param user - this parameter is the user for whom the method search
     * @return - this method return map with the book and the position in the waiting queue about this book.
     */
    public Map<String, Integer> showPositionOfUserInWaitingList(User user) {
        Map<String, Integer> map = new HashMap<>();
        Book book = books.get(books.indexOf(user));
        if (book instanceof PaperBook) {
            PaperBook paperBook = (PaperBook) book;
            int position = paperBook.getUserQueuePosition(user);
            if (position > 0) {
                map.put(paperBook.getISBN(), position);
            }
        }
        return map;
    }

    /**
     * This method is used to search for a books by the title
     *
     * @param title - this parameter is the title, which is the criteria for the result
     * @return - this method return a list of books if there are books in the repository, otherwise
     * the method returns an empty list. If the title is invalid string, the method will return null value
     */
    public List<Book> searchBooksByTitle(String title) {
        if (!isStringValid(title)) {
            return null;
        }
        List<Book> booksResult = new ArrayList<>();
        for (Book book : books) {
            if (book.isTitleEqual(title)) {
                booksResult.add(book);
            }
        }
        return booksResult;
    }

    /**
     * This method is used to search for a books by the genre
     *
     * @param genre - this parameter is the genre, which is the criteria for the result
     * @return - this method return a list of books if there are books in the repository, otherwise
     * the method returns an empty list. If the genre is invalid, the method will return null value
     */
    public List<Book> searchBooksByGenre(BookGenre genre) {
        if (genre == null) {
            return null;
        }
        List<Book> bookResult = new ArrayList<>();
        for (Book book : books) {
            if (book.isGenreEqual(genre)) {
                bookResult.add(book);
            }
        }
        return bookResult;
    }

    /**
     * This method is used to search for a books by the tags
     *
     * @param tags - this parameter is is the tags, which are the criteria for the result
     * @return - this method return a list of books if there are books in the repository, otherwise
     * the method returns an empty list. If the tags are invalid, the method will return null value
     */
    public List<Book> searchBooksByTags(Tag... tags) {
        if (tags == null || tags.length < 0) {
            return null;
        }
        List<Book> bookResult = new ArrayList<>();
        for (Book book : books) {
            if (book.getTags().containsAll(Arrays.asList(tags))) {
                bookResult.add(book);
            }
        }
        return bookResult;
    }

    /**
     * This method is used to search for a books by the author's full name
     *
     * @param authorFullName - this parameter is author's full name
     * @return - this method return a list of books if there are books in the repository, otherwise
     * the method returns an empty list. If the tags are invalid, the method will return null value
     */
    public List<Book> searchBooksByAuthorFullName(String authorFullName) {
        if (!isStringValid(authorFullName)) {
            return null;
        }
        List<Book> bookResult = new ArrayList<>();
        for (Book book : books) {
            if(book.isThereAnAuthorWithFullName(authorFullName)) {
                bookResult.add(book);
            }
        }
        return bookResult;
    }
}
