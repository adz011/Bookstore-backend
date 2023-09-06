package com.bookstore.book;

import com.bookstore.book.base.BookDetails;
import com.bookstore.book.base.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public List<BookInfo> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(BookInfo bookInfo) {
        System.out.println(bookInfo);
    }
}
