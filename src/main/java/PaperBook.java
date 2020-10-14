import enumerations.BookGenre;
import enumerations.Tag;
import exceptions.CommonValidationException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class represents a paper book, which inherit {@link Book} properties. Every paper book has a queue, which keeps track
 * of the users that has requested that book, but at the moment there was no available copy.
 */
public class PaperBook extends Book {

    private static final int QUEUE_HEAD = 0;
    private LinkedList<User> waitingUsers = new LinkedList<>();
    private int totalQuantity;
    private int availableQuantity;

    public PaperBook(String title, String summary, String isbn, List<Author> authors, List<Tag> tags, BookGenre genre,
                     int quantity) {
        super(title, summary, isbn, authors, tags, genre);
        validateQuantity(quantity);
        totalQuantity = quantity;
        availableQuantity = quantity;
    }

    /**
     * This method validates the quantity of the book when a new book is created
     * @param quantity - this parameter is the quantity
     */
    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new CommonValidationException("Paper book quantity must be at least 1!");
        }
    }

    /**
     * This method check if the book is available at the moment of request
     *
     * @return - this method return true if the book is available for borrowing, otherwise return false
     */
    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    /**
     * This method updates book's available quantity when a user is borrowed the book
     */
    public void decrementQuantity() {
        if (availableQuantity > 0) {
            availableQuantity--;
        }
    }

    /**
     * This method updates book's available quantity when a user has returned the book
     */
    public void incrementQuantity() {
        if (availableQuantity < totalQuantity) {
            availableQuantity++;
        }
    }

    /**
     * This method is used when a user has requested a book, but at the moment of the request there wasn't available
     * copy for this book and the user need to wait for a copy of the book to become available.
     * @param user - this parameter is the user, which will wait for the book to become available
     */
    public void addUserToTheQueue(User user) {
        if (user != null) {
            waitingUsers.add(user);
        }
    }

    /**
     * This method is used when a user has returned a book to the library and that means a copy of the book
     * is available. The method extracts the first element of the waiting queue and returns it.
     * @return - this method return the first user in the queue, which has requested this book, if there is no
     * waiting queue for this book the method return null
     */
    public User pullUserFromQueue() {
        if(waitingUsers.size() > 0) {
            return waitingUsers.remove(QUEUE_HEAD);
        }
        return null;
    }

    /**
     * This method returns the queue position of the user
     * @param user - this parameter is the user
     * @return - this method return positive integer value if the user if found in the queue, otherwise -1 is returned
     */
    public int getUserQueuePosition(User user) {
        if (user != null) {
            return waitingUsers.indexOf(user);
        }
        return -1;
    }

    public int getQueueSize() {
        return waitingUsers.size();
    }
}
