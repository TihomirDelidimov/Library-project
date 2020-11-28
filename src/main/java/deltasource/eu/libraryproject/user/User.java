package deltasource.eu.libraryproject.user;

import deltasource.eu.libraryproject.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 32, message = "Username must should be between 2 and 32 symbols!")
    @Column(unique = true)
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 32, message = "Password must should be between 2 and 32 symbols!")
    private String password;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128, message = "Address must should be between 2 and 128 symbols!")
    private String address;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Boolean isGdprConsent;
}