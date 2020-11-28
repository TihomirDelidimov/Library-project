package deltasource.eu.libraryproject.book.ebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EBookServiceImpl implements EBookService {
    @Autowired
    private EBookRepository eBookRepository;

    @Override
    public List<EBook> getAllBooks() {
        return eBookRepository.findAll();
    }

    @Override
    public EBook getBook(Long id) throws Exception {
        return eBookRepository.findById(id).orElseThrow(
                ()-> new Exception(id + " book doesn't exist!")
        );
    }

    @Override
    @Transactional
    public EBook saveBook(EBook book) {
        return eBookRepository.save(book);
    }
}
