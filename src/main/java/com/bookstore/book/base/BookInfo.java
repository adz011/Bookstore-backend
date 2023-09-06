package com.bookstore.book.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //ignores all properties not defined in the class, while de-serialising

public class BookInfo {
    @Id
    @SequenceGenerator(
            name= "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    BookDetails details;
    String bib_key;
    String thumbnail_url;

    public BookInfo(BookDetails details, String bib_key, String thumbnail_url) {
        this.details = details;
        this.bib_key = bib_key;
        this.thumbnail_url = thumbnail_url;
    }

    public BookInfo() {
    }
}
