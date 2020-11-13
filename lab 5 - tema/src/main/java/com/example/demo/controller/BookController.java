package com.example.demo.controller;


import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    public String getAll(Model model) {
       //List<BookDto> bookDtos = BookService.getAllBook();
        List<BookDto> bookDtos = bookService.getAllBooks();
        model.addAttribute("bookDtos", bookDtos);
        return "view-books";
    }

    @GetMapping("/view-create")
    public String viewCreate(BookDto bookDto) {
        return "add-book";
    }

    @GetMapping("/{iban}")
    public String getBookByIban(@PathVariable("iban") String iban, Model model)
    {
        BookDto bookDto = bookService.getBookByIban(iban);
        model.addAttribute("bookDto", bookDto);
        return "view-book";
    }

    @PostMapping("/create")
    public String createShop(@Valid BookDto bookDto, BindingResult result, Model model)
    {
        if (result.hasErrors()){
            return "add-book";
        }

        bookService.createBook(bookDto);
        model.addAttribute("bookDtos", bookService.getAllBooks());
        return "view-books";
    }

    @GetMapping("/edit-book/{iban}")
    public String editBook(@PathVariable("iban") String iban, BookDto bookDto, Model model)
    {
        BookDto findBookDto = bookService.getBookByIban(iban);
        model.addAttribute("bookDto", findBookDto);
        return "edit-book";
    }

    @GetMapping("/delete-book/{iban}")
    public String stergereCarte(@PathVariable("iban") String iban, BookDto bookDto, Model model){
        bookService.stergere(iban);

        List <BookDto> bookDtos = bookService.getAllBooks();
        model.addAttribute("bookDtos", bookDtos);

        return "view-books";
    }

    @PostMapping("/modificare/{iban}")
    public String modificareCarte(@PathVariable String iban, @Valid BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-book";
        }

        bookService.updateBook(bookDto);
        model.addAttribute("bookDto", bookService.getBookByIban(iban));
        return "view-book";
    }
}
