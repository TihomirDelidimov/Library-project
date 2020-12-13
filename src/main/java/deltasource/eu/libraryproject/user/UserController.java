package deltasource.eu.libraryproject.user;

import deltasource.eu.libraryproject.person.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid User user) {
        if (userService.saveUser(user) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("User saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User couldn't be saved!");
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getbyusername")
    public ResponseEntity<?> getByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with username: " + username + " is not found!");
    }
}