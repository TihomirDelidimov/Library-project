import exceptions.CommonValidationException;
import static CommonStringValidation.CommonStringValidator.*;

/**
 * This class represents a person with common properties, which purpose is to be inherited by multiple classes
 */
public abstract class Person {

    protected String firstName;
    protected String lastname;

    public Person(String firstName, String lastname) {
        nameValidator(firstName, lastname);
        this.firstName = firstName;
        this.lastname = lastname;
    }

    /**
     * This method is used to validate the first name and the last name of the person
     * @param firstName - this parameter is the first name of the person
     * @param lastname - this parameter is the last name of the person
     */
    private void nameValidator(String firstName, String lastname) {
        if (!isStringValid(firstName)) {
            throw new CommonValidationException("Invalid first name!");
        }
        if (!isStringValid(lastname)) {
            throw new CommonValidationException("Invalid last name!");
        }
    }

    public String getFullName() {
        return firstName + " " + lastname;
    }
}
