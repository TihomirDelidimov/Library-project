package deltasource.eu.libraryproject.book.paperbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperBookRepository extends JpaRepository<PaperBook, Long> {

}
