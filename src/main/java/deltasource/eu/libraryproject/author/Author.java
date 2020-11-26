package deltasource.eu.libraryproject.author;

import deltasource.eu.libraryproject.book.Book;
import deltasource.eu.libraryproject.person.Gender;
import deltasource.eu.libraryproject.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Author extends Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
  //  @ManyToMany(targetEntity = Book.class)

    public Author (String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
        super(firstName,lastName,gender);
        this.dateOfBirth = dateOfBirth;
    }

    public Author (String firstName, String lastName, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this(firstName,lastName,gender,dateOfBirth);
        this.dateOfDeath = dateOfDeath;
    }
}
