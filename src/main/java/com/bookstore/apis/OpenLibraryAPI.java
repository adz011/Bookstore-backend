package com.bookstore.apis;

import com.bookstore.book.Book;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class OpenLibraryAPI {
    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl
            = "https://openlibrary.org/api/books?bibkeys=ISBN:";

    String format = "&format=json&jscmd=details";

    HttpEntity<Book> request = new HttpEntity<>(new Book());


    public Book getRecord(String ISBN){
         return restTemplate.postForObject(fooResourceUrl + ISBN + format, request,  Book.class);
    }
}
