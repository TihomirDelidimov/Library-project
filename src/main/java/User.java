import enumerations.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.CommonValidationException;

import static CommonStringValidation.CommonStringValidator.*;

/**
 * This class represents a user, which inherits common properties from {@link Person}.
 */
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
        userDataValidation(firstName, lastName, username, password, gender, address, email, age, gdprConsent);
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.age = age;
        this.isGDPRConsent = gdprConsent;
    }

    public void userDataValidation(String firstName, String lastName, String username, String password, Gender gender,
                                   String address, String email, int age, boolean gdprConsent) {

        validateUsername(username);
        validatePassword(password);
        validateGender(gender);
        validateAddress(address);
        validateEmail(email);
        validateAge(age);
    }

    public String getUsername() {
        return username;
    }

    private void validateUsername(String username) {
        if (!isStringValid(username)) {
            throw new CommonValidationException("Missing user's username!");
        }
    }

    private void validatePassword(String password) {
        if (!isStringValid(password)) {
            throw new CommonValidationException("Missing user's password!");
        }
    }

    private void validateGender(Gender gender) {
        if (gender == null) {
            throw new CommonValidationException("Missing user's gender of the user!");
        }
    }

    private void validateAddress(String address) {
        if (!isStringValid(address)) {
            throw new CommonValidationException("Missing user's address!");
        }
    }

    private void validateEmail(String email) {
        if (!isStringValid(email)) {
            throw new CommonValidationException("Missing user's email!");
        }
    }

    private void validateAge(int age) {
        if (age <= 0) {
            throw new CommonValidationException("Age cannot be 0 or less than 0!");
        }
    }

    /**
     * This method is used when a user is trying to login, to check his username
     *
     * @param username - this parameter is the username
     * @return - this method returns true if the username is equal
     */
    public boolean isUsernameEqual(String username) {
        if (isStringValid(username)) {
            return username.equals(this.username);
        }
        return false;
    }

    /**
     * This method is used to check user's password when a user is trying to login into the library
     *
     * @param password - this parameter is the user's password
     * @return - this method return true if the supplied password match the user's password, otherwise return false
     */
    public boolean isPasswordEqual(String password) {
        if (isStringValid(password)) {
            return password.equals(this.password);
        }
        return false;
    }

    /**
     * This method is used to update user's history when the user has borrowed, read or downloaded a book
     *
     * @param book - this parameter is the book, which will be saved to the history
     * @param date - this parameter is the date, when the user has borrowed, read ot downloaded a book
     */
    public void addBookToHistory(Book book, LocalDate date) {
        if (book != null && date != null) {
            userHistoryList.add(new UserHistory(book, date));
        }
    }
}
