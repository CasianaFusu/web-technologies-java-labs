package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookMapper bookMapper, BookRepository bookRepository)
    {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.getAll().stream().map(bookMapper::convertBookFrom).collect(Collectors.toList());
    }

    public void createBook(BookDto bookDto) {
        Book book = bookMapper.convertBookFrom(bookDto);
        bookRepository.save(book);
    }

    public BookDto getBookByIban(String iban){
        var book = bookRepository.getByIban(iban);
        return bookMapper.convertBookFrom(book.get());
    }

    public void updateBook(BookDto bookDto){
        Book bookOld = bookRepository.getByIban(bookDto.getBookIban()).get();
        BookDto bookDtoOld = bookMapper.convertBookFrom(bookOld);

        bookOld.setTitle(bookDto.getBookTitle());
        bookOld.setIban(bookDto.getBookIban());
        bookOld.setAuthor(bookDto.getBookAuthor());
    }

    public void stergere(String iban){
        Book book = bookRepository.getByIban(iban).get();
        bookRepository.stergere(book);
    }
}
