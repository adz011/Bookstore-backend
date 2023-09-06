package com.bookstore.book;

import com.bookstore.book.base.BookDetails;
import com.bookstore.book.base.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookInfo> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public void registerNewBook(@RequestBody BookInfo bookInfo){
        bookService.addNewBook(bookInfo);
    }
}
