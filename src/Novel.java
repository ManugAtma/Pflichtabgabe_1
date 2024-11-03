public class Novel extends Book {

    String genre;

    Novel() {
        // default value to prevent null pointers
        setGenre("not provided");
    }

    Novel(long isbn, String title, String author, int pages, String genre)
            throws IllegalArgumentException {
        super(isbn, title, author, pages);
        setGenre(genre);
    }

    void setGenre(String genre) throws IllegalArgumentException {
        Book.validateString(genre,"genre");
        this.genre = genre;
    }

    String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Genre: "
                + genre
                +  " (Category: Novel)";
    }
}
