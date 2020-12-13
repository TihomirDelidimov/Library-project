package deltasource.eu.libraryproject.book.ebook;

import deltasource.eu.libraryproject.book.paperbook.PaperBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EBookController {

    @Autowired
    private EBookService eBookService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> add(@Valid @RequestBody EBook ebook) {
        if(eBookService.saveBook(ebook)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body("Electronic book saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Electronic book couldn't be saved!");
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long id) {
       EBook eBook = eBookService.getBook(id);
       if(eBook!=null) {
           return ResponseEntity.status(HttpStatus.OK).body(eBook);
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<EBook> listOfBooks = new ArrayList<>();
        listOfBooks.addAll(eBookService.getAllBooks());
        if(!listOfBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(listOfBooks);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no electronic books in the database!");
    }
}
