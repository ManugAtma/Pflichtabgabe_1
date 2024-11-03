import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    Library lib;


    @BeforeEach
    public void setUp() {
        lib = new Library();
    }

    // tests for borrowBook (BB)

    // Normalfall 1: book stored in middle of inventory and available
    @Test
    public void testBBStoredAndAvailable() {
        Book b = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        NonfictionBook nfb = new NonfictionBook(2222222222222L, "java", "Alexander", 351, "IT", 10);
        Novel n = new Novel(3333333333333L, "pride and prejudice", "Austen", 276, "Romance");
        lib.addBook(b);
        lib.addBook(nfb);
        lib.addBook(n);
        assertEquals(nfb, lib.borrowBook(2222222222222L));
    }

    // Normalfall 2: book stored in middle of inventory but not available
    @Test
    public void testBBStored() {
        Book b = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        NonfictionBook nfb = new NonfictionBook(2222222222222L, "java", "Alexander", 351, "IT", 10);
        Novel n = new Novel(3333333333333L, "pride and prejudice", "Austen", 276, "Romance");
        lib.addBook(b);
        lib.addBook(nfb);
        lib.addBook(n);
        nfb.setAvailability(false);
        assertNull(lib.borrowBook(2222222222222L));
    }

    // Normalfall 3: Szenario: method called two times in a row
    @Test
    public void testBBTwoTimes() {
        Book b = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        NonfictionBook nfb = new NonfictionBook(2222222222222L, "java", "Alexander", 351, "IT", 10);
        Novel n = new Novel(3333333333333L, "pride and prejudice", "Austen", 276, "Romance");
        lib.addBook(b);
        lib.addBook(nfb);
        lib.addBook(n);
        lib.borrowBook(2222222222222L);
        assertNull(lib.borrowBook(2222222222222L));
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

    // Fehlerfall: illegal isbn, only 4 digits
    @Test
    public void testBBIllegalISBN() {
        Book b = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        lib.addBook(b);
        assertThrows(IllegalArgumentException.class, () -> {
            lib.borrowBook(1111);
        });
    }

// TODO: Grenzfälle?

    // tests for getRelevantNonfiction (GRN)

    // Normalfall 1: some Nonfiction books with both matching genre and relevance,
    // some books that are not of type Nonfiction
    @Test
    public void testGRNAllNonfictionMatch() {
        Book b = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        NonfictionBook nfb1 = new NonfictionBook(2222222222222L, "java", "Alexander", 351, "IT", 10);
        NonfictionBook nfb2 = new NonfictionBook(4444444444444L, "SQL", "Gaertner", 439, "IT", 9);
        Novel n = new Novel(3333333333333L, "pride and prejudice", "Austen", 276, "Romance");
        lib.addBook(b);
        lib.addBook(nfb1);
        lib.addBook(nfb2);
        lib.addBook(n);
        assertEquals(nfb1, lib.getRelevantNonfiction("IT")[0]);
        assertEquals(nfb2, lib.getRelevantNonfiction("IT")[1]);
    }

    // Normalfall 2: some Nonfiction books with matching attributes, some Nonfiction books
    // with attributes that don't match, some books that are not of type Nonfiction
    @Test
    public void testGRNSomeNonfictionMatch() {
        Book b = new Book(1111111111111L, "war and peace", "Tolstoi", 1300);
        NonfictionBook nfb1 = new NonfictionBook(2222222222222L, "java", "Alexander", 351, "IT", 10);
        NonfictionBook nfb2 = new NonfictionBook(4444444444444L, "SQL", "Gaertner", 439, "IT", 9);
        Novel n = new Novel(3333333333333L, "pride and prejudice", "Austen", 276, "Romance");
        lib.addBook(b);
        lib.addBook(nfb1);
        lib.addBook(nfb2);
        lib.addBook(n);
        assertEquals(nfb1, lib.getRelevantNonfiction("IT")[0]);
        assertEquals(nfb2, lib.getRelevantNonfiction("IT")[1]);
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
            NonfictionBook nfb2 = new NonfictionBook(x + 20 + i, "java", "Alexander", 351, "IT", 6);
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
        assertThrows(IllegalArgumentException.class, () -> {
            lib.getRelevantNonfiction(null);
        });
    }

    // TODO: Grenzfälle?
    // TODO: in any case: test rel == 7 and rel == 8, Grenzfälle?

}


// -- borrowBook
// happy path: in bib and ava (in middle)
// happy path: book in middle, but not ava
// happy path: mehrmals: erst is ava, dann nicht ava
// best case: empty bib
// close to best: boook at beginning
// worst case: not in bib, bib is full
// fehlerfall: wrong isbn
// in lib but not available


// -- getRN
// happypath1: some Novels, all matchT and rel > 7, z.B.9
// happypath2: some Novels, some both matchT and rel > 7
// happypath3: some Novels, some only matchT and some only rel > 7
// happypath4: no novels
// fehlerfall 1: empty as arg
// fehlerfall 2: null String as arg
// best case: empty bib
// worst case: all Novels, all Romance, but none rel > 7, full bib
// Grenzfall 1: ava and rel == 8? als normalfall?
// Grenzfall 2: ava and rel == 7? als normalfall?

