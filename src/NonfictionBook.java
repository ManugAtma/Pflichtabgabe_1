public class NonfictionBook extends Book {

    private String topic;
    private int relevance;

    NonfictionBook() {
        // default value to prevent null pointers
        setTopic("not provided");
        // default value to be in valid range (1-10)
        setRelevance(1);
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

    void setTopic(String topic) throws IllegalArgumentException {
        Book.validateString(topic,"topic");
        this.topic = topic;
    }

    public void setRelevance(int relevance) throws IllegalArgumentException {
        if ((1 > relevance) || (relevance > 10)) {
            throw new IllegalArgumentException("Relevance must be between 1 and 10");
        }
        this.relevance = relevance;
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
