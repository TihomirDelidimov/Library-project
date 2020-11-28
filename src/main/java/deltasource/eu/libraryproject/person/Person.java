package deltasource.eu.libraryproject.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Person {


    @NotNull(message = "First name is required")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 40, message = "First name should be between 2 and 40 symbols")
    protected String firstName;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required!")
    @Size(min = 2, max = 40, message = "Last name should be between 2 and 40 symbols!")
    protected String lastName;
    @NotNull
    @Enumerated(EnumType.STRING)
    protected Gender gender;
}
