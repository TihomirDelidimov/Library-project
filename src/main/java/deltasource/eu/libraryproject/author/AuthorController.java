package deltasource.eu.libraryproject.author;

import deltasource.eu.libraryproject.person.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Author author) {
        if (authorService.saveAuthor(author) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Author saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author couldn't be saved");
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long id) {
        Author author = authorService.getAuthor(id);
        if (author != null) {
            return ResponseEntity.status(HttpStatus.OK).body(author);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Author> listOfAuthors = new ArrayList<>();
        listOfAuthors.addAll(authorService.getAllAuthors());
        if (!listOfAuthors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(listOfAuthors);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are not authors in the database!");
    }
}
