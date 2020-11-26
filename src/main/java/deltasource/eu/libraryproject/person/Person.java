package deltasource.eu.libraryproject.person;

import deltasource.eu.libraryproject.commonexception.CommonValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public abstract class Person {

    @Column(length = 32, nullable = false)
    protected String firstName;
    @Column(length = 32, nullable = false)
    protected String lastName;
    @Enumerated(EnumType.STRING)
    protected Gender gender;

    public Person(String firstName, String lastName, Gender gender) {
        personValidator(firstName,lastName,gender);
        this.firstName = firstName;
        this.lastName = lastName;
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
