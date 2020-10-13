import enumerations.Gender;

import java.util.Scanner;

/**
 * This class represents the whole User Interface to the library.
 */
public class UI {

    private Library library;
    private User loggedUser;
    private Scanner consoleInput = new Scanner(System.in);
    private int input;

    public UI(Library library) {
        if (library == null) {
            throw new NullPointerException("Missing library reference!");
        }
        this.library = library;
    }

    /**
     * This method is used to show the menu to the user of the application, which user is not logged yet.
     *
     * @return - this method return the menu in string format.
     */
    public String showNonLoginMenu() {
        return new StringBuilder().append("\nMenu: \n")
                .append("Press '0' to exit \n")
                .append("Press '1' to show all books \n")
                .append("Press '2' to register \n")
                .append("Press '3' to login \n")
                .toString();
    }

    /**
     * This method is used to execute a functionality to the non-logged user of the application. The first option
     * is listing all the books. The second option is to supply the user with registration. The third option is to
     * let user to login into the library.
     */
    public void runNonLoginUI() {
        do {
            System.out.print(showNonLoginMenu());
            input = consoleInput.nextInt();
            switch (input) {
                case 1:
                    System.out.println(library.showBooks());
                    break;
                case 2:
                    userRegistration();
                    break;
                case 3:
                    loggedUser = userLogin();
                    if (loggedUser != null) {
                        runLoggedUI();
                    }
                    break;
            }
        } while (input != 0);
    }

    /**
     * This method is used to show the menu to the user of the application, which user is logged successfully
     *
     * @return - this method return the menu in string format.
     */
    private String showLoggedMenu() {
        return new StringBuilder().append("\nMenu: \n")
                .append("Press '0' to exit \n")
                .append("Press '1' to show all books \n")
                .append("Press '2' to create borrow request \n")
                .append("Press '3' to show borrow requests \n")
                .append("Press '4' to borrow book \n")
                .append("Press '5' to show borrowed books \n")
                .append("Press '6' to return book the library \n ")
                .toString();
    }

    /**
     * This method is used to execute the menu, which the user will see after successful login into the library.
     */
    private void runLoggedUI() {
        do {
            System.out.print(showLoggedMenu());
            input = consoleInput.nextInt();
            switch (input) {
                case 0:
                    break;
                case 1:
                    System.out.println(library.showBooks());
                    break;
                case 2:
                    borrowBookRequestDialog();
                    break;
                case 3:
                    System.out.println(library.showBookRequests());
                    break;
                case 4:
                    borrowBookDialog();
                    break;
                case 5:
                    System.out.println(library.showBorrowedBooks());
                    break;
                case 6:
                    userReturnBookDialog();
                    break;
                case 7:
                    System.out.println(library.showUserWaitingList(loggedUser));
                    break;
            }
        } while (input != 0);
    }

    private void userReturnBookDialog() {
        System.out.print("\nReturning paper book ");
        System.out.print("\nEnter username of the user: ");
        String username = consoleInput.next();
        System.out.print("\nEnter isbn of the book: ");
        String isbn = consoleInput.next();

        library.userReturnedBook(username, isbn);
    }

    private void borrowBookRequestDialog() {
        System.out.print("\nBorrow request for a paper book");
        System.out.print("\nEnter ISBN of the book, to create borrow request: ");
        String isbn = consoleInput.next();
        library.borrowBookRequest(loggedUser, isbn);
    }

    /**
     * This method is used to show a dialog from, which the user can borrow a book
     */
    private void borrowBookDialog() {
        System.out.print("\nBorrowing the book from the library");
        System.out.print("\nEnter username of the user, which is borrowing a book: ");
        String username = consoleInput.next();
        System.out.print("\nEnter ISBN of the book, which the user is borrowing: ");
        String isbn = consoleInput.next();
        library.userBorrowedBook(username, isbn);
    }

    /**
     * This method is used to authenticate the user, which is trying to login into the system. If the user
     * exists a reference to the User object is returned.
     *
     * @return - this method return User reference if the user exist in the library's user repository and his
     * login information is correct, otherwise return false
     */
    private User userLogin() {
        System.out.println("\nLogin");
        System.out.print("Enter your username: ");
        String username = consoleInput.next();
        System.out.print("Enter your password: ");
        String password = consoleInput.next();
        if (library.authenticateUser(username, password)) {
            return library.getUserByUsername(username);
        }
        System.out.println("Wrong login information!");
        return null;
    }

    /**
     * This method is used to supply the user with registration functionality
     */
    private void userRegistration() {
        System.out.println("\nRegistration");
        System.out.print("Enter your first name: ");
        String firstName = consoleInput.next();
        System.out.print("Enter your last name: ");
        String lastName = consoleInput.next();
        System.out.print("Enter your username: ");
        String username = consoleInput.next();
        System.out.print("Enter your password: ");
        String password = consoleInput.next();
        System.out.print("Enter gender (male or female): ");
        Gender gender = Gender.valueOf(consoleInput.next().trim().toUpperCase());
        System.out.print("Enter full address: ");
        String address = consoleInput.next();
        System.out.print("Enter your email: ");
        String email = consoleInput.next();
        System.out.print("Enter your age: ");
        int age = consoleInput.nextInt();
        boolean isUserGDPRConsent = getUserGDPRConsent();

        if (isUserGDPRConsent) {
            library.addUser(new User(firstName, lastName, username, password, gender, address, email, age, isUserGDPRConsent));
        } else {
            System.out.println("Without EU GDPR consent a new account cannot be created!");
        }
    }

    /**
     * This method asks the user's consent about EU GDPR
     *
     * @return - this method return true if the user has given his consent, otherwise return false
     */
    private boolean getUserGDPRConsent() {
        System.out.println("Do you give your consent about EU GDPR?");
        System.out.println("Press '1' to consent!");
        System.out.println("Press '2' to decline!");
        return consoleInput.nextInt() == 1;
    }
}
