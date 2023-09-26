package com.bookstore.item.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@ToString
public class Book {
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
    private long id;
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    int numberOfPages;

    public Book(String ISBN, String title, String author, String publisher, LocalDate publicationDate, Genre genre, int numberOfPages) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
    }

    public Book() {

    }
}
