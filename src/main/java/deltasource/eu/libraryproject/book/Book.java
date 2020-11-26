package deltasource.eu.libraryproject.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public abstract class Book {
    protected String title;
    protected String summary;
    protected String isbn;
    protected BookGenre bookGenre;
}
