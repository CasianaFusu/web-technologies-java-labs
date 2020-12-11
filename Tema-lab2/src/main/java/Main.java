import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import beans.Book;
import beans.Author;
import beans.Owner;
import beans.Price;


public class Main {
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        //Book book_test = context.getBean("book1", Book.class); //by type

        // Book book = context.getBean("book2", Book.class);

        //System.out.println(book + " " + book_test);

        Book b = context.getBean("book",Book.class);
        Book b1 = context.getBean("book_fiction", Book.class);
       // Author a = context.getBean(Author.class);
       // Owner o = context.getBean(Owner.class);

       // Price p = context.getBean(Price.class);

        System.out.println(b);
        System.out.println(b.getAuthor());
        System.out.println(b.getPrice());

        System.out.println(b1);
        System.out.println(b1.getAuthor());
        System.out.println(b1.getPrice());
    }
}
