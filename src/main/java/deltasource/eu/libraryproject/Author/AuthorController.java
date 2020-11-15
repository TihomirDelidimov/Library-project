package deltasource.eu.libraryproject.Author;

import deltasource.eu.libraryproject.Person.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(path = "/add", params = {"firstname","lastname","gender","dateofbirth"} )
    public ResponseEntity<String> add(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam Gender gender,
                                      @RequestParam("dateofbirth") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateOfBirth) {
        Author author = new Author(firstName,lastName,gender,dateOfBirth);
        if (authorService.saveAuthor(author) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Author saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author couldn't be saved!");
    }

    @PostMapping(path = "/add", params = {"firstname","lastname","gender","dateofbirth","dateofdeath"} )
    public ResponseEntity<String> add(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam Gender gender,
                                      @RequestParam("dateofbirth") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateOfBirth,
                                      @RequestParam("dateofdeath") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateofdeath) {
        Author author = new Author(firstName,lastName,gender,dateOfBirth,dateofdeath);
        if (authorService.saveAuthor(author) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Author saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author couldn't be saved!");
    }
}
