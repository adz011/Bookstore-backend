package com.bookstore.item.book;

import com.bookstore.apis.GoogleBooksAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

@Service
public class BookService {
    private final GoogleBooksAPI googleBooksAPI = new GoogleBooksAPI();

    private final BookMapper bookMapper = new BookMapper();


    public void addNewBook(Book book) {
        System.out.println(book);
    }

    public Book getBookByISBN(String itemID) throws JsonProcessingException {
        String book = googleBooksAPI.getBookByISBN(itemID, "");
        // Create jsonObject of the received api data
        JsonObject jsonObject = JsonParser.parseString(book).getAsJsonObject();

        // Navigate to the "volumeInfo" object
        JsonObject volumeInfo = jsonObject.getAsJsonArray("items")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("volumeInfo");

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new Gson().toJson(volumeInfo), Book.class);
    }
}
