import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumerations.BookGenre;
import enumerations.Tag;

import static enumerations.Tag.*;

public class Application {

    public static void main(String[] args) {
        List<Author> listOfAuthors = new ArrayList<>();
        listOfAuthors.add(new Author("George", "Martin", LocalDate.of(48, 8, 20)));
        List<Tag> listOfTags = new ArrayList<>();
        listOfTags.add(MYSTERY);
        listOfTags.add(RARE_BOOK);
        listOfTags.add(SUSPENSE);
        Book aStormOfSwords = new PaperBook("A Storm of Swords", "Summary", "9780553106633", listOfAuthors, listOfTags, BookGenre.FANTASY, 3);

        listOfAuthors = new ArrayList<>();
        listOfAuthors.add(new Author("Daniel", "Abraham", LocalDate.of(69, 10, 14)));
        System.out.println(listOfAuthors.get(0));
        listOfAuthors.add(new Author("Ty", "Frank", LocalDate.of(72, 3, 5)));
        Book leviathanWakes = new PaperBook("Leviathan Wakes", "Summary", "978-0-9706726-8-1", listOfAuthors, listOfTags, BookGenre.FANTASY, 2);

        Library library = new Library();
        library.addBook(aStormOfSwords);
        library.addBook(leviathanWakes);
        UI ui = new UI(library);
        ui.runNonLoginUI();
    }
}
