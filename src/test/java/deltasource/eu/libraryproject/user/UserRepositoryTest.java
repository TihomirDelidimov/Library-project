package deltasource.eu.libraryproject.user;

import deltasource.eu.libraryproject.person.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

//    @Test
//    public void setUserRepository_saveAndRetrieveEntity_OK() {
//        //given
//
//
//        //when
//        userRepository.save(user);
//
//        //then
//        assertNotNull(user.getId());
//    }
}
