public class Book {

    private long isbn;
    private String title;
    private String author;
    private int pages;
    private boolean isAvailable = true;

    public Book() {
        // default values to prevent null pointers
        setTitle("not provided");
        setAuthor("not provided");
    }

    public Book(long isbn, String title, String author, int pages)
            throws IllegalArgumentException {
        setISBN(isbn);
        setTitle(title);
        setAuthor(author);
        setPages(pages);
    }

    // static methods (so that they can easily be used in Library class as well)

    // checks if given String is null or empty. if yes, throws exception.
    public static void validateString(String s, String output) throws NullPointerException, IllegalArgumentException {
        if (s == null) {
            throw new NullPointerException(output + " can't be null");
        }
        if (s.isEmpty()) {
            throw new IllegalArgumentException(output + " can't be empty");
        }
    }

    // checks if given isbn has 13 digits. if yes, throws exception.
    public static void validateISBN(long isbn) throws IllegalArgumentException {
        String isbnAsString = Long.toString(isbn);
        if ((isbnAsString.length() != 13) || (isbn < 0)) {
            throw new IllegalArgumentException("ISBN must be a positive number with exactly 13 digits");
        }
    }

    // instance methods

    // defensive setters

    public void setISBN(long isbn) throws IllegalArgumentException {
        Book.validateISBN(isbn);
        this.isbn = isbn;
    }

    public void setTitle(String title) throws NullPointerException, IllegalArgumentException {
        Book.validateString(title, "title");
        this.title = title;
    }

    public void setAuthor(String author) throws NullPointerException, IllegalArgumentException {
        Book.validateString(author, "author");
        this.author = author;
    }

    public void setPages(int pages) throws IllegalArgumentException {
        if (pages < 1) {
            throw new IllegalArgumentException("Book must have at least one page");
        }
        this.pages = pages;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // getters

    public long getISBN() {
        return isbn;
    }

    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public String toString() {
        return "ISBN: "
                + isbn
                + ", Title: "
                + title
                + ", Author: "
                + author
                + ", Pages: "
                + pages
                + ", available: "
                + isAvailable;
    }
}
