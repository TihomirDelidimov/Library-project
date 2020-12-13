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
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a set of book tags. It has nested enumeration class, which is represent a book tag.
 * The constructor of the class is marked with @JsonCreator annotation to specify to Jackson deserializator
 * to use the constructor when the object is deserialized and @JsonValue annotation when the object is serialized.
 * JsonCreator.Mode.DELEGATING is used because only one parameter is being serialized/deserialized, otherwise
 * JsonCreator.MODE.PROPERTIES should be used.
 */
@Entity(name = "tag")
@NoArgsConstructor
@Getter
@Setter
public class BookTag {

    private enum Tag {

        MYSTERY("Mystery"),
        FANTASY("Fantasy"),
        SUSPENSE("Suspense"),
        RARE_BOOK("Rare book"),
        LITERATURE("Literature"),
        COMICS("Comics"),
        FOR_ADULTS("For adults"),
        FOR_KIDS("For kids");

        private String value;

        Tag(String value) {
            this.value = value;
        }

        @Override
    //    @JsonValue
        public String toString() {
            return value;
        }

        /**
         * Method to get Tag enum value from given string
         * @param value - Represents the string value
         * @return Instance of Genre if the value in the parameter exist, otherwise throws {@link IllegalArgumentException}
         */
     //   @JsonCreator
        public static Tag fromValue(String value) {
            if (value != null) {
                for (Tag tag : values()) {
                    if (tag.value.equals(value)) {
                        return tag;
                    }
                }
            }
            throw new IllegalArgumentException("Invalid tag " + value);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Tag tag;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public BookTag(@JsonProperty("tags") String tag){
        this.tag = Tag.fromValue(tag);
    }

    @Override
    public String toString() {
        return tag.toString();
    }
}

