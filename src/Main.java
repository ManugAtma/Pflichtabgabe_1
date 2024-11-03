public class Main {
    public static void main(String[] args) {

        Book b = new Book(1111111111111L, "things", "Manug", 55);
        NonfictionBook nb = new NonfictionBook(2222222222222L, "things",
                "Leon", 55, "science", 10);
        Novel nv = new Novel(3333333333333L, "things",
                "Manug", 80, "Romance");
        Novel nv2 = new Novel(4444444444444L, "things",
                "Anusch", 80, "Romance");

        Book book = new Book();
        System.out.println(book);

        Novel nov = new Novel();
        System.out.println(nov);

        NonfictionBook nonf = new NonfictionBook();
        System.out.println(nonf);

       /* Library la = new Library();
        System.out.println(la.addBook(b));
        System.out.println(la.addBook(nb));
        System.out.println(la.addBook(nv));
        System.out.println(la.addBook(nv2));
        System.out.println("---------------------");*/

       /* System.out.println(la.getBibliography());
        System.out.println("---------------------");

        Book[] arr = la.getsBooksByAuthor("Manug");
        for (Book book: arr){
            System.out.println(book);
        }
        System.out.println("---------------------");

        System.out.println(la.getTotalPagesOfLoveNovels());
        System.out.println("---------------------");

        Book[] arr2 = la.getRelevantNonfiction("science");
        for (Book book: arr2){
            System.out.println(book);
        }
        System.out.println("---------------------");
*/







    }
}