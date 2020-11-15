package deltasource.eu.libraryproject.User;
import deltasource.eu.libraryproject.Person.Gender;
import deltasource.eu.libraryproject.Person.Person;

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
                String address, String email, int age, boolean isGDPRConsent) {
        super(firstName,lastName,gender);
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.isGDPRConsent = isGDPRConsent;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGDPRConsent() {
        return isGDPRConsent;
    }

    public void setGDPRConsent(boolean GDPRConsent) {
        isGDPRConsent = GDPRConsent;
    }
}