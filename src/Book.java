public class Book {

    private long isbn;
    private String title;
    private String author;
    private int pages;
    private boolean isAvailable = true;

    // TODO: check if isbn already exists?, static attr list?
    // TODO: parameterless constructor throws exception?

    Book() {
        // default values to prevent null pointers
        setTitle("not provided");
        setAuthor("not provided");
    }

    Book(long isbn, String title, String author, int pages)
            throws IllegalArgumentException {
        setISBN(isbn);
        setTitle(title);
        setAuthor(author);
        setPages(pages);
    }

    // static methods

    // checks if given String is null or empty. if yes, throws exception.
    public static void validateString(String s, String output) throws IllegalArgumentException{
        if ((s == null) || (s.isEmpty())) {
            throw new IllegalArgumentException("Must provide valid " + output);
        }
    }

    // checks if given isbn has 13 digits. if yes, throws exception.
    public static void validateISBN(long isbn) throws IllegalArgumentException {
        String isbnAsString = Long.toString(isbn);
        if (isbnAsString.length() != 13) {
            throw new IllegalArgumentException("ISBN length must be exactly 13 digits");
        }
    }

    // instance methods

    // defensive setters

    public void setISBN(long isbn) throws IllegalArgumentException {
        Book.validateISBN(isbn);
        this.isbn = isbn;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        Book.validateString(title,"title");
        this.title = title;
    }

    public void setAuthor(String author) throws IllegalArgumentException {
        Book.validateString(author, "author");
        this.author = author;
    }

    public void setPages(int pages) throws IllegalArgumentException {
        if (pages < 1) {
            throw new IllegalArgumentException("Book must have at least one page");
        }
        this.pages = pages;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // getters

    public long getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    int getPages() {
        return pages;
    }

    String getAuthor() {
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
