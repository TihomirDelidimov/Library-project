package deltasource.eu.libraryproject.book.paperbook;

import deltasource.eu.libraryproject.book.ebook.EBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperBookServiceImpl implements PaperBookService {

    @Autowired
    PaperBookRepository paperBookRepository;

    @Override
    public PaperBook saveBook(PaperBook book) {
        return paperBookRepository.save(book);
    }

    @Override
    public List<PaperBook> getAllBooks() {
        return paperBookRepository.findAll();
    }

    @Override
    public PaperBook getBook(Long id) {
        PaperBook paperBook = null;
        try {
            paperBook = paperBookRepository.findById(id).orElseThrow(
                    () -> new Exception(id + " book doesn't exist!"));
        } catch (Exception ex) {
            return null;
        }
        return paperBook;
    }
}
