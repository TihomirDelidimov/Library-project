package deltasource.eu.libraryproject.book.ebook;

import deltasource.eu.libraryproject.book.Book;
import deltasource.eu.libraryproject.book.IsbnConstraint;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EBook extends Book {

    private URL downloadUrl;
    @NotNull
    private URL readUrl;
    @NotNull
    @NotBlank
    @IsbnConstraint
    @Column(unique = true)
    private String isbn;
}
