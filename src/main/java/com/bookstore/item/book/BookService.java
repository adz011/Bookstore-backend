package com.bookstore.item.book;

import com.bookstore.apis.GoogleBooksAPI;
import com.bookstore.item.book.author.AuthorRepository;
import com.bookstore.item.book.category.CategoryRepository;
import com.bookstore.item.book.imageLink.ImageLinkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final GoogleBooksAPI googleBooksAPI = new GoogleBooksAPI();

    private final BookMapper bookMapper = new BookMapper();

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageLinkRepository imageLinkRepository;


    public void addNewBook(Book book) {
        System.out.println(book);
    }

    public Book getBookByISBN(String itemID) throws JsonProcessingException, BookNotFoundException {
        String bookJson = googleBooksAPI.getBookByISBN(itemID, "AIzaSyAocUxh0hB7t9bEPFwzrNizKbFcs4S8HSs");
        // Create jsonObject of the received api data
        JsonObject jsonObject = JsonParser.parseString(bookJson).getAsJsonObject();
        if(jsonObject.get("totalItems").getAsInt() <= 0){
            throw new BookNotFoundException();
        }
        // Navigate to the "volumeInfo" object
        JsonObject volumeInfo = jsonObject.getAsJsonArray("items")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("volumeInfo");

        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(new Gson().toJson(volumeInfo), Book.class);
        book.setISBN(itemID);
        return createBook(book);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }
}
