package deltasource.eu.libraryproject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
