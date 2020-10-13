import java.util.ArrayList;
import java.util.List;

import enumerations.BookGenre;
import enumerations.Tag;

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
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid book title!");
        }
    }

    private void validateSummary(String summary) {
        if (summary == null || summary.isEmpty()) {
            throw new IllegalArgumentException("Invalid book summary");
        }
    }

    private void validateAuthors(List<Author> authors) {
        if (authors == null || authors.size() == 0) {
            throw new IllegalArgumentException("The book must have author!");
        }
    }

    private void validateTags(List<Tag> tags) {
        if (tags == null || tags.size() == 0) {
            throw new IllegalArgumentException("The book must have a tag!");
        }
    }

    private void validateGenre(BookGenre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("The book must have genre!");
        }
    }

    public String getISBN() {
        return isbn;
    }

    /**
     * This method validates the ISBN of the book. The method checks if the ISBN is consisting of 13 digits, if that is true
     * a checksum of the ISBN is calculated. If the ISBN consist of non-numeric characters an exception is thrown.
     *
     * @param isbn - this parameter is the ISBN of the book
     */
    private void validateISBN(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("Missing ISBN!");
        }
        isbn = isbn.replaceAll("-", "");

        if (isbn.length() != 13) {
            throw new IllegalArgumentException("Incorrect ISBN!");
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
                throw new IllegalArgumentException("Incorrect ISBN");
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("The ISBN consists of non-numeric characters!");
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
    public boolean checkISBN(String isbn) {
        if (isbn != null && !isbn.isEmpty()) {
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
    public boolean checkTitle(String title) {
        if(title!=null && title.isEmpty()) {
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
    public boolean checkGenre(BookGenre genre) {
        if(genre != null) {
            return genre == this.genre;
        }
        return false;
    }
}