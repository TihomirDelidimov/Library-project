package deltasource.eu.libraryproject.Author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorService {
    public abstract Author saveAuthor(Author author);
    public abstract List<Author> getAllAuthor();
    public abstract Author getAuthor(Integer id) throws Exception;
}
