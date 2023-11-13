package com.bookstore.item.book.author;

import com.bookstore.item.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Author {
    @Id
    @GeneratedValue
    private long Id;
    private String author;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> book;
}


