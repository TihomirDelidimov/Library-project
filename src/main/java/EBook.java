import enumerations.BookGenre;
import enumerations.Tag;
import exceptions.CommonValidationException;
import interfaces.Downloadable;
import interfaces.Readable;

import static CommonStringValidation.CommonStringValidator.*;

import java.util.List;

/**
 * This class is representation of an E-Book, which can be read online or downloaded
 */
public class EBook extends Book implements Readable, Downloadable {

    private String linkToRead;
    private String linkToDownload;

    public EBook(String title, String summary, String isbn, List<Author> authors,
                 List<Tag> tags, BookGenre genre, String linkToRead, String linkToDownload) {
        super(title, summary, isbn, authors, tags, genre);
        validateLinkToRead(linkToRead);
        validateLinkToRead(linkToDownload);
        this.linkToRead = linkToRead;
        this.linkToDownload = linkToDownload;
    }

    private void validateLinkToRead(String link) {
        if (!isStringValid(link)) {
            throw new CommonValidationException("Invalid online link to read!");
        }
    }

    private void validateLinkToDownload(String link) {
        if (!isStringValid(link)) {
            throw new CommonValidationException("Invalid online link to download!");
        }
    }

    @Override
    public String getReadOnlineLink() {
        return linkToRead;
    }

    @Override
    public String getDownloadLink() {
        return linkToDownload;
    }
}
