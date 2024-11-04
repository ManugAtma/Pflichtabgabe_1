import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    Library lib;
    Book book;
    NonfictionBook nFBook;
    Novel novel;

    @BeforeEach
    public void setUp() {
        lib = new Library();
    }

    // borrowBook (BB)

    // additional method to avoid redundant code. adds 3 books to library
    public void addBooksToLibrary (){
        book = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        nFBook = new NonfictionBook(2222222222222L, "java", "Alexander", 351, "IT", 10);
        novel = new Novel(3333333333333L, "pride and prejudice", "Austen", 276, "Romance");
        lib.addBook(book);
        lib.addBook(nFBook);
        lib.addBook(novel);
    }

    // tests for borrowBook (BB)

    // Normalfall 1: book stored in middle of inventory and available
    @Test
    public void testBBStoredAndAvailable() {
        addBooksToLibrary();
        assertEquals(nFBook, lib.borrowBook(2222222222222L));
        assertFalse(nFBook.isAvailable());
    }

    // Normalfall 2: book stored in middle of inventory but not available
    @Test
    public void testBBStored() {
        addBooksToLibrary();
        nFBook.setAvailable(false);
        assertFalse(nFBook.isAvailable());
        assertNull(lib.borrowBook(2222222222222L));
    }

    // Normalfall 3: scenario: method called two times in a row
    @Test
    public void testBBTwoTimes() {
        addBooksToLibrary();
        lib.borrowBook(2222222222222L);
        assertNull(lib.borrowBook(2222222222222L));
        assertFalse(nFBook.isAvailable());
    }

    // best case: empty library
    @Test
    public void testBBEmptyLibrary() {
        assertNull(lib.borrowBook(2222222222222L));
    }

    // worst case: book not stored, full library
    @Test
    public void testBBNotStoredFullLibrary() {
        for (int i = 0; i < 20; i++) {
            long x = 1111111111111L;
            Book b = new Book(x + i, "war and peace", "Tolstoi", 1300);
            lib.addBook(b);
        }
        assertNull(lib.borrowBook(2222222222222L));
    }

    // Fehlerfall 1: illegal isbn: only 4 digits
    @Test
    public void testBBIllegalISBNLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            lib.borrowBook(1111);
        });
    }

    // Fehlerfall 2: illegal isbn: 13 symbols but one of them is "-"
    @Test
    public void testBBNegativeISBN() {
        assertThrows(IllegalArgumentException.class, () -> {
            lib.borrowBook(-111111111111L);
        });
    }

    // Grenzfall: illegal isbn: only 12 digits
    @Test
    public void testBBLengthOfISBNAlmostRight(){
        assertThrows(IllegalArgumentException.class, () -> {
            lib.borrowBook(111111111111L);
        });
    }


    // tests for getRelevantNonfiction (GRN)


    // Normalfall 1: some Nonfiction books with matching genre and matching relevance,
    // some books that are not of type Nonfiction
    @Test
    public void testGRNAllNonfictionMatch() {
        addBooksToLibrary();
        NonfictionBook nFBook2 = new NonfictionBook(4444444444444L, "SQL", "Gaertner", 439, "IT", 9);
        lib.addBook(nFBook2);
        assertEquals(nFBook, lib.getRelevantNonfiction("IT")[0]);
        assertEquals(nFBook2, lib.getRelevantNonfiction("IT")[1]);
    }

    // Normalfall 2: one Nonfiction book with matching attributes, two Nonfiction books
    // with only one matching attribute, some books that are not of type Nonfiction
    @Test
    public void testGRNSomeNonfictionMatch() {
        addBooksToLibrary();
        NonfictionBook nFBook2 = new NonfictionBook(4444444444444L, "yoga", "Smith", 439, "sports", 9);
        lib.addBook(nFBook2);
        NonfictionBook nFBook3 = new NonfictionBook(5555555555555L, "CSS is annoying", "William", 239, "IT", 2);
        lib.addBook(nFBook3);
        Book[] result = lib.getRelevantNonfiction("IT");
        assertEquals(nFBook, lib.getRelevantNonfiction("IT")[0]);
        assertEquals(1, result.length);
    }

    // best case: empty library
    @Test
    public void testGRNEmptyLibrary() {
        Book[] result = lib.getRelevantNonfiction("sport");
        assertEquals(0, result.length);
    }

    // worst case: only Nonfiction books, all match topic, but none matches relevance, full library
    @Test
    public void testGRNNoMatchesFullLibrary() {
        for (int i = 0; i < 10; i++) {
            long x = 1111111111111L;
            NonfictionBook nfb1 = new NonfictionBook(x + i, "java", "Alexander", 351, "IT", 3);
            NonfictionBook nfb2 = new NonfictionBook(x + 20 + i, "java 2", "Alexander", 377, "IT", 6);
            lib.addBook(nfb1);
            lib.addBook(nfb2);
        }
        Book[] result = lib.getRelevantNonfiction("IT");
        assertEquals(0, result.length);
    }

    // Fehlerfall 1: empty string as argument
    @Test
    public void testGRNEmptyArg() {
        assertThrows(IllegalArgumentException.class, () -> {
            lib.getRelevantNonfiction("");
        });
    }

    // Fehlerfall 2: null as argument
    @Test
    public void testGRNNullArg() {
        assertThrows(NullPointerException.class, () -> {
            lib.getRelevantNonfiction(null);
        });
    }

    // Grenzfall: Nonfiction book with matching topic and matching relevance of 8
    @Test
    public void testGRNRelevanceOf8() {
        NonfictionBook nFBook2 = new NonfictionBook(4444444444444L, "yoga", "Smith", 439, "sports", 8);
        lib.addBook(nFBook2);
        assertEquals(nFBook2, lib.getRelevantNonfiction("sports")[0]);
    }

    // "Gegencheck" für Grenzfall: Nonfiction book with matching topic and non-matching relevance of 7
    @Test
    public void testGRNRelevanceOf7() {
        NonfictionBook nFBook2 = new NonfictionBook(4444444444444L, "yoga", "Smith", 439, "sports", 7);
        lib.addBook(nFBook2);
        Book[] result = lib.getRelevantNonfiction("IT");
        assertEquals(0, result.length);
    }
}

