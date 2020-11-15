package deltasource.eu.libraryproject.Book;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Book {
    protected String title;
    protected String summary;
    protected String isbn;
    protected BookGenre bookGenre;
}
