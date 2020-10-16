import enumerations.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store a list of users and execute common operations on these users
 */
public class UserRepository {

    private List<User> users = new ArrayList<>();

    /**
     * This method is used to create a new user in the user repository. It checks if a user with the same username exist, if it does
     * the new user is not created and null value is returned.
     *
     * @param firstName   - this parameter is the first name of the user
     * @param lastName    - this parameter is the last name of the user
     * @param username    - this parameter is the username of the user
     * @param password    - this parameter is the password of the user
     * @param gender      - this parameter is the gender of the user
     * @param address     - this parameter is the address of the user
     * @param email       - this parameter is the email of the user
     * @param age         - this parameter is the age of the user
     * @param gdprConsent - this parameter indicates if the user is EU GDPR compliant
     * @return - this method return the reference to the user if the username doesn't exist for another user, otherwise the method will
     * return null value
     */
    public User createUser(String firstName, String lastName, String username, String password, Gender gender,
                           String address, String email, int age, boolean gdprConsent) {
        if (getUserByUsername(username) == null) {
            return new User(firstName, lastName, username, password, gender, address, email, age, gdprConsent);
        }
        return null;
    }

    /**
     * This method adds a new user to the user repository. If the new user has entered username, which already exist for another
     * user an exception is thrown, otherwise the new user is add to the repository
     *
     * @param newUser - this parameter is a new potential user to the repository
     */
    public void addUser(User newUser) {
        users.add(newUser);
    }

    /**
     * This method is used by the user repository to verify a user account, which is consisting of username and password.
     * The method is called when a user is trying to login with his account
     *
     * @param username - this parameter is the user's username
     * @param password - this parameter is the user's password
     * @return - this method return true if the user has entered correct both username and password, otherwise return false
     */
    public boolean verifyUserAccount(String username, String password) {
        for (User user : users) {
            if (user.isUsernameEqual(username) && user.isPasswordEqual(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method searches for a user in the user repository by the username. If a user with this username is found, the user
     * is returned, otherwise null value is returned
     *
     * @param username - this parameter is the username of the user
     * @return - this method return the user if the searching by username is successful, otherwise return null
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.isUsernameEqual(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * This method is used to save a history to a user about borrowed book, read book or downloaded book
     *
     * @param username - this parameter is the username of the user, which history will be updated
     * @param book     - this parameter is the book
     */
    public void addBookToUserHistory(String username, Book book, LocalDate date) {
        getUserByUsername(username).addBookToHistory(book, date);
    }
}
