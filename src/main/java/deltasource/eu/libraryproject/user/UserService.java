package deltasource.eu.libraryproject.user;

import java.util.List;

public interface UserService {
    public abstract User saveUser(User user);
    public abstract List<User> getAllUsers();
    public abstract User getUser(Long id) throws Exception;
    public abstract User getUserByUsername(String username);
}
