import enumerations.BookGenre;

import static enumerations.Gender.*;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static enumerations.Tag.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library = new Library();

    @BeforeEach
    public void loadDataToTheLibraryTest() {
        Author georgeMartin = new Author("George", "Martin", LocalDate.of(1948, 8, 20));
        Author danielAbraham = new Author("Daniel", "Abraham", LocalDate.of(1969, 10, 14));
        Author tyFrank = new Author("Ty", "Frank", LocalDate.of(72, 3, 5));
        library.addPaperBook("A Storm of Swords", "Summary", "9780553106633",
                Arrays.asList(georgeMartin), Arrays.asList(MYSTERY, RARE_BOOK, SUSPENSE), BookGenre.FANTASY, 3);
        library.addPaperBook("Leviathan Wakes", "Summary", "978-0-9706726-8-1",
                Arrays.asList(danielAbraham, tyFrank), Arrays.asList(MYSTERY, RARE_BOOK, SUSPENSE), BookGenre.FANTASY, 2);
        library.registerUser("Ivan", "Ivanov", "Ivan123", "ivan670515", MALE,
                "Plovdiv, bul Osvobojdenie, number: 6", "Ivan123@abv.bg", 25, true);
    }

    @Test
    public void checkUserCredentials_ValidCredentials_ShouldReturnTrue() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri"
                , "pesho97@abv.bg", 23, true);

        //when
        boolean isUserRegistered = library.authenticateUser("Pesho97", "pesho");

        //then
        assertTrue(isUserRegistered);
    }

    @Test
    public void checkUserCredentials_IncorrectUsername_ShouldReturnFalse() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri"
                , "pesho97@abv.bg", 23, true);

        //when
        boolean isUserRegistered = library.authenticateUser("Pesho9", "pesho");

        //then
        assertFalse(isUserRegistered);
    }

    @Test
    public void checkUserCredentials_IncorrectPassword_ShouldReturnFalse() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri"
                , "pesho97@abv.bg", 23, true);

        //when
        boolean isUserRegistered = library.authenticateUser("Pesho97", "peshoo");

        //then
        assertFalse(isUserRegistered);
    }

    @Test
    public void searchForBooksByTitle_ShouldReturnExistingBook() {
        //when
        List<Book> searchResult = library.searchBooksByTitle("Leviathan Wakes");

        //then
        assertNotNull(searchResult);
        assertEquals(1, searchResult.size());
    }

    @Test
    public void searchForBooksByTitle_BookDoesNotExist_ShouldReturnEmptyList() {
        //when
        List<Book> searchResult = library.searchBooksByTitle("This book name doesn't exist");

        //then
        assertNotNull(searchResult);
        assertEquals(0, searchResult.size());
    }

    @Test
    public void searchForBooksByTitle_EmptyName_ShouldReturnNullValue() {
        //when
        List<Book> searchResult = library.searchBooksByTitle("");

        //then
        assertNull(searchResult);
    }

    @Test
    public void searchForBooksByGenre_ShouldReturnTwoBooks() {
        //when
        List<Book> searchResult = library.searchBooksByTags(MYSTERY);

        //then
        assertNotNull(searchResult);
        assertEquals(2, searchResult.size());
    }

    @Test
    public void searchForBooksByGenre_BookDoesNotExist_ShouldReturnEmptyList() {
        //when
        List<Book> searchResult = library.searchBooksByTags(FOR_ADULTS);

        //then
        assertNotNull(searchResult);
        assertEquals(0, searchResult.size());
    }

    @Test
    public void searchForBooksByGenre_NullValueParameter_ShouldReturnNullValue() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFullName(null);

        //then
        assertNull(searchResult);
    }

    @Test
    public void searchForBooksByGenre_EmptyName_ShouldReturnNullValue() {
        //when
        List<Book> searchResult = library.searchBooksByTags();

        //then
        assertNull(searchResult);
    }

    @Test
    public void searchForBooksByAuthorFullName_ShouldReturnOneBook() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFullName("Ty Frank");

        //then
        assertNotNull(searchResult);
        assertEquals(1, searchResult.size());
    }

    @Test
    public void searchForBooksByAuthorFullName_AuthorDoesNotExist_ShouldReturnEmptyList() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFullName("Jim Austin");

        //then
        assertNotNull(searchResult);
        assertEquals(0, searchResult.size());
    }

    @Test
    public void searchForBooksByAuthorFullName_NullValueParameter_ShouldReturnNulLValue() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFullName(null);

        //then
        assertNull(searchResult);
    }

    @Test
    public void searchForBooksByAuthorFirstName_ShouldReturnOneBook() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFirstName("Ty");

        //then
        assertNotNull(searchResult);
        assertEquals(1, searchResult.size());
    }

    @Test
    public void searchForBooksByAuthorFirstName_AuthorDoesNotExist_ShouldReturnEmptyList() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFirstName("Jerry");

        //then
        assertNotNull(searchResult);
        assertEquals(0, searchResult.size());
    }

    @Test
    public void searchForBooksByAuthorFullName_NullValueParameter_ShouldReturnNullValue() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorFullName(null);

        //then
        assertNull(searchResult);
    }


    @Test
    public void searchForBooksByAuthorLastName_ShouldReturnOneBook() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorLastName("Frank");

        //then
        assertNotNull(searchResult);
        assertEquals(1, searchResult.size());
    }

    @Test
    public void searchForBooksByAuthorLastName_AuthorDoesNotExist_ShouldReturnEmptyList() {
        //when
        List<Book> searchResult = library.searchForBooksByAuthorLastName("Jerry O'Riley");

        //then
        assertNotNull(searchResult);
        assertEquals(0, searchResult.size());
    }

    @Test
    public void addingAReadOnlyEBook_IsTheBookAdded_TheBookIsAdded() {
        //given
        int libraryBookCountBeforeAdd = library.getLibraryBookCount();
        Author neilGaiman = new Author("Neil", "Gaiman", LocalDate.of(1960, 10, 10));

        //when
        boolean isAdded = library.addReadOnlyEBook("American Gods", "Summary", "978-0062080233", Arrays.asList(neilGaiman),
                Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY, "path to the file for reading");

        //then
        assertEquals(2, libraryBookCountBeforeAdd);
        assertNotEquals(libraryBookCountBeforeAdd, library.getLibraryBookCount());
        assertEquals(3, library.getLibraryBookCount());
        assertTrue(isAdded);
    }

    @Test
    public void addingAReadOnlyEBook_BookISBNAlreadyExist_ShouldReturnNullValue() {
        //given
        int libraryBookCountBeforeAdd = library.getLibraryBookCount();
        Author neilGaiman = new Author("Neil", "Gaiman", LocalDate.of(1960, 10, 10));

        //when
        boolean isAdded = library.addReadOnlyEBook("American Gods", "Summary", "9780553106633", Arrays.asList(neilGaiman),
                Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY, "path to the file for reading");

        //then
        assertFalse(isAdded);
        assertEquals(libraryBookCountBeforeAdd, library.getLibraryBookCount());
    }

    @Test
    public void addingAReadOnlyEBook_InvalidISBN_ShouldThrowException() {
        //given
        int libraryBookCountBeforeAdd = library.getLibraryBookCount();
        Author neilGaiman = new Author("Neil", "Gaiman", LocalDate.of(1960, 10, 10));
        String expectedMessage = "Incorrect ISBN";

        //when
        Exception exception = assertThrows(InvalidISBNException.class, () ->
                library.addReadOnlyEBook("American Gods", "Summary", "978-00620802", Arrays.asList(neilGaiman),
                        Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY, "path to the file for reading"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void searchForBooksByTitle_AddedReadOnlyEBookBeforeSearching_ShouldReturnOneBook() {
        //given
        List<Book> searchResultBeforeAdding = library.searchBooksByTitle("American Gods");
        Author neilGaiman = new Author("Neil", "Gaiman", LocalDate.of(1960, 10, 10));
        library.addReadOnlyEBook("American Gods", "Summary", "978-0062080233", Arrays.asList(neilGaiman),
                Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY, "path to the file for reading");

        //when
        List<Book> searchResultAfterAdding = library.searchBooksByTitle("American Gods");

        //then
        assertNotNull(searchResultBeforeAdding);
        assertNotNull(searchResultAfterAdding);
        assertNotEquals(searchResultBeforeAdding, searchResultAfterAdding);
    }

    @Test
    public void addingAnEBook_IsTheBookAdded_TheBookIsAdded() {
        //given
        int libraryBookCountBeforeAdd = library.getLibraryBookCount();
        Author brianBendis = new Author("Brian", "Bendis", LocalDate.of(1967, 8, 18));
        Author jonathanHickman = new Author("Jonathan", "Hickman", LocalDate.of(1972, 9, 3));
        Author nickSpencer = new Author("Nick", "Spencer", LocalDate.of(1981, 3, 3));

        //when
        boolean isAdded = library.addEBook("Ultimate Fallout", "Summary", "978-0785159131",
                Arrays.asList(brianBendis, jonathanHickman, nickSpencer), Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY,
                "path to the file for reading", "path to the file for downloading");

        //then
        assertTrue(isAdded);
        assertNotEquals(libraryBookCountBeforeAdd, library.getLibraryBookCount());
        assertEquals(3, library.getLibraryBookCount());
    }

    @Test
    public void addingAnEBookBook_ISBNAlreadyExist_ShouldReturnNullValue() {
        //given
        int libraryBookCountBeforeAdd = library.getLibraryBookCount();
        Author brianBendis = new Author("Brian", "Bendis", LocalDate.of(1967, 8, 18));
        Author jonathanHickman = new Author("Jonathan", "Hickman", LocalDate.of(1972, 9, 3));
        Author nickSpencer = new Author("Nick", "Spencer", LocalDate.of(1981, 3, 3));

        //when
        boolean isAdded = library.addEBook("Ultimate Fallout", "Summary", "978-0-9706726-8-1",
                Arrays.asList(brianBendis, jonathanHickman, nickSpencer), Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY,
                "path to the file for reading", "path to the file for downloading");

        //then
        assertFalse(isAdded);
        assertEquals(libraryBookCountBeforeAdd, library.getLibraryBookCount());
    }

    @Test
    public void addingAnEBook_InvalidISBN_ShouldThrowException() {
        //given
        int libraryBookCountBeforeAdd = library.getLibraryBookCount();
        Author brianBendis = new Author("Brian", "Bendis", LocalDate.of(1967, 8, 18));
        Author jonathanHickman = new Author("Jonathan", "Hickman", LocalDate.of(1972, 9, 3));
        Author nickSpencer = new Author("Nick", "Spencer", LocalDate.of(1981, 3, 3));
        String expectedMessage = "Incorrect ISBN!";

        //when
        Exception exception = assertThrows(InvalidISBNException.class, () ->
                library.addEBook("Ultimate Fallout", "Summary", "978-0-9",
                        Arrays.asList(brianBendis, jonathanHickman, nickSpencer), Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY,
                        "path to the file for reading", "path to the file for downloading"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void getReadLinkForABook_CanTheUserGetReadLinkForReadOnlyEBook_ShouldBeAble() {
        //given
        Author neilGaiman = new Author("Neil", "Gaiman", LocalDate.of(1960, 10, 10));
        library.addReadOnlyEBook("American Gods", "Summary", "978-0062080233", Arrays.asList(neilGaiman),
                Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY, "path to the file for reading");
        String expectedReadLink = "path to the file for reading";

        //when
        String actualReadLink = library.getBookOnlineLinkToRead("Ivan123", "978-0062080233");

        //then
        assertEquals(expectedReadLink, actualReadLink);
    }

    @Test
    void getReadLinkForABook_CanTheUserGetReadLinkForEBook_ShouldBeAble() {
        //given
        Author brianBendis = new Author("Brian", "Bendis", LocalDate.of(1967, 8, 18));
        Author jonathanHickman = new Author("Jonathan", "Hickman", LocalDate.of(1972, 9, 3));
        Author nickSpencer = new Author("Nick", "Spencer", LocalDate.of(1981, 3, 3));
        library.addEBook("Ultimate Fallout", "Summary", "978-0785159131",
                Arrays.asList(brianBendis, jonathanHickman, nickSpencer), Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY,
                "path to the file for reading", "path to the file for downloading");
        String expectedReadLink = "path to the file for reading";

        //when
        String actualReadLink = library.getBookOnlineLinkToRead("Ivan123", "978-0785159131");

        //then
        assertEquals(expectedReadLink, actualReadLink);
    }

    @Test
    public void getReadLinkForABook_CanTheUserGetReadLinkForPaperBook_ShouldThrowException() {
        //given
        String expectedMessage = "A book with ISBN: 978-0-9706726-8-1 is not an electronic book!";

        //when
        Exception exception = assertThrows(TheBookDoesNotHaveReadLink.class,
                () -> library.getBookOnlineLinkToRead("Ivan123", "978-0-9706726-8-1"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void getDownloadLinkForABook_CanAUserGetDownloadLinkForADownloadableBook_ShouldBeAbble() {
        //given
        Author brianBendis = new Author("Brian", "Bendis", LocalDate.of(1967, 8, 18));
        Author jonathanHickman = new Author("Jonathan", "Hickman", LocalDate.of(1972, 9, 3));
        Author nickSpencer = new Author("Nick", "Spencer", LocalDate.of(1981, 3, 3));
        library.addEBook("Ultimate Fallout", "Summary", "978-0785159131",
                Arrays.asList(brianBendis, jonathanHickman, nickSpencer), Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY,
                "path to the file for reading", "path to the file for downloading");
        String expectedDownloadLink = "path to the file for downloading";

        //when
        String actualDownloadLink = library.getBookOnlineLinkToDownload("Ivan123", "978-0785159131");

        //then
        assertEquals(expectedDownloadLink, actualDownloadLink);
    }

    @Test
    public void getDownloadLinkForABook_CanTheUserGetDownloadLinkFromReadOnlyEBook_ShouldThrowException() {
        //given
        Author neilGaiman = new Author("Neil", "Gaiman", LocalDate.of(1960, 10, 10));
        library.addReadOnlyEBook("American Gods", "Summary", "978-0062080233", Arrays.asList(neilGaiman),
                Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY, "path to the file for reading");
        String expectedMessage = "A book with ISBN: 978-0062080233 does not have download link!";

        //when
        Exception exception = assertThrows(TheBookDoesNotHaveDownloadLink.class,
                () -> library.getBookOnlineLinkToDownload("Ivan123", "978-0062080233"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void getDownloadLinkForABook_CanTheUserGetDownloadLinkFromPaperBook_ShouldThrowException() {
        //given
        String expectedMessage = "A book with ISBN: 978-0-9706726-8-1 does not have download link!";

        //when
        Exception exception = assertThrows(TheBookDoesNotHaveDownloadLink.class,
                () -> library.getBookOnlineLinkToDownload("Ivan123", "978-0-9706726-8-1"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void searchForBooksByTitle_AddedEBookBeforeSearching_ShouldReturnOneBook() {
        //given
        List<Book> searchResultBeforeAdding = library.searchBooksByTitle("Ultimate Fallout");
        Author brianBendis = new Author("Brian", "Bendis", LocalDate.of(1967, 8, 18));
        Author jonathanHickman = new Author("Jonathan", "Hickman", LocalDate.of(1972, 9, 3));
        Author nickSpencer = new Author("Nick", "Spencer", LocalDate.of(1981, 3, 3));
        library.addEBook("Ultimate Fallout", "Summary", "978-0785159131",
                Arrays.asList(brianBendis, jonathanHickman, nickSpencer), Arrays.asList(FANTASY, MYSTERY), BookGenre.FANTASY,
                "path to the file for reading", "path to the file for downloading");

        //when
        List<Book> searchResultAfterAdding = library.searchBooksByTitle("Ultimate Fallout");

        //then
        assertNotNull(searchResultBeforeAdding);
        assertNotNull(searchResultAfterAdding);
        assertNotEquals(searchResultBeforeAdding, searchResultAfterAdding);
    }

    @Test
    public void createBorrowRequestForBook_IsNewRequestCreated_ShouldCreateNewRequest() {
        //given
        int initialNumberOfRequests = library.showBookRequests().size();

        //when
        library.borrowBookRequest("Ivan123", "9780553106633");

        //then
        int numberOfRequestsAfterCreatedNew = library.showBookRequests().size();
        assertNotEquals(initialNumberOfRequests, numberOfRequestsAfterCreatedNew);
        assertTrue(initialNumberOfRequests < numberOfRequestsAfterCreatedNew);
    }

    @Test
    public void createBorrowRequestForBook_IsBookAvailableAfterRequestingMaximumQuantity_ShouldNotBeAvailable() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri"
                , "pesho97@abv.bg", 23, true);
        PaperBook paperBook = (PaperBook) library.getBookByISBN("978-0-9706726-8-1");
        library.borrowBookRequest("Pesho97", "978-0-9706726-8-1");

        //when
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");

        //then
        assertFalse(paperBook.isAvailable());
    }

    @Test
    public void createBorrowRequestForBook_EmptyUsername_ShouldThrowException() {
        //given
        String expectedMessage = "The user doesn't exist!";

        //when
        Exception exception = assertThrows(UserNotFoundException.class, () -> library.borrowBookRequest("", "9780553106633"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void createBorrowRequestForBook_NullValueForUsername_ShouldThrowException() {
        //given
        String expectedMessage = "The user doesn't exist!";

        //when
        Exception exception = assertThrows(UserNotFoundException.class, () -> library.borrowBookRequest(null, "9780553106633"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void createBorrowRequestForBook_EmptyISBN_ShouldThrowException() {
        //given
        String expectedMessage = "Empty ISBN!";

        //when
        Exception exception = assertThrows(InvalidISBNException.class, () -> library.borrowBookRequest("Ivan123", ""));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void createBorrowRequestForBook_NullValueISBN_ShouldThrowException() {
        //given
        String expectedMessage = "Empty ISBN!";

        //when
        Exception exception = assertThrows(InvalidISBNException.class, () -> library.borrowBookRequest("Ivan123", null));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void createBorrowRequestForBook_IncorrectISBN_ShouldThrowException() {
        //given
        String expectedMessage = "There is not paper book with this ISBN: 2323!";

        //when
        Exception exception = assertThrows(InvalidISBNException.class, () -> library.borrowBookRequest("Ivan123", "2323"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void createBorrowRequestsForBookForTheSameUser_IsAnotherRequestCreated_ShouldThrowException() {
        //given
        library.borrowBookRequest("Ivan123", "9780553106633");
        String expectedMessage = "This user: Ivan123 already has a request for a book with this ISBN: 9780553106633!";

        //when
        Exception exception = assertThrows(UserAlreadyHasBorrowRequestException.class, () ->
                library.borrowBookRequest("Ivan123", "9780553106633"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void createBorrowRequestsForABookForThreeUsers_IsThirdRequestCreated_ShouldNotCreateThirdRequest() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.registerUser("Petyr", "Georgiev", "petyr1", "petyr123", MALE, "Address", "petyt1@abv.bg", 31, true);
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        int numberOfRequestsBeforeFirstAttempt = library.showBookRequests().size();
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        int numberOfRequestsBeforeThirdAttempt = library.showBookRequests().size();

        //when
        library.borrowBookRequest("petyr1", "978-0-9706726-8-1");

        //then
        assertEquals(1, numberOfRequestsBeforeFirstAttempt);
        assertEquals(2, numberOfRequestsBeforeThirdAttempt);
        assertEquals(2, library.showBookRequests().size());
    }

    @Test
    public void createBorrowRequestsForMultipleBooks_IsAUserAbleToBorrowTwoDifferentBooks_ShouldBeAble() {
        //given
        int initialBorrowRequestNumber = library.showBookRequests().size();
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");

        //when
        library.borrowBookRequest("Ivan123", "9780553106633");

        //then
        assertEquals(0, initialBorrowRequestNumber);
        assertEquals(2, library.showBookRequests().size());
    }

    @Test
    public void createBorrowRequestsForMultipleBooks_AreMultipleUsersAbleToRequestMultipleBooks_ShouldBeAble() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        int initialNumberOfRequests = library.showBookRequests().size();
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");

        //when
        library.borrowBookRequest("Ivan123", "9780553106633");
        library.borrowBookRequest("pesho1", "9780553106633");

        //then
        assertEquals(0, initialNumberOfRequests);
        assertEquals(4, library.showBookRequests().size());
    }

    @Test
    public void createBorrowRequestsForABookForThreeUsers_DoesTheThirdUserWaitInQueue_ThirdUserShouldBeInQueue() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.registerUser("Petyr", "Georgiev", "petyr1", "petyr123", MALE, "Address", "petyt1@abv.bg", 31, true);
        User ivan123 = library.getUserByUsername("Ivan123");
        User pesho1 = library.getUserByUsername("pesho1");
        User petyr1 = library.getUserByUsername("petyr1");
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");

        //when
        library.borrowBookRequest("petyr1", "978-0-9706726-8-1");

        //then
        PaperBook paperBook = (PaperBook) library.getBookByISBN("978-0-9706726-8-1");
        int ivan123Position = paperBook.getUserQueuePosition(ivan123);
        int pesho1Position = paperBook.getUserQueuePosition(pesho1);
        int petyr1Position = paperBook.getUserQueuePosition(petyr1);
        assertTrue(ivan123Position < 0);
        assertTrue(pesho1Position < 0);
        assertEquals(ivan123Position, pesho1Position);
        assertEquals(1, petyr1Position);
    }

    @Test
    public void createMultipleBorrowRequestsForAUsers_CanTheUserWaitForMultipleBooks_ShouldBeAble() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.registerUser("Petyr", "Georgiev", "petyr1", "petyr123", MALE, "Address", "petyt1@abv.bg", 31, true);
        library.registerUser("Petyar", "Ivanova", "petya1", "petya123", FEMALE, "Address", "petyaabv.bg", 31, true);
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "9780553106633");
        library.borrowBookRequest("petyr1", "978-0-9706726-8-1");
        library.borrowBookRequest("petyr1", "9780553106633");
        library.borrowBookRequest("petya1", "9780553106633");
        User ivan123 = library.getUserByUsername("Ivan123");
        PaperBook aStormOfSwords = (PaperBook) library.getBookByISBN("9780553106633");
        PaperBook leviathanWakes = (PaperBook) library.getBookByISBN("978-0-9706726-8-1");
        int sizeOfQueueForStormOfSwords = aStormOfSwords.getQueueSize();
        int sizeOfQueueForLeviathanWakes = leviathanWakes.getQueueSize();

        //when
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("Ivan123", "9780553106633");

        //then
        assertEquals(0, sizeOfQueueForStormOfSwords);
        assertEquals(0, sizeOfQueueForLeviathanWakes);
        assertEquals(1, aStormOfSwords.getQueueSize());
        assertEquals(1, leviathanWakes.getQueueSize());
    }

    @Test
    public void borrowBook_CanABookBeBorrowedWithEmptyISBN_ShouldThrowException() {
        //given
        String expectedMessage = "Empty ISBN";

        //when
        Exception exception = assertThrows(InvalidISBNException.class, () -> library.borrowBookRequest("Ivan123", ""));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void borrowBook_canABookBeBorrowedWithNullValueForISBN_ShouldThrowException() {
        //given
        String expectedMessage = "Empty ISBN";

        //when
        Exception exception = assertThrows(InvalidISBNException.class,
                () -> library.borrowBookRequest("Ivan123", null));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void borrowBook_CanAbookBeBorrowedByEmptyUsername_ShouldThrowException() {
        //given
        String expectedMessage = "The user doesn't exist!";

        //when
        Exception exception = assertThrows(UserNotFoundException.class,
                () -> library.borrowBookRequest("", "978-0-9706726-8-1"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void borrowBook_CanABookBeBorrowedWithNullValueForUsername_ShouldThrowException() {
        //given
        String expectedMessage = "The user doesn't exist!";

        //when
        Exception exception = assertThrows(UserNotFoundException.class,
                () -> library.borrowBookRequest(null, "978-0-9706726-8-1"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void borrowBookForAUser_IsTheBookBorrowed_TheBookShouldBeBorrowed() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        int initialNnumberOfBorrowedBooks = library.showBorrowedBooks().size();

        //when
        library.borrowBook("Ivan123", "978-0-9706726-8-1");

        //then
        assertNotEquals(initialNnumberOfBorrowedBooks, library.showBorrowedBooks().size());
    }

    @Test
    public void borrowBookForAUser_IsTheListWithRequestsUpdatedAfterBorrowing_ShouldBeUpdated() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        int numberOfRequests = library.showBookRequests().size();

        //when
        library.borrowBook("Ivan123", "978-0-9706726-8-1");

        //then
        assertNotEquals(numberOfRequests, library.showBookRequests().size());
    }

    @Test
    public void borrowBookForAMultipleUsers_CanABookBeBorrowedByTwoUsers_ShouldBeAble() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");
        int numberOfBorrowedBooksBeforeSecondBorrowing = library.showBorrowedBooks().size();

        //when
        library.borrowBook("pesho1", "978-0-9706726-8-1");

        //then
        assertEquals(1, numberOfBorrowedBooksBeforeSecondBorrowing);
        assertEquals(2, library.showBorrowedBooks().size());
    }

    @Test
    public void borrowMultipleBooksForAMultipleUsers_AreMultipleUsersAbleToBorrowMultipleBooks_ShouldBeAble() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        library.borrowBookRequest("Ivan123", "9780553106633");
        library.borrowBookRequest("pesho1", "9780553106633");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("pesho1", "9780553106633");
        int numberOfBorrowedBooks = library.showBorrowedBooks().size();

        //when
        library.borrowBook("Ivan123", "9780553106633");
        library.borrowBook("pesho1", "978-0-9706726-8-1");

        //then
        assertEquals(2, numberOfBorrowedBooks);
        assertEquals(4, library.showBorrowedBooks().size());
    }

    @Test
    public void borrowBookForAUser_BorrowBookWithoutRequest_ShouldThrowException() {
        //given
        String expectedMessage = "Trying to borrow a book without a request!";

        //when
        Exception exception = assertThrows(BorrowRequestDoesNotExistException.class, () ->
                library.borrowBook("Ivan123", "978-0-9706726-8-1"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void returnBook_CanASingleBorrowedBookBeBeReturnedByTheUser_BorrowedBooksListShouldBeEmpty() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");
        boolean isBorrowedBooksListEmptyBeforeReturn = library.showBorrowedBooks().isEmpty();

        //when
        library.returnBook("Ivan123", "978-0-9706726-8-1");

        //then
        assertFalse(isBorrowedBooksListEmptyBeforeReturn);
        assertTrue(library.showBorrowedBooks().isEmpty());
    }

    @Test
    public void returnBooks_CanAUserReturnTwoBooks_ShouldBeAble() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("Ivan123", "9780553106633");
        library.borrowBook("Ivan123", "9780553106633");
        library.returnBook("Ivan123", "978-0-9706726-8-1");
        int numberOfBorrowedBooksBeforeSecondReturn = library.showBorrowedBooks().size();

        //when
        library.returnBook("Ivan123", "9780553106633");

        //then
        assertEquals(1, numberOfBorrowedBooksBeforeSecondReturn);
        assertEquals(0, library.showBorrowedBooks().size());
    }

    @Test
    public void returnBooks_CanMultipleUsersReturnMultipleBooks_ShouldBeAblea() {
        //given
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("Ivan123", "9780553106633");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "9780553106633");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "9780553106633");
        library.borrowBook("pesho1", "978-0-9706726-8-1");
        library.borrowBook("pesho1", "9780553106633");
        library.returnBook("Ivan123", "978-0-9706726-8-1");
        library.returnBook("pesho1", "9780553106633");
        int numberOfBorrowedBooksBeforeReturns = library.showBorrowedBooks().size();

        //when
        library.returnBook("Ivan123", "9780553106633");
        library.returnBook("pesho1", "978-0-9706726-8-1");

        //then
        assertEquals(2, numberOfBorrowedBooksBeforeReturns);
        assertEquals(0, library.showBorrowedBooks().size());
    }

    @Test
    public void returnBook_CanABookBeReturnedWithoutRequest_ExceptionShouldBeThrown() {
        //given
        String expectedMessage = "A book with ISBN: 978-0-9706726-8-1 is not borrwed!";

        //when
        Exception exception = assertThrows(ReturningANonBorrowedBookException.class, () ->
                library.returnBook("Ivan123", "978-0-9706726-8-1"));

        //then
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void postponeBook_CanAUserPostponeBook_ShouldBeAble() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");

        //when
        boolean isPostponed = library.postponeBook("Ivan123", "978-0-9706726-8-1", 3);

        //then
        assertTrue(isPostponed);
    }

    @Test
    public void postponeBook_CanAReturnBePostponementWithWrongValues_ShouldThrowExceptionsForEveryIncorrectParameter() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");

        //when
        assertAll(
                () -> assertThrows(UserNotFoundException.class, () -> library.postponeBook("", "978-0-9706726-8-1", 3)),
                () -> assertThrows(UserNotFoundException.class, () -> library.postponeBook(null, "978-0-9706726-8-1", 3)),
                () -> assertThrows(InvalidISBNException.class, () -> library.postponeBook("Ivan123", "", 3)),
                () -> assertThrows(InvalidISBNException.class, () -> library.postponeBook("Ivan123", null, 3))
        );
    }

    @Test
    public void postponeBook_CanAUserPostponeMoreThanTwoWeeks_ShouldNotBeAble() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");

        //when
        boolean isPostponed = library.postponeBook("Ivan123", "978-0-9706726-8-1", 15);

        //then
        assertFalse(isPostponed);
    }

    @Test
    public void postponeBook_CanAUserPostponeMultipleTimes_ShouldBeAbleIfDoesNotExistTheMaximumDays() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBook("Ivan123", "978-0-9706726-8-1");
        library.postponeBook("Ivan123", "978-0-9706726-8-1", 5);

        //when
        boolean isPostponed = library.postponeBook("Ivan123", "978-0-9706726-8-1", 5);

        //then
        assertTrue(isPostponed);
    }

    @Test
    public void removeBorrowRequestAfter3Days_IsBorrowRequestRemovedAfterExpirationDate_ShouldBeRemoved() {
        //given
        int numberOfBorrowRequests = library.showBookRequests().size();
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.incrementCurrentDate(4);

        //when
        library.updateBorrowRequestRecords();

        //then
        assertEquals(numberOfBorrowRequests, library.showBookRequests().size());
    }

    @Test
    public void removeBorrowRequestAfter3DaysForMultipleUsers_IsUpdatedForOneUser_ShouldBeUpdatedForOne() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri"
                , "pesho97@abv.bg", 23, true);
        library.borrowBookRequest("Pesho97", "978-0-9706726-8-1");
        library.incrementCurrentDate(4);
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        int numberOfRequestsBeforeUpdate = library.showBookRequests().size();

        PaperBook paperBook = (PaperBook) library.getBookByISBN("978-0-9706726-8-1");
        boolean isTheBookAvailableBeforeTheUpdate = paperBook.isAvailable();

        //when
        library.updateBorrowRequestRecords();

        //then
        assertFalse(isTheBookAvailableBeforeTheUpdate);
        assertTrue(paperBook.isAvailable());
        assertEquals(2, numberOfRequestsBeforeUpdate);
        assertEquals(1, library.showBookRequests().size());
    }

    @Test
    public void create3DaysRequestForUserInTheQueue_IsUserAbleToBorrowTheBookAfterACopyBecomeAvailable_ShouldBeAble() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri", "pesho97@abv.bg", 23, true);
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("Pesho97", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        User pesho1 = library.getUserByUsername("pesho1");
        PaperBook paperBook = (PaperBook) library.getBookByISBN("978-0-9706726-8-1");
        int pesho1PositionBeforeUpdate = paperBook.getUserQueuePosition(pesho1);
        library.incrementCurrentDate(4);

        //when
        library.updateBorrowRequestRecords();

        //then
        assertEquals(1, pesho1PositionBeforeUpdate);
        assertEquals(1, library.showBookRequests().size());
        assertNotEquals(pesho1PositionBeforeUpdate, paperBook.getUserQueuePosition(pesho1));
    }

    @Test
    public void requestAgainForTheSameBook_CanTheUserRequestTheSameBookAgainAfterExpiredRequest_ShouldBeAble() {
        //given
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        int numberOfRequestsBeofreTheUpdate = library.showBookRequests().size();
        library.incrementCurrentDate(4);
        library.updateBorrowRequestRecords();

        //when
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");

        //then
        assertEquals(1, numberOfRequestsBeofreTheUpdate);
        assertEquals(1, library.showBookRequests().size());
    }

    @Test
    public void userBorrowBookAfterWaitingInQueue_CanTheUserBorrowTheBookAfterBeingInTheQueue_ShouldBeAble() {
        //given
        library.registerUser("Petar", "Petrov", "Pesho97", "pesho", MALE, "Sofia, bul. 6 Septemvri", "pesho97@abv.bg", 23, true);
        library.registerUser("Pesho", "Petrov", "pesho1", "pesho123", MALE, "Address", "pesho1@abv.bg", 29, true);
        library.borrowBookRequest("Pesho97", "978-0-9706726-8-1");
        library.borrowBookRequest("Ivan123", "978-0-9706726-8-1");
        library.borrowBookRequest("pesho1", "978-0-9706726-8-1");
        library.incrementCurrentDate(4);
        library.updateBorrowRequestRecords();
        int initialNumberOfBorrowedBooks = library.showBorrowedBooks().size();

        //when
        library.borrowBook("pesho1", "978-0-9706726-8-1");

        //then
        assertEquals(0, initialNumberOfBorrowedBooks);
        assertEquals(1, library.showBorrowedBooks().size());
    }
}
