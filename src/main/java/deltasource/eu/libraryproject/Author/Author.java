package deltasource.eu.libraryproject.Author;

import deltasource.eu.libraryproject.Person.Gender;
import deltasource.eu.libraryproject.Person.Person;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Author extends Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private LocalDate dateOfDeath;

    public Author (String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
        super(firstName,lastName,gender);
        this.dateOfBirth = dateOfBirth;
    }

    public Author (String firstName, String lastName, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this(firstName,lastName,gender,dateOfBirth);
        this.dateOfDeath = dateOfDeath;
    }
}
