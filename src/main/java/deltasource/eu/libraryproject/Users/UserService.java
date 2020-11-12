package deltasource.eu.libraryproject.Users;

import java.util.List;

public interface UserService {
    public abstract void saveUser(User user);
    public abstract List<User> getAllUsers();
    public abstract User getUser(Integer id) throws Exception;
}
