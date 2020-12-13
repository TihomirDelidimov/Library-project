package deltasource.eu.libraryproject.book;

import deltasource.eu.libraryproject.author.Author;
import deltasource.eu.libraryproject.book.ebook.EBook;
import deltasource.eu.libraryproject.book.ebook.EBookRepository;
import deltasource.eu.libraryproject.user.UserRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:testdb.properties")
public class EBookRepositoryTest {

    @Autowired
    private EBookRepository eBookRepository;

    private EBook eBook;

    @BeforeEach
    private void initializeEBook() throws MalformedURLException {
        Author john = new Author();
        eBook = new EBook();
        eBook.setTitle("Leviathan wakes");
        eBook.setSummary("Short summary");
        eBook.setIsbn("9783161484100");
        eBook.setGenre(new BookGenre("Drama"));
        eBook.setTags(Sets.newHashSet(Arrays.asList(new BookTag("Mystery"), new BookTag("Fantasy"))));
        eBook.setAuthors(Sets.newHashSet(Arrays.asList(john)));
        eBook.setReadUrl(new URL("https://www.google.com"));
        eBook.setDownloadUrl(new URL("https://www.google.com"));
    }

    @Test
    public void eBookRepository_SaveEBook_OK(){
        //given

        //when
        eBookRepository.save(eBook);

        //then
        assertNotNull(eBook.getId());
    }

    @Test
    public void eBookRepository_findBookById_OK() throws Exception {
        //given
        eBookRepository.save(eBook);

        //when
        EBook foundBook = eBookRepository.findById(new Long(eBook.getId())).get();

        //then
        assertNotNull(eBook.getId());
        assertEquals(eBook.getId(),foundBook.getId());
    }
}
