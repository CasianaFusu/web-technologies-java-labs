package com.example.demo.mapper;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book convertBookFrom(BookDto bookDto){
        return new Book(bookDto.getBookTitle(),bookDto.getBookIban(), bookDto.getBookAuthor());
    }

    public BookDto convertBookFrom (Book book){
        return new BookDto(book.getTitle(), book.getIban(), book.getAuthor());
    }
}
