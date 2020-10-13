import java.time.LocalDate;

/**
 * This class represents an author
 */
public class Author extends Person {

    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    public Author(String firstName, String lastName, LocalDate dateOfBirth) {
        super(firstName, lastName);
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Invalid date of birth!");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * This method is used to set the date of death for the author
     *
     * @param dateOfDeath - this parameter is the date of death
     */
    public void setDateOfDeath(LocalDate dateOfDeath) {
        if (dateOfDeath != null || dateOfDeath.isAfter(dateOfBirth)) {
            this.dateOfDeath = dateOfDeath;
        } else {
            throw new IllegalArgumentException("Date of death cannot be before date of birth!!");
        }
    }
}
