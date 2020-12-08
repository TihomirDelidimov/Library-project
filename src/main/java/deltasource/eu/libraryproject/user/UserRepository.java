package deltasource.eu.libraryproject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.username like :username")
    User getUserByUsername(String username);
    @Query(value =
            "SELECT * " +
            "FROM user u " +
            "WHERE u.username like :username",
            nativeQuery = true)
    User getUserByUsernameNative(String username);
    default User getUserByUsernameEM(EntityManager entityManager, String username) {
        if(entityManager == null || username == null) {
            return null;
        }
        User user = (User) entityManager
                .createNativeQuery("SELECT *, 1 AS clazz_ FROM user u WHERE u.username like :username ",User.class)
                .setParameter("username",username)
                .getSingleResult();
        System.out.println(user.getId());
        return user;
    }
}
