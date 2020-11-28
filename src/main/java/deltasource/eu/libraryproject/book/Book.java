package deltasource.eu.libraryproject.book;

import deltasource.eu.libraryproject.author.Author;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    protected Long id;
    @Column(length = 64) @NotNull @NotBlank
    protected String title;
    @NotNull @NotEmpty
    protected String summary;
    @NotNull
    protected BookGenre bookGenre;
    @ManyToMany(targetEntity = Author.class, cascade=CascadeType.ALL)
    @JoinTable(
            name="Books_Authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name="author_id")})
    protected Set<Author> authors = new HashSet<>();
}
