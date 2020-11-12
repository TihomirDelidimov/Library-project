package deltasource.eu.libraryproject.Users;

import deltasource.eu.libraryproject.Persons.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String firstname, @RequestParam String lastname,
            @RequestParam int age, @RequestParam String username, @RequestParam String password, @RequestParam Gender gender,
            @RequestParam String address, @RequestParam String email, @RequestParam boolean isGDPRConsent) {
        User newUser = new User(firstname,lastname,username,password,gender,address,email,age,isGDPRConsent);
        userService.saveUser(newUser);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
}