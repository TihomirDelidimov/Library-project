package deltasource.eu.libraryproject.book.paperbook;

import java.util.List;

public interface PaperBookService {
    public abstract PaperBook saveBook(PaperBook book);
    public abstract List<PaperBook> getAllBooks();
    public abstract PaperBook getBook(Long id);
}
