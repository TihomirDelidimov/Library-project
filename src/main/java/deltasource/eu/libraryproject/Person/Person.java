package deltasource.eu.libraryproject.Person;

import deltasource.eu.libraryproject.CommonExceptions.CommonValidationException;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    @Column(length = 32, nullable = false)
    protected String firstName;
    @Column(length = 32, nullable = false)
    protected String lastName;
    @Enumerated(EnumType.STRING)
    protected Gender gender;

    public Person() {

    }

    public Person(String firstName, String lastName, Gender gender) {
        personValidator(firstName,lastName,gender);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    private void personValidator(String firstName, String lastName, Gender gender) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new CommonValidationException(firstName + " is invalid first name!");
        }
        if(lastName == null || lastName.trim().isEmpty()) {
            throw new CommonValidationException(lastName + " is invalid last name!");
        }
        if(gender == null) {
            throw new CommonValidationException(gender + " is invalid gender!");
        }
    }
}
