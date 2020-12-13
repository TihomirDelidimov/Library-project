package deltasource.eu.libraryproject.book.paperbook;

import deltasource.eu.libraryproject.book.Book;
import deltasource.eu.libraryproject.book.IsbnConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaperBook extends Book {

    @IsbnConstraint
    @Column(unique = true)
    private String isbn;
}
