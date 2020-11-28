package deltasource.eu.libraryproject.book.ebook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import deltasource.eu.libraryproject.author.Author;
import deltasource.eu.libraryproject.book.BookGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
}
