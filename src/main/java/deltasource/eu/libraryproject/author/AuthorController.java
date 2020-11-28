package deltasource.eu.libraryproject.author;

import deltasource.eu.libraryproject.person.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> add(@RequestBody Author author) {
        if (authorService.saveAuthor(author) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Author saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author couldn't be saved");
    }
}
