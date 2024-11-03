public class NonfictionBook extends Book {

    private String topic;
    private int relevance;

    NonfictionBook() {
        // default value to prevent null pointers
        setTopic("not provided");
    }

    NonfictionBook(long isbn,
                   String title,
                   String author,
                   int pages,
                   String topic,
                   int relevance) throws IllegalArgumentException {

        super(isbn, title, author, pages);
        setTopic(topic);
        setRelevance(relevance);
    }

    // if topic wasn't set previously, sets it and returns true. returns false otherwise.
    boolean setTopic(String topic) throws IllegalArgumentException {
        if (!this.topic.equals("not provided")) return false; // no default value, so topic had been set already
        if ((topic == null) || (topic.isEmpty())) {
            throw new IllegalArgumentException("Valid topic must be provided");
        }
        this.topic = topic;
        return true;
    }

    // if relevance wasn't set previously, sets it and returns true. returns false otherwise.
    public boolean setRelevance(int relevance) throws IllegalArgumentException {
        if (this.relevance != 0) return false; // no java default value (0), so relevance had been set already
        if ((1 > relevance) || (relevance > 10)) {
            throw new IllegalArgumentException("Relevance must be between 1 and 10");
        }
        this.relevance = relevance;
        return true;
    }

    String getTopic() {
        return topic;
    }

    int getRelevance() {
        return relevance;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Topic: "
                + topic
                + ", Relevance: "
                + relevance
                + " (Category: Nonfiction)";
    }
}
