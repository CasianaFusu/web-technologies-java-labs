import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import beans.Book;
import beans.Author;
import beans.Owner;
import org.springframework.context.annotation.Primary;
import beans.Price;


@Configuration
public class ProjectConfig {

    @Bean
    Book book()
    {
        Book b = new Book("Solenoid");
        b.setAuthor(author()); //bean-ul de mai jos
        b.setPrice(price()); //wiring
        return b;
    }

    @Bean
    Book book_fiction(Price price2){ //dependency injection
        Book b = new Book("Prietena mea geniala");
        b.setAuthor(author2());
        b.setPrice(price2);
        return b;
    }

    @Bean
    Author author2(){
        return new Author("Elena Ferrante");
    }

    @Bean
    Price price2(){
        return new Price("34 lei");
    }

    @Bean
    Author author() {
        return new Author("Mircea Cartarescu");
    }

    @Bean
    Owner owner()
    {
        return new Owner();
    }

    @Bean
    Price price()
    {
        return new Price("23 lei");
    }
   // @Bean
   // @Primary
   // Author author_test ()
   // {
   //     return new Author();
   // }
}
