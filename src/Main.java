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

        System.out.println(la.getNumberOfBooks());
        System.out.println("---------------------");

        System.out.println(la.getBookByISBN(1111111111111L));
        System.out.println("---------------------");

        b.setAvailability(false);
        System.out.println(la.isAvailable(1111111111111L));
        System.out.println("---------------------");

        System.out.println(la.borrowBook(1111111111111L));
        System.out.println("---------------------");





        Book[] arr = la.getsBooksByAuthor("Leon");
        for (Book item: arr){
            System.out.println(item);
        }
        System.out.println("---------------------");

        System.out.println(la.getTotalPagesOfLoveNovels());
        System.out.println("---------------------");

        Book[] arr2 = la.getRelevantNonfiction("science");
        for (Book item: arr2){
            System.out.println(item);
        }
        System.out.println("---------------------");






    }
}