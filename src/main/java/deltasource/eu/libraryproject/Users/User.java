package deltasource.eu.libraryproject.Users;

import deltasource.eu.libraryproject.Persons.Gender;
import deltasource.eu.libraryproject.Persons.Person;

import javax.persistence.*;

@Entity
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

    public User() {

    }

    public User(String firstName, String lastName, String username, String password, Gender gender,
                String address, String email, int age, boolean gdprConsent) {
        super(firstName,lastName,gender);

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}