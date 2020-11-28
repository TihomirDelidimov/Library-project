package deltasource.eu.libraryproject.book.ebook;

import deltasource.eu.libraryproject.user.User;

import java.util.List;

public interface EBookService {
    public abstract EBook saveBook(EBook book);
    public abstract List<EBook> getAllBooks();
    public abstract EBook getBook(Long id) throws Exception;
}
