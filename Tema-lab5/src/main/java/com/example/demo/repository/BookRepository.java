package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    private final List<Book> bookList = new ArrayList<>();

    public List<Book> getAll()
    {
        return bookList;
    }

    private void createAndSave(String title, String iban, String author)
    {
        Book book = new Book(title,iban, author);
        bookList.add(book);
    }
    public void stergere(Book book){
        bookList.remove(book);
    }

    public void save(Book book){
        bookList.add(book);
    }

    public Optional<Book> getByIban(String iban){
        return bookList.stream().filter(book -> book.getIban().equals(iban)).findFirst();
    }

    private void setUpContextForBookRepository()
    {
        createAndSave("Poezii", "1231239021910", "Marin Sorescu");
        createAndSave("1984", "079847358347589", "George Orwell");
        createAndSave("Aleea cu licurici", "9873477474", "Kristin Hannah");
    }

    //must have this
    @PostConstruct
    public void afterPropertiesSetting() {
        setUpContextForBookRepository();
    }
}
