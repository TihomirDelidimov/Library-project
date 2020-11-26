package deltasource.eu.libraryproject.user;
import deltasource.eu.libraryproject.person.Gender;
import deltasource.eu.libraryproject.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class User extends Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length = 32, nullable = false, unique = true)
    private String username;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 64, nullable = false)
    private String address;
    @Column(length = 64, nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean isGDPRConsent;

    public User(String firstName, String lastName, String username, String password, Gender gender,
                String address, String email, int age, boolean isGDPRConsent) {
        super(firstName,lastName,gender);
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.isGDPRConsent = isGDPRConsent;
    }
}