package deltasource.eu.libraryproject.author;

import java.util.List;

public interface AuthorService {
    public abstract Author saveAuthor(Author author);
    public abstract List<Author> getAllAuthor();
    public abstract Author getAuthor(Integer id) throws Exception;
}
