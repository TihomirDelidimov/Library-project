package deltasource.eu.libraryproject.book.paperbook;

import deltasource.eu.libraryproject.book.ebook.EBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paperbook")
public class PaperBookController {

    @Autowired
    PaperBookService paperBookService;

    @PostMapping
    public ResponseEntity<String> add(@Valid @RequestBody PaperBook paperBook) {
        if (paperBookService.saveBook(paperBook) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Paper book saved successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paper book couldn't be saved!");
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long id) {
        PaperBook paperBook = paperBookService.getBook(id);
        if (paperBook != null) {
            return ResponseEntity.status(HttpStatus.OK).body(paperBook);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<PaperBook> listOfBooks = new ArrayList<>();
        listOfBooks.addAll(paperBookService.getAllBooks());
        if (!listOfBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(listOfBooks);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no paper books in the database!");
    }
}
