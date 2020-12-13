package deltasource.eu.libraryproject.author;

import deltasource.eu.libraryproject.book.paperbook.PaperBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        Author author = null;
        try {
            author = authorRepository.findById(id).orElseThrow(
                    () -> new Exception(id + " author doesn't exist!"));
        } catch (Exception ex) {
            return null;
        }
        return author;
    }
}
