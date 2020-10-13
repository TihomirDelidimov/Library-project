import enumerations.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    private String username;
    private String password;
    private Gender gender;
    private String address;
    private String email;
    private List<UserHistory> userHistoryList = new ArrayList<>();
    private int age;
    private boolean isGDPRConsent;

    public User(String firstName, String lastName, String username, String password, Gender gender,
                String address, String email, int age, boolean gdprConsent) {
        super(firstName, lastName);
        validateUsername(username);
        validatePassword(password);
        validateGender(gender);
        validateAddress(address);
        validateEmail(email);
        validateAge(age);
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.age = age;
        this.isGDPRConsent = gdprConsent;
    }

    public String getUsername() {
        return username;
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Missing user's username!");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Missing user's password!");
        }
    }

    private void validateGender(Gender gender) {
        if (gender == null) {
            throw new NullPointerException("Missing user's gender of the user!");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Missing user's address!");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Missing user's email!");
        }
    }

    private void validateAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be 0 or less than 0!");
        }
    }

    /**
     * This method is used when a user is trying to login, to check his username
     * @param username
     * @return
     */
    public boolean checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return username.equals(this.username);
    }

    /**
     * This method is used when a new user is registered to check if the username exist. This method is used
     * by UserRepository when a new user is registering.
     *
     * @param user - this parameter is the new user, which might be registered
     * @return - this method return true if the username is equal as the calling object's username, which in this case is user,
     * otherwise return false
     */
    public boolean checkUsername(User user) {
        if (user != null) {
            return user.username.equals(this.username);
        }
        return false;
    }

    /**
     * This method is used to check user's password when a user is trying to login into the library
     * @param password - this parameter is the user's password
     * @return - this method return true if the supplied password match the user's password, otherwise return false
     */
    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.equals(this.password);
    }

    /**
     * This method is used to update user's history when the user has borrowed, read or downloaded a book
     * @param book - this parameter is the book, which will be saved to the history
     * @param date - this parameter is the date, when the user has borrowed, read ot downloaded a book
     */
    public void updateHistory(Book book, LocalDate date) {
        if(book!=null && date!=null) {
            userHistoryList.add(new UserHistory(book,date));
        }
    }
    //read book
    //download book
    //return book
}
