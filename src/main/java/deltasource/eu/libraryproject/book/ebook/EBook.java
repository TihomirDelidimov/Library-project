package deltasource.eu.libraryproject.book.ebook;

import deltasource.eu.libraryproject.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

@Getter @Setter @AllArgsConstructor
@Entity
public class EBook extends Book {
    @Id
    Integer id;
    URL downloadUrl;
    URL readUrl;
}
