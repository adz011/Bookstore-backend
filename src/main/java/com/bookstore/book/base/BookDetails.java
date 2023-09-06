package com.bookstore.book.base;

import com.bookstore.book.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //ignores all properties not defined in the class, while de-serialising
public class BookDetails {

    private long id;
    private String title;
    private String[] authors;
    private String[] publishers;
    private String publish_date;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    int numberOfPages;

    public BookDetails(String title, String[] authors, String[] publishers, String publishDate, Genre genre, int numberOfPages) {
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
        this.publish_date = publishDate;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
    }

    public BookDetails() {

    }
}
