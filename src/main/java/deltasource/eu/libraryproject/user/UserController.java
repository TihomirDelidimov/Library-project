package deltasource.eu.libraryproject.user;

import deltasource.eu.libraryproject.person.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestParam String firstname, @RequestParam String lastname,
                                      @RequestParam int age, @RequestParam String username, @RequestParam String password,
                                      @RequestParam Gender gender, @RequestParam String address, @RequestParam String email,
                                      @RequestParam("gdpr") boolean isGDPRConsent) {
        User newUser = new User(firstname, lastname, username, password, gender, address, email, age, isGDPRConsent);
        if (userService.saveUser(newUser) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("User saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User couldn't be saved!");
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
}