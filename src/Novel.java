public class Novel extends Book {

    private String genre;

    public Novel() {
        // default value to prevent null pointers
        setGenre("not provided");
    }

    public Novel(long isbn, String title, String author, int pages, String genre)
            throws NullPointerException, IllegalArgumentException {
        super(isbn, title, author, pages);
        setGenre(genre);
    }

    public void setGenre(String genre) throws NullPointerException, IllegalArgumentException {
        Book.validateString(genre,"genre");
        this.genre = genre;
    }

    public String getGenre() {
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
