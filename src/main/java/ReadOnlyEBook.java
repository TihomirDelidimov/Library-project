import enumerations.BookGenre;
import enumerations.Tag;
import exceptions.CommonValidationException;
import interfaces.Readable;
import static CommonStringValidation.CommonStringValidator.*;

import java.util.List;

/**
 * This class is representation of an E-Book, which can be read online
 */
public class ReadOnlyEBook extends Book implements Readable {

    private String linkToRead;

    public ReadOnlyEBook(String title, String summary, String isbn, List<Author> authors,
                         List<Tag> tags, BookGenre genre, String linkToRead) {
        super(title, summary, isbn, authors, tags, genre);
        validateLinkToRead(linkToRead);
        this.linkToRead = linkToRead;
    }

    private void validateLinkToRead(String link) {
        if(!isStringValid(link)) {
            throw new CommonValidationException("Invalid online link to read!");
        }
    }

    @Override
    public String getReadOnlineLink() {
        return linkToRead;
    }
}
