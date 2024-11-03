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

    // if genre wasn't set previously, sets it and returns true. returns false otherwise.
    boolean setGenre(String genre) throws IllegalArgumentException {
        if (!this.genre.equals("not provided")) return false; // no default value, so genre had been set already
        if ((genre == null) || (genre.isEmpty())) {
            throw new IllegalArgumentException("Valid genre must be provided");
        }
        this.genre = genre;
        return true;
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
