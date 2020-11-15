package deltasource.eu.libraryproject.Author;

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
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Integer id) throws Exception {
        return authorRepository.findById(id).orElseThrow(
                ()-> new Exception(id + " user doesn't exist!")
        );
    }
}
