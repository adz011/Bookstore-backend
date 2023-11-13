package com.bookstore.repository;

import com.bookstore.item.book.Book;
import com.bookstore.item.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void BookRepository_SaveAll_ReturnSavedBook() {
        Book book = Book.builder()
                .title("Title")
                .ISBN("9780470383278")
                .build();

        Book savedBook = bookRepository.save(book);

        Assertions.assertNotNull(savedBook);
        Assertions.assertTrue(savedBook.getId()>=0);
    }


    @Test
    public void BookRepository_FindAll_ReturnsMoreThanOneBook(){
        Book book1 = Book.builder()
                .title("Title")
                .ISBN("9780470383278")
                .build();

        Book book2 = Book.builder()
                .title("Title")
                .ISBN("9780201715941")
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> bookList = bookRepository.findAll();

        Assertions.assertNotNull(bookList);
        Assertions.assertEquals(bookList.size(), 2);
    }


    @Test
    public void BookRepository_FindById_ReturnsNotNull(){
        Book book = Book.builder()
                .title("Title")
                .ISBN("9780470383278")
                .build();

        bookRepository.save(book);

        Book retrievedBook = bookRepository.findById(book.getId()).get();

        Assertions.assertNotNull(retrievedBook);
    }


    @Test
    public void BookRepository_DeleteBook_ReturnsEmpty(){
        Book book = Book.builder()
                .title("Title")
                .ISBN("9780470383278")
                .build();

        bookRepository.save(book);

        bookRepository.deleteById(book.getId());
        Optional<Book> retrievedBook = bookRepository.findById(book.getId());

        Assertions.assertTrue(retrievedBook.isEmpty());
    }
}