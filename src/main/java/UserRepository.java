import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> userList = new ArrayList<>();

    /**
     * This method adds a new user to the user repository. If the new user has entered username, which already exist for another
     * user an exception is thrown, otherwise the new user is add to the repository
     * @param newUser - this parameter is a new potential user to the repository
     */
    public void addUser(User newUser) {
        if (newUser != null) {
            for(User user : userList ){
                if(user.checkUsername(newUser)) {
                    throw new IllegalArgumentException("Username already exist in the repository!");
                }
            }
            userList.add(newUser);
        }
    }

    /**
     * This method is used by the user repository to verify a user account, which is consisting of username and password.
     * The method is called when a user is trying to login with his account
     * @param username - this parameter is the user's username
     * @param password - this parameter is the user's password
     * @return - this method return true if the user has entered correct both username and password, otherwise return false
     */
    public boolean verifyUserAccount(String username, String password) {
        for(User user : userList) {
            if(user.checkUsername(username) && user.checkPassword(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method searches for a user in the user repository by the username. If a user with this username is found, the user
     * is returned, otherwise null value is returned
     * @param username - this parameter is the username of the user
     * @return - this method return the user if the searching by username is successful, otherwise return null
     */
    public User getUserByUsername(String username) {
        for(User user : userList ) {
            if(user.checkUsername(username)) {
                return user;
            }
        }
        return null;
    }
}
