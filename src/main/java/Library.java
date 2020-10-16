import enumerations.BookGenre;
import enumerations.Gender;
import enumerations.Tag;
import exceptions.*;
import interfaces.Downloadable;
import interfaces.Readable;

import java.time.LocalDate;

import static java.time.LocalDate.*;

import java.util.*;

/**
 * This class represents a library, which keep a track of the users requests to borrow a book, users that has
 * borrowed a books. {@link Library#borrowRequestRecords} is used to track the users, which have created a borrowing request and
 * have 3 days to borrow the book from the library. borrowBooksRecords is used to track the users, which have borrowed
 * a book and each user has 14 days to return the book (if not postponement). These two lists store {@link UserRecord}
 */
public class Library {

    private final static int USER_DAYS_TO_BORROW = 3;
    private final static int USER_DAYS_TO_RETURN_THE_BOOK = 14;
    private final static int AVERAGE_BOOK_BORROWED_DAYS_PER_USER = 21;
    private UserRepository userRepository = new UserRepository();
    private BookRepository bookRepository = new BookRepository();
    private List<UserRecord> borrowRequestRecords = new ArrayList<>();
    private List<UserRecord> borrowedBookRecords = new ArrayList<>();
    private List<User> overdueUsers = new ArrayList<>();
    private LocalDate currentDate = now();

    /**
     * This method is used to register a new user to the library and more specifically to the user repository
     *
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param gender
     * @param address
     * @param email
     * @param age
     * @param gdprConsent
     */
    public boolean registerUser(String firstName, String lastName, String username, String password, Gender gender,
                                String address, String email, int age, boolean gdprConsent) {
        User newUser = userRepository.createUser(firstName, lastName, username, password, gender, address, email, age, gdprConsent);
        if (newUser != null) {
            userRepository.addUser(newUser);
            return true;
        }
        return false;
    }

    public boolean addPaperBook(String title, String summary, String isbn, List<Author> authors, List<Tag> tags, BookGenre genre, int quantity) {
        PaperBook paperBook = bookRepository.createNewPaperBook(title, summary, isbn, authors, tags, genre, quantity);
        if (paperBook != null) {
            bookRepository.addBook(paperBook);
            return true;
        }
        return false;
    }

    public boolean addReadOnlyEBook(String title, String summary, String isbn, List<Author> authors, List<Tag> tags,
                                    BookGenre genre, String linkToRead) {
        ReadOnlyEBook readOnlyEBook = bookRepository.createNewReadOnlyEBook(title, summary, isbn, authors, tags, genre, linkToRead);
        if (readOnlyEBook != null) {
            bookRepository.addBook(readOnlyEBook);
            return true;
        }
        return false;
    }

