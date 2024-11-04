import java.util.Arrays;

public class Library {

    private final int MAX_NUM_OF_BOOKS = 20;
    private final Book[] bookInventory = new Book[MAX_NUM_OF_BOOKS];
    // if < MAX_NUM_OF_BOOKS, defines at what position of bookInventory the next book will be added
    private int insertionIndex = 0;

    public Library() {}

    // if space is available, adds a book to library and returns true. returns false otherwise.
    public boolean addBook(Book book) throws IllegalArgumentException, NullPointerException {

        if (book == null) throw new NullPointerException("Argument 'book' can't be null");
        // to prevent books with invalid isbn to be added to library
        // check if ISBN of book was set (default value 0 if not).
        if (book.getISBN() == 0) throw new IllegalArgumentException("ISBN of book must be set");

        // check if there is space to store book
        if (insertionIndex < MAX_NUM_OF_BOOKS) {
            bookInventory[insertionIndex] = book;
            insertionIndex++;
            return true;
        }
        return false;
    }

    // returns the number of all books added to the library
    public int getNumberOfBooks() {
        return insertionIndex; // insertionIndex attribute equals number of stored books
    }

    // additional method
    // returns book with given isbn if it's stored in the library. returns null otherwise.
    public Book getBookByISBN(long isbn) throws IllegalArgumentException {
        Book.validateISBN(isbn);
        for (int i = 0; i < insertionIndex; i++) {
            if (bookInventory[i].getISBN() == isbn) return bookInventory[i];
        }
        return null;
    }

    // returns true if a book with given isbn is available in library. returns false otherwise.
    public boolean isAvailable(long isbn) throws IllegalArgumentException {
        Book.validateISBN(isbn);
        Book book = getBookByISBN(isbn);
        if (book == null) return false;
        return book.isAvailable();
    }

    // if book with given isbn is available, returns it and sets its
    // isAvailable attribute to false. returns null otherwise.
    public Book borrowBook(long isbn) throws IllegalArgumentException {
        Book.validateISBN(isbn);
        Book book = getBookByISBN(isbn);
        if (book == null) return null; // check if book is stored
        if (!book.isAvailable()) return null; // check if book can be borrowed
        book.setAvailable(false);
        return book;
    }

    // returns an array containing all books written by given author (and stored in library).
    // array is empty if no matches are found.
    public Book[] getBooksByAuthor(String author) throws NullPointerException, IllegalArgumentException {
        Book.validateString(author,"author");
        Book[] booksOfAuthor = new Book[MAX_NUM_OF_BOOKS]; // to store matching books
        int matches = 0; // counts number of books written by author
        for (int i = 0; i < insertionIndex; i++) {
            if (bookInventory[i].getAuthor().equals(author)) {
                booksOfAuthor[matches++] = bookInventory[i];
            }
        }
        // copy books to array with adequate length
        return Arrays.copyOfRange(booksOfAuthor, 0, matches);
    }

    // returns an array containing all books of given topic with relevance > 7.
    // if no matches found, array is empty.
    public Book[] getRelevantNonfiction(String topic) throws NullPointerException, IllegalArgumentException{
        Book.validateString(topic,"topic");
        Book[] matchingBooks = new Book[MAX_NUM_OF_BOOKS];
        int matches = 0; // counts number of matching books
        for (int i = 0; i < insertionIndex; i++) {
            Book book = bookInventory[i];
            if (book instanceof NonfictionBook
                    && ((NonfictionBook) book).getTopic().equals(topic)
                    && ((NonfictionBook) book).getRelevance() > 7) {
                matchingBooks[matches++] = book;
            }
        }
        // copy matching books to array with adequate length
        return Arrays.copyOfRange(matchingBooks, 0, matches);
    }

    // returns the total page number of all love Novels
    public int getTotalPagesOfLoveNovels() {
        int totalPages = 0;
        for (int i = 0; i < insertionIndex; i++) {
            if (bookInventory[i] instanceof Novel
                    && ((Novel) bookInventory[i]).getGenre().equals("Romance")) {
                totalPages += bookInventory[i].getPages();
            }
        }
        return totalPages;
    }

    // outputs available info for all books that have been added to the library
    public String getBibliography() {
        String bibliography = "Bibliography: " + "\n";
        for (int i = 0; i < insertionIndex; i++) {
            bibliography += bookInventory[i].toString() + "\n";
        }
        return bibliography;
    }
}
