package deltasource.eu.libraryproject.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import sun.jvm.hotspot.utilities.Assert;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/empty.properties")
public class IsbnValidatorTest {

    @MockBean
    ConstraintValidatorContext constraintValidatorContext;

    @Autowired
    IsbnValidator isbnValidator;

    @Test
    public void setIsbnValidator_CheckForValidISBN13_OK() {
        //given
        String isbn = "978-0-1234567-1-7";



        //when
        boolean result = isbnValidator.isValid(isbn, constraintValidatorContext);

        //then
        assertTrue(result);
    }

    @Test
    public void setIsbnValidator_CheckForValidISBN10_OK() {
        //given
        String isbn = "1841499897";

        //when
        boolean result = isbnValidator.isValid(isbn,constraintValidatorContext);

        //then
        assertTrue(result);
    }

    @Test
    public void setConstraintValidatorContext_CheckForValidISBN_Invalid() {
        //given
        String isbn = "231321312-3";

        //when
        boolean result = isbnValidator.isValid(isbn,constraintValidatorContext);

        //then
        assertFalse(result);
    }
}
