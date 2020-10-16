import exceptions.CommonValidationException;

import java.time.LocalDate;

/**
 * This class represents an author
 */
public class Author extends Person {

    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    public Author(String firstName, String lastName, LocalDate dateOfBirth) {
        super(firstName, lastName);
        validateDateOfBirth(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    public Author(String firstName,String lastName, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        super(firstName,lastName);
        validateDateOfBirth(dateOfBirth);
        setDateOfDeath(dateOfDeath);
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    private void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new CommonValidationException("Missing date of birth!");
        }
    }

    private void validateDateOfDeath(LocalDate dateOfDeath) {
        if (dateOfDeath == null) {
            throw new CommonValidationException("Missing date of death");
        }
        else if(dateOfDeath.isBefore(dateOfBirth)) {
            throw new CommonValidationException("Date of death cannot be before date of birth!!");
        }
    }

    /**
     * This method is used to set the date of death for the author
     *
     * @param dateOfDeath - this parameter is the date of death
     */
    public void setDateOfDeath(LocalDate dateOfDeath) {
        if (dateOfDeath != null && dateOfDeath.isAfter(dateOfBirth)) {
            this.dateOfDeath = dateOfDeath;
        } else {
            throw new CommonValidationException("Date of death cannot be before date of birth!!");
        }
    }
}
