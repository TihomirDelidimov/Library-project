package deltasource.eu.libraryproject.author;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import deltasource.eu.libraryproject.book.Book;
import deltasource.eu.libraryproject.person.Gender;
import deltasource.eu.libraryproject.person.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.After;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Author extends Person {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Setter(AccessLevel.NONE)
    private LocalDate dateOfDeath;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST)
    private Set<Book> books = new HashSet<>();

    /**
     * If the date of death of the author is before the birth date, exception is thrown.
     * @param dateOfDeath
     */
    public void setDateOfDeath(LocalDate dateOfDeath) {
        if(dateOfDeath == null || dateOfDeath.isBefore(dateOfBirth)) {
            throw new IllegalArgumentException("Author date of death is not set correctly!");
        }
        this.dateOfDeath = dateOfDeath;
    }
}
