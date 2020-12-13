package deltasource.eu.libraryproject.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This class represents a set of book genres. It has nested enumeration class, which is represent a genre.
 * The constructor of the class is marked with @JsonCreator annotation to specify to Jackson deserializator
 * to use the constructor when the object is deserialized and @JsonValue annotation when the object is serialized.
 * JsonCreator.Mode.DELEGATING is used because only one parameter is being serialized/deserialized, otherwise
 * JsonCreator.MODE.PROPERTIES should be used.
 */
@Entity(name = "genre")
@NoArgsConstructor
@Getter
@Setter
public class BookGenre {

    private enum Genre {

        COMEDY("Comedy"),
        HORROR("Horror"),
        ACTION("Action"),
        ROMANCE("Romance"),
        DRAMA("Drama"),
        CRIME("Crime");

        private String value;

        Genre(String value) {
            this.value = value;
        }

        @Override
        //    @JsonValue
        public String toString() {
            return value;
        }

        /**
         * Method to get Genre enum value from given string
         * @param value - Represents the string value
         * @return Instance of Genre if the value in the parameter exist, otherwise throws {@link IllegalArgumentException}
         */
        //   @JsonCreator
        public static Genre fromValue(String value) {
            if (value != null) {
                for (Genre genre : values()) {
                    if (genre.value.equals(value)) {
                        return genre;
                    }
                }
            }
            throw new IllegalArgumentException("Invalid genre " + value);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Long id;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public BookGenre(@JsonProperty("genre") String genre) {
        this.genre = Genre.fromValue(genre);
    }

    @Override
    @JsonValue
    public String toString() {
        return genre.toString();
    }
}
