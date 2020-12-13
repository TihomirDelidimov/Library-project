package deltasource.eu.libraryproject.user;

import deltasource.eu.libraryproject.person.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:testdb.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private User user;
    @BeforeEach
    private void initializeUser() {
        user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Petrov");
        user.setGender(Gender.MALE);
        user.setUsername("ivan456");
        user.setPassword("123456");
        user.setIsGdprConsent(true);
        user.setEmail("Ivan456@abv.bg");
        user.setAddress("Plovdiv, bul. 6 Septemvri");
    }

    @Test
    public void userRepository_saveAndRetrieveEntity_OK() {
        //given
        System.out.println(user.getUsername());
        System.out.println(user.getId());

        //when
        userRepository.save(user);

        //then
        assertNotNull(user.getId());
    }

    @Test
    public void userRepository_getUserByUsername_OK() {
        //given
        userRepository.save(user);

        //when
        User foundUser = userRepository.getUserByUsername(user.getUsername());

        //then
        assertNotNull(foundUser);
        assertEquals(foundUser.getUsername(),user.getUsername());
    }

    @Test
    public void userRepository_getUserByUsernameNative_OK () {
        //given
        userRepository.save(user);

        //when
        User foundUser = userRepository.getUserByUsernameNative(user.getUsername());

        //then
        assertNotNull(foundUser);
        assertEquals(foundUser.getUsername(), user.getUsername());
    }

    @Test
    public void userRepository_getUserByUsernameEM_OK () {
        //given
        userRepository.save(user);

        //when
        User foundUser = userRepository.getUserByUsernameEM(entityManager,user.getUsername());

        //then
        assertNotNull(foundUser);
        assertEquals(foundUser.getUsername(),user.getUsername());
    }
}
