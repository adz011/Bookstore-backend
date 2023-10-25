package com.bookstore.item.book.author;

import com.bookstore.item.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    private String author;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
