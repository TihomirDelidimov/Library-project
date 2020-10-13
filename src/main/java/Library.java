import java.util.*;

/**
 * This class represents a library, which keep a track of the users requests to borrow a book, users that has
 * borrowed a books.borrowRequestsRecords is used to track the users, which have created a borrowing request and
 * have 3 days to borrow the book from the library. borrowBooksRecords is used to track the users, which have borrowed
 * a book and each user has 14 days to return the book (if not postponement). These two lists store {@link UserRecord}
 */
public class Library {

    private UserRepository userRepository = new UserRepository();
    private BookRepository bookRepository = new BookRepository();
    private List<UserRecord> borrowRequestsRecords = new ArrayList<>();
    private List<UserRecord> borrowedBooksRecords = new ArrayList<>();
    private final int USER_DAYS_TO_BORROW = 3;
    private final int USER_DAYS_TO_RETURN_THE_BOOK = 14;

    /**
     * This method is used to add a new user to the library and more specifically to the user repository
     *
     * @param user - this parameter is the new user of the library
     */
    public void addUser(User user) {
        if (user != null) {
            userRepository.addUser(user);
        }
    }

    /**
     * This method is used to add a new book to the library and more specifically to the book repository
     *
     * @param book - this parameter is the new book, which will be added to the repository
     */
    public void addBook(Book book) {
        if (book != null) {
            bookRepository.addBook(book);
        }
    }

    /**
     * This method is used to show all the books in the library
     *
     * @return - this method return the library's books in string format
     */
    public String showBooks() {
        return bookRepository.toString()
                .replace("[", "")
                .replace("]", "");
    }

    /**
     * This method is used to authenticate a user, which is trying to login with his username and password. The verification
     * of the account is executed by the userRepository.
     *
     * @param username - this parameter is the user's username
     * @param password - this parameter is the user's password
     * @return - this method return true if the user's username and password are both correct, otherwise return false
     */
    public boolean authenticateUser(String username, String password) {
        return userRepository.verifyUserAccount(username, password);
    }

    /**
     * This method is used to return a user reference by user's username, which is unique.
     *
     * @param username - this parameter is the user's username
     * @return - this method return user reference if a user with the supplied username is found in the user repository,
     * otherwise return null
     */
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * This method is used to create a borrow request for a paper book from the library. First it checks if the paper book exist in the repository
     * based on book's ISBN, after that it checks if the book can be borrowed (if the book is available at the moment). If
     * the book is available at the moment of the current request, the information about the user's borrowing request is stored
     * in borrowRequestsRecords. It's content represent that a user has made a request to borrow
     * a book, but he has 3 days to get the book, otherwise the book will be returned to the repository. The quantity of the books
     * is decremented so the next request for borrowing will depend on the book's available quantity.
     * If the paper book is not available at the moment of the request, the user is placed in queue of waiting for the available copy.
     *
     * @param user - this parameter is the user, which is borrowing the book
     * @param isbn - this parameter is the ISBN of the book, which might be borrowed
     */
    public void borrowBookRequest(User user, String isbn) {
        if (bookRepository.checkPaperBookExistence(isbn)) {
            if (bookRepository.canABookBeBorrowed(isbn)) {
                PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
                UserRecord userBorrowRequest =
                        new UserRecord(user.getUsername(), paperBook, USER_DAYS_TO_BORROW);
                borrowRequestsRecords.add(userBorrowRequest);
                System.out.println("rrr");
                System.out.println(borrowRequestsRecords.get(0));
                paperBook.decrementQuantity();
            } else {
                PaperBook book = (PaperBook) bookRepository.getBookByISBN(isbn);
                book.addUserToTheQueue(user);
                book.getUserQueuePosition(user);
            }
        }
    }

    /**
     * This method is used when a user has come to the library and borrowed the book, which he has requested.
     * The book and the user will be removed from the borrowRequestsRecords and put into the borrowedBooksRecords
     *
     * @param username - this parameter is the username of the user, which has borrowed the book
     * @param isbn     - this parameter is the isbn of the book, which was borrowed by the user
     */
    public void userBorrowedBook(String username, String isbn) {
        UserRecord recordToDelete = null;
        for (UserRecord borrowRequestRecord : borrowRequestsRecords) {
            if (borrowRequestRecord.checkRecordByUsername(username) && borrowRequestRecord.checkRecordByISBN(isbn)) {
                recordToDelete = borrowRequestRecord;
                break;
            }
        }

        if (recordToDelete != null) {
            borrowRequestsRecords.remove(recordToDelete);
            PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
            UserRecord borrowedBookRecord =
                    new UserRecord(username, paperBook, USER_DAYS_TO_RETURN_THE_BOOK);
            borrowedBooksRecords.add(borrowedBookRecord);
        }
    }

    /**
     * This method is used when a user has returned a book back to the library. The borrowed book and the user
     * will be removed from the borrowedBooksRecords, which has the information that the user has borrowed the book.
     * The paper book quantity will not increase in case there is a user waiting for this book in the queue. If
     * the book's waiting queue is empty the quantity of the book will increase, because the user has returned the book
     * and the next user, which choose this book will be able to create borrow request without waiting.
     *
     * @param username - this parameter is the username of the user
     * @param isbn - this parameter is the ISBN of the book
     */
    public void userReturnedBook(String username, String isbn) {
        UserRecord recordToDelete = null;
        for (UserRecord borrowedBookRecord : borrowedBooksRecords) {
            if (borrowedBookRecord.checkRecordByUsername(username) && borrowedBookRecord.checkRecordByISBN(isbn)) {
                recordToDelete = borrowedBookRecord;
                break;
            }
        }

        if (recordToDelete != null) {
            borrowedBooksRecords.remove(recordToDelete);
            PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
            User nextUser = paperBook.pullUserFromQueue();
            if (nextUser != null) {
                borrowBookRequest(nextUser, paperBook.getISBN());
            } else {
                paperBook.incrementQuantity();
            }
        }
    }

    /**
     * This method is used when a user wants to see his waiting list. It will return map, which represents
     * the book he is waiting to borrow and his number in the queue.
     * @param user - this parameter is the user for whom the information will be extracted
     * @return - this method return Map, which represents the books the user is waiting and the positions in the
     * queues for this books. If no information is extracted a null value is returned.
     */
    public Map<PaperBook, Integer> showUserWaitingList(User user) {
        if (user != null) {
            return bookRepository.getUserWaitingBooks(user);
        }
        return null;
    }

    /**
     * This method is used to show the whole list of borrowed books by users
     * @return - this method return the borrowed books list in string format
     */
    public String showBorrowedBooks() {
        return borrowedBooksRecords.toString().replace("[", "").replace("]", "");
    }

    /**
     * This method is used to show the whole list of borrow requests by users
     * @return - this method return the borrow request list in string format
     */
    public String showBookRequests() {
        return borrowRequestsRecords.toString().replace("[", "").replace("]", "");
    }
}