    public boolean addEBook(String title, String summary, String isbn, List<Author> authors,
                            List<Tag> tags, BookGenre genre, String linkToRead, String linkToDownload) {
        EBook eBook = bookRepository.createNewEBook(title, summary, isbn, authors, tags, genre, linkToRead, linkToDownload);
        if (eBook != null) {
            bookRepository.addBook(eBook);
            return true;
        }
        return false;
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
     * This method is used to return a book reference by book's ISBN, which is unique.
     *
     * @param isbn - this parameter is the book's ISBN
     * @return - this method return book reference if a book with the suuplied isbn is found in the book repository, otherwise
     * return null
     */
    public Book getBookByISBN(String isbn) {
        return bookRepository.getBookByISBN(isbn);
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
     * @param username - this parameter is the user, which is borrowing the book
     * @param isbn     - this parameter is the ISBN of the book, which might be borrowed
     * @return - this method return a message, which context is indication about the output of the method
     */
    public String borrowBookRequest(String username, String isbn) {
        User requestOwner = findUserByUsername(username);
        if (isUserOverdue(requestOwner)) {
            return username + " has overdue books and cannot create new borrow request!";
        }
        if (!bookRepository.checkPaperBookExistence(isbn)) {
            throw new InvalidISBNException("There is not paper book with this ISBN: " + isbn + "!");
        }
        PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
        if (bookRepository.canTheBookBeBorrowed(isbn)) {
            if (addNewRequestToTheList(requestOwner, paperBook)) {
                return username + " has 3 days to borrow the book from the library!";
            } else {
                throw new UserAlreadyHasBorrowRequestException("This user: " + username +
                        " already has a request for a book with this ISBN: " + isbn + "!");
            }
        }
        paperBook.addUserToTheQueue(requestOwner);
        int userQueuePosition = paperBook.getQueueSize();
        LocalDate estimationDate = now().plusDays(userQueuePosition * AVERAGE_BOOK_BORROWED_DAYS_PER_USER);
        return username + " position in queue for book: " + userQueuePosition
                + ". Estimate time of borrowing: " + estimationDate.toString();
    }

    /**
     * This method is used to find a user by the username in the repository
     *
     * @param username - this parameter is the username of the user
     * @return - this method return a reference to the user if found, otherwise null
     */
    private User findUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException("The user doesn't exist!");
    }

    private boolean isUserOverdue(User user) {
        return overdueUsers.contains(userRepository);
    }

    /**
     * This method is used to check is the user already has created a borrow request for that book
     *
     * @param username - this parameter is the username of the user
     * @param isbn     - this parameter is the isbn of the book
     * @return - this method return true if the user already has a request for that book, otherwise return false
     */
    private boolean isUserAlreadyRequestedTheBook(String username, String isbn) {
        PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
        for (UserRecord userRecord : borrowRequestRecords) {
            if (userRecord.isUserAlreadyRequestedTheBook(username, paperBook)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to add new borrow request (if it doesn't exist for the user) to the list of requests
     * {@link Library#borrowRequestRecords} and update the book's quantity.
     *
     * @param user      - this parameter is the user, which has requested a book
     * @param paperBook - this parameter is the requested book
     * @return - this method return true if the request is added successfully, otherwise return false
     */
    private boolean addNewRequestToTheList(User user, PaperBook paperBook) {
        UserRecord userBorrowRequest =
                new UserRecord(user.getUsername(), paperBook, currentDate, currentDate.plusDays(USER_DAYS_TO_BORROW));
        if (!isUserAlreadyRequestedTheBook(user.getUsername(), paperBook.getISBN())) {
            borrowRequestRecords.add(userBorrowRequest);
            paperBook.decrementQuantity();
            return true;
        }
        return false;
    }

    /**
     * This method is used when a user has come to the library and borrowed the book, which he has requested.
     * The book and the user will be removed from the borrowRequestsRecords and put into the borrowedBooksRecords.
     * The method also updates the user history.
     *
     * @param username - this parameter is the username of the user, which has borrowed the book
     * @param isbn     - this parameter is the isbn of the book, which was borrowed by the user
     */
    public void borrowBook(String username, String isbn) {
        UserRecord recordToDelete = null;
        for (UserRecord borrowRequestRecord : borrowRequestRecords) {
            if (borrowRequestRecord.checkRecordByUsername(username) && borrowRequestRecord.checkRecordByISBN(isbn)) {
                recordToDelete = borrowRequestRecord;
                break;
            }
        }

        if (recordToDelete != null) {
            borrowRequestRecords.remove(recordToDelete);
            PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
            UserRecord borrowedBookRecord =
                    new UserRecord(username, paperBook, currentDate, currentDate.plusDays(USER_DAYS_TO_RETURN_THE_BOOK));
            borrowedBookRecords.add(borrowedBookRecord);
            userRepository.addBookToUserHistory(username, paperBook, currentDate);
        } else {
            throw new BorrowRequestDoesNotExistException("Trying to borrow a book without a request!");
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
     * @param isbn     - this parameter is the ISBN of the book
     */
    public void returnBook(String username, String isbn) {
        UserRecord recordToDelete = null;
        for (UserRecord borrowedBookRecord : borrowedBookRecords) {
            if (borrowedBookRecord.checkRecordByUsername(username) && borrowedBookRecord.checkRecordByISBN(isbn)) {
                recordToDelete = borrowedBookRecord;
                break;
            }
        }

        if (recordToDelete != null) {
            borrowedBookRecords.remove(recordToDelete);
            PaperBook paperBook = (PaperBook) bookRepository.getBookByISBN(isbn);
            createBookRequestForTheNextInQueue(paperBook);
        } else {
            throw new ReturningANonBorrowedBookException("A book with ISBN: " + isbn + " is not borrwed!");
        }
    }

    /**
     * This method is used to get the next user, which is waiting for the specific book in a queue and
     * create a borrow book request. If there is no user waiting for the book, the book quantity is increased by 1.
     *
     * @param paperBook - this parameter is the paper book, for which the method creates borrow request
     */
    private void createBookRequestForTheNextInQueue(PaperBook paperBook) {
        User nextUser = paperBook.pullUserFromQueue();
        if (nextUser != null) {
            addNewRequestToTheList(nextUser, paperBook);
        } else {
            paperBook.incrementQuantity();
        }
    }

    /**
     * This method is used to create a postponement for specific user, which
     *
     * @param username     - this parameter is the username of the user, on which a postponement for the specific book will be created
     * @param isbn         - this parameter is the isbn of the the book, which the user has borrowed
     * @param periodInDays - this parameter is the period of time, which user wants to make postponement for a book
     * @return - this method return true if a successful postponement is created, otherwise return false
     */
    public boolean postponeBook(String username, String isbn, int periodInDays) {
        findUserByUsername(username);
        if (bookRepository.checkPaperBookExistence(isbn)) {
            for (UserRecord userRecord : borrowedBookRecords) {
                if (userRecord.checkRecordByUsername(username) && userRecord.checkRecordByISBN(isbn)) {
                    return userRecord.createPostponement(periodInDays);
                }
            }
        }
        return false;
    }

    /**
     * This method is used to get a link for a user to read the book
     *
     * @param username - this parameter is the username of the user
     * @param isbn     - this parameter is the book's ISBN
     * @return - this method return a link where the user can read the book
     */
    public String getBookOnlineLinkToRead(String username, String isbn) {
        Book book = bookRepository.getBookByISBN(isbn);
        if (book instanceof ReadOnlyEBook || book instanceof EBook) {
            userRepository.addBookToUserHistory(username, book, currentDate);
            return ((Readable) book).getReadOnlineLink();
        } else if (book != null) {
            throw new TheBookDoesNotHaveReadLink("A book with ISBN: " + isbn + " is not an electronic book!");
        }
        return null;
    }

    /**
     * This method is usedto get a link for a user to download the book
     *
     * @param username - this parameter is the username of the user
     * @param isbn     - this parameter is the book's ISBN
     * @return - this method return a link where the user can download the book
     */
    public String getBookOnlineLinkToDownload(String username, String isbn) {
        Book book = bookRepository.getBookByISBN(isbn);
        if (book instanceof EBook) {
            userRepository.addBookToUserHistory(username, book, currentDate);
            return ((Downloadable) book).getDownloadLink();
        } else if (book != null) {
            throw new TheBookDoesNotHaveDownloadLink("A book with ISBN: " + isbn + " does not have download link!");
        }
        return null;
    }

    /**
     * This method is used when a user wants to see his waiting list. It will return map, which represents
     * the book he is waiting to borrow and his number in the queue for that book.
     *
     * @param user - this parameter is the user for whom the information will be extracted
     * @return - this method return Map, which represents the books the user is waiting and the positions in the
     * queues for this books. If no information is extracted a null value is returned.
     */
    public Map<String, Integer> showUserWaitingList(User user) {
        if (user != null) {
            return bookRepository.showPositionOfTheUserInAllQueues(user);
        }
        return null;
    }

    /**
     * This method is used to show the whole list of borrow requests by users
     *
     * @return - this method return the borrow request list in string format
     */
    public List<UserRecord> showBookRequests() {
        return borrowRequestRecords;
    }

    public List<UserRecord> showBorrowedBooks() {
        return borrowedBookRecords;
    }

    public void incrementCurrentDate(int days) {
        currentDate = currentDate.plusDays(days);
        ;
    }

    /**
     * This method is used to update the {@link Library#borrowRequestRecords}. It checks the due dates of the
     * list and if the method find that the current date is after the due date of the request, this means
     * that the request has expired and the request needs to be removed from the list. After all the records are checked
     * the expired records are deleted and the queues about the paper book, which has become available are updated
     */
    public void updateBorrowRequestRecords() {
        List<UserRecord> recordsToRemove = new ArrayList<>();
        List<PaperBook> paperBookQueueUpdateList = new ArrayList<>();
        for (UserRecord userRecord : borrowRequestRecords) {
            if (currentDate.isAfter(userRecord.getDueDate())) {
                recordsToRemove.add(userRecord);
                PaperBook paperBook = userRecord.getPaperBook();
                if (!paperBookQueueUpdateList.contains(paperBook)) {
                    paperBookQueueUpdateList.add(paperBook);
                }
            }
        }
        if (recordsToRemove.size() > 0) {
            borrowRequestRecords.removeAll(recordsToRemove);
        }
        if (!paperBookQueueUpdateList.isEmpty()) {
            updatePaperBooksQueue(paperBookQueueUpdateList);
        }
    }

    /**
     * This method is used to update the queues for the paper books
     *
     * @param paperBookQueueUpdateList - this parameter is a list of books, which queues needs to be updated
     */
    private void updatePaperBooksQueue(List<PaperBook> paperBookQueueUpdateList) {
        for (PaperBook paperBook : paperBookQueueUpdateList) {
            createBookRequestForTheNextInQueue(paperBook);
        }
    }

    /**
     * This method is used to check {@link Library#borrowedBookRecords} for user, which has overdue a book. If
     * a user has an overdue book, he will be removed from the list of borrowed books and put to another list, which keeps
     * track of users that have an overdue books.
     */
    public void updateBorrowedBookRecords() {
        for (UserRecord userRecord : borrowedBookRecords) {
            if (currentDate.isAfter(userRecord.getDueDate())) {
                User user = userRepository.getUserByUsername(userRecord.getUsername());
                overdueUsers.add(user);
            }
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.searchBooksByTitle(title);
    }

    public List<Book> searchBooksByTags(Tag... tags) {
        return bookRepository.searchBooksByTags(tags);
    }

    public List<Book> searchForBooksByAuthorFullName(String authorFullName) {
        return bookRepository.searchForBooksByAuthorFullName(authorFullName);
    }

    public List<Book> searchForBooksByAuthorFirstName(String firstName) {
        return bookRepository.searchForBooksByAuthorFirstName(firstName);
    }

    public List<Book> searchForBooksByAuthorLastName(String lastName) {
        return bookRepository.searchForBooksByAuthorLastName(lastName);
    }

    /**
     * This method is used to get the book count of the library
     *
     * @return - this method returns the book count of the library
     */
    public int getLibraryBookCount() {
        return bookRepository.getBookCount();
    }
}
