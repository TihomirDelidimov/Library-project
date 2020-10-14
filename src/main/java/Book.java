import java.util.ArrayList;
import java.util.List;

import enumerations.BookGenre;
import enumerations.Tag;
import exceptions.CommonValidationException;

import static CommonStringValidation.CommonStringValidator.*;

/**
 * This class represent a book with common properties, which purpose is to be inherited by multiple classes
 */
public abstract class Book {

    protected String title;
    protected String summary;
    protected String isbn;
    protected BookGenre genre;
    protected List<Author> authors = new ArrayList<>();
    protected List<Tag> tags = new ArrayList<>();

    public Book(String title, String summary, String isbn, List<Author> authors, List<Tag> tags, BookGenre genre) {
        bookValidator(title, summary, isbn, authors, tags, genre);
        this.title = title;
        this.summary = summary;
        this.isbn = isbn;
        this.authors.addAll(authors);
        this.tags.addAll(tags);
        this.genre = genre;
    }

    private void bookValidator(String title, String summary, String isbn, List<Author> authors, List<Tag> tags, BookGenre genre) {
        validateTitle(title);
        validateSummary(summary);
        validateISBN(isbn);
        validateAuthors(authors);
        validateTags(tags);
        validateGenre(genre);
    }

    private void validateTitle(String title) {
        if (!isStringValid(title)) {
            throw new CommonValidationException("Invalid book title!");
        }
    }

    private void validateSummary(String summary) {
        if (!isStringValid(summary)) {
            throw new CommonValidationException("Invalid book summary");
        }
    }

    private void validateAuthors(List<Author> authors) {
        if (authors == null || authors.size() == 0) {
            throw new CommonValidationException("The book must have author!");
        }
    }

    private void validateTags(List<Tag> tags) {
        if (tags == null || tags.size() == 0) {
            throw new CommonValidationException("The book must have a tag!");
        }
    }

    private void validateGenre(BookGenre genre) {
        if (genre == null) {
            throw new CommonValidationException("The book must have genre!");
        }
    }

    public String getISBN() {
        return isbn;
    }

    public List<Tag> getTags() {
        return tags;
    }

    /**
     * This method is used to check if an author with the supplied full name exist for the current book
     * @param fullName - this parameter is the author's full name
     * @return - this method return true if it finds an author for the book with the supplied full name, otherwise return false
     */
    public boolean isThereAnAuthorWithFullName(String fullName) {
        for (Author author : authors) {
            if (author.getFullName().equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * This method validates the ISBN of the book. The method checks if the ISBN is consisting of 13 digits, if that is true
     * a checksum of the ISBN is calculated. If the ISBN consist of non-numeric characters an exception is thrown.
     *
     * @param isbn - this parameter is the ISBN of the book
     */
    private void validateISBN(String isbn) {
        if (isbn == null) {
            throw new CommonValidationException("Missing ISBN!");
        }
        isbn = isbn.replaceAll("-", "");

        if (isbn.length() != 13) {
            throw new CommonValidationException("Incorrect ISBN!");
        }

        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            int checkSum = 10 - (tot % 10);
            if (checkSum == 10) {
                checkSum = 0;
            }

            if (checkSum != Integer.parseInt(isbn.substring(12))) {
                throw new CommonValidationException("Incorrect ISBN");
            }
        } catch (NumberFormatException nfe) {
            throw new CommonValidationException("The ISBN consists of non-numeric characters!");
        }
    }

    @Override
    public String toString() {
        StringBuilder bookInfo = new StringBuilder()
                .append("\nTitle: ")
                .append(title)
                .append("\nSummary: ")
                .append(summary)
                .append("\nGenre: ")
                .append(genre)
                .append("\nISBN: ")
                .append(isbn)
                .append("\nAuthors: ");

        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            bookInfo.append(author.getFullName());
            if ((i + 1) < authors.size()) {
                bookInfo.append(", ");
            }
        }
        bookInfo.append("\nTags: ")
                .append(tags);
        return bookInfo.toString();
    }

    /**
     * This method check if a given ISBN match a book's ISBN
     *
     * @param isbn - this parameter is ISBN, which will be checked
     * @return - this method return true if the ISBNs match, otherwise return false
     */
    public boolean isISBNEqual(String isbn) {
        if (isStringValid(isbn)) {
            return isbn.equals(this.isbn);
        }
        return false;
    }

    /**
     * This method checks if a given title match a book's title
     *
     * @param title - this parameter is the title, which will be checked
     * @return - this method return true if the titles match, otherwise return false
     */
    public boolean isTitleEqual(String title) {
        if (isStringValid(title)) {
            return title.equals(this.title);
        }
        return false;
    }

    /**
     * This method checks if a given genre match a book's genre
     *
     * @param genre - this parameter is the genre, which will be checked
     * @return - this method return true if the genres match, otherwise return false
     */
    public boolean isGenreEqual(BookGenre genre) {
        if (genre != null) {
            return genre == this.genre;
        }
        return false;
    }
}