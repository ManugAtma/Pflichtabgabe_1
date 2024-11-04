public class Main {
    public static void main(String[] args) {

        Book b = new Book(1111111111111L, "things", "Manug", 40);
        NonfictionBook nb = new NonfictionBook(2222222222222L, "things",
                "Leon", 55, "science", 10);
        Novel nv = new Novel(3333333333333L, "things",
                "Manug", 90, "Romance");
        Novel nv2 = new Novel(4444444444444L, "things",
                "Anusch", 80, "Romance");

        Book book = new Book();
        book.setISBN(5555555555555L);
        // System.out.println(book);

        NonfictionBook nonf = new NonfictionBook();
        nonf.setISBN(7777777777788L);
        nonf.setRelevance(3);
        nonf.setTopic("law");
        // System.out.println(nonf);

        Novel nov = new Novel();
        nov.setISBN(7777777777777L);
        nov.setGenre("Romance");
        // System.out.println(nov);


        Library la = new Library();
        System.out.println(la.addBook(b));
        System.out.println(la.addBook(nb));
        System.out.println(la.addBook(nv));
        System.out.println(la.addBook(nv2));
        System.out.println(la.addBook(book));
        System.out.println(la.addBook(nov));
        System.out.println(la.addBook(nonf));
        System.out.println("---------------------");

        System.out.println(la.getBibliography());
        System.out.println("---------------------");

        System.out.println("getNumberOfBooks: " + la.getNumberOfBooks());
        System.out.println("---------------------");

        System.out.println("getBookByISBN (book): " + la.getBookByISBN(1111111111111L));
        System.out.println("---------------------");

        System.out.println("getBookByISBN (null): " + la.getBookByISBN(2221111111111L));
        System.out.println("---------------------");

        b.setAvailable(false);
        System.out.println("isAvailable (false): "+ la.isAvailable(1111111111111L));
        System.out.println("---------------------");

        b.setAvailable(true);
        System.out.println("isAvailable (true): " + la.isAvailable(1111111111111L));
        System.out.println("---------------------");

        System.out.println("borrowBook (book): " + la.borrowBook(1111111111111L));
        System.out.println("---------------------");


        System.out.println("borrowBook (null): " + la.borrowBook(1111111111111L));
        System.out.println("---------------------");






        System.out.println("getBooksByAuthor(\"Manug\") (2)");
        Book[] arr = la.getBooksByAuthor("Manug");
        for (Book item: arr){
            System.out.println(item);
        }
        System.out.println("---------------------");

        System.out.println("getBooksByAuthor(\"Raga\") (0)");
        Book[] arr2 = la.getBooksByAuthor("Raga");
        for (Book item: arr2){
            System.out.println(item);
        }
        System.out.println("---------------------");

        System.out.println("getTotalPagesOfLoveNovels (170): " + la.getTotalPagesOfLoveNovels());
        System.out.println("---------------------");

        System.out.println("getRelevantNonfiction (1): ");
        Book[] arr3 = la.getRelevantNonfiction("science");
        for (Book item: arr3){
            System.out.println(item);
        }
        System.out.println("---------------------");







    }
}