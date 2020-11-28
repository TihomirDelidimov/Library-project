package deltasource.eu.libraryproject.user;

import deltasource.eu.libraryproject.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(
                ()-> new Exception(id + " user doesn't exist!")
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
      //  return userRepository.getUserByUsernameNative(username);
//        Object user = entityManager.createNativeQuery("SELECT *, 1 AS clazz FROM user u WHERE u.username like :username", User.class)
//                .setParameter("username",username)
//                .getSingleResult();
//        return (User)user;
    }
}

