import java.util.Date;

/**
 * This class represents an author
 */
public class Author extends Person {
    private Date dateOfBirth;
    private Date dateOfDeath;

    public Author(String firstName, String lastName, Date dateOfBirth) {
        super(firstName,lastName);
        if(dateOfBirth==null) {
            throw new IllegalArgumentException("Invalid date of birth!");
        }
        this.dateOfBirth = dateOfBirth;
    }
}
