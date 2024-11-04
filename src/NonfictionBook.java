public class NonfictionBook extends Book {

    private String topic;
    private int relevance;

    public NonfictionBook() {
        // default value to prevent null pointers
        setTopic("not provided");
        // default value to be in valid range (1-10)
        setRelevance(1);
    }

    public NonfictionBook(long isbn,
                   String title,
                   String author,
                   int pages,
                   String topic,
                   int relevance) throws NullPointerException, IllegalArgumentException {

        super(isbn, title, author, pages);
        setTopic(topic);
        setRelevance(relevance);
    }

    public void setTopic(String topic) throws NullPointerException, IllegalArgumentException {
        Book.validateString(topic,"topic");
        this.topic = topic;
    }

    public void setRelevance(int relevance) throws IllegalArgumentException {
        if ((1 > relevance) || (relevance > 10)) {
            throw new IllegalArgumentException("Relevance must be between 1 and 10");
        }
        this.relevance = relevance;
    }

    public String getTopic() {
        return topic;
    }

    public int getRelevance() {
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
