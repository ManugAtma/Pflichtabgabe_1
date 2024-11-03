public class Book {

    private long isbn;
    private String title;
    private String author;
    private int pages;
    private boolean isAvailable = true;

    // TODO: check if isbn already exists?, static attr list?

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

    // if an attribute has been set already, it shouldn't be changed again.
    // these setters ensure that by checking for default values (java default value for numbers: 0,
    // custom default value for Strings: "not provided").

    // if isbn wasn't set previously, sets it and returns true. returns false otherwise.
    public boolean setISBN(long isbn) throws IllegalArgumentException {
        if (this.isbn != 0) return false; // no default value (0), so isbn had been set already
        String isbnAsString = Long.toString(isbn);
        if (isbnAsString.length() != 13) {
            throw new IllegalArgumentException("ISBN length must be exactly 13 digits");
        }
        if (isbnAsString.charAt(0) == 0) {
            throw new IllegalArgumentException("ISBN can't be an octal or hex number");
        }
        this.isbn = isbn;
        return true;
    }

    // if title wasn't set previously, sets it and returns true. returns false otherwise.
    public boolean setTitle(String title) throws IllegalArgumentException {
        if (!this.title.equals("not provided")) return false; // no default value, so title had been set already
        if ((title == null) || (title.isEmpty())) {
            throw new IllegalArgumentException("Must provide valid title");
        }
        this.title = title;
        return true;
    }

    // if author wasn't set previously, sets it and returns true. returns false otherwise.
    public boolean setAuthor(String author) throws IllegalArgumentException {
        if (!this.author.equals("not provided")) return false; // no default value, so author had been set already
        if ((author == null) || (author.isEmpty())) {
            throw new IllegalArgumentException("Must provide valid author");
        }
        this.author = author;
        return true;
    }

    // if number of pages wasn't set previously, sets it and returns true. returns false otherwise.
    public boolean setPages(int pages) throws IllegalArgumentException {
        if (this.pages != 0) return false; // no default value (0), so pages had been set already
        if (pages < 1) {
            throw new IllegalArgumentException("Book must have at least one page");
        }
        this.pages = pages;
        return true;
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
