package com.bookstore.item.book.category;

import com.bookstore.item.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Category {
    @Id
    @GeneratedValue
    private long Id;
    @ManyToMany(mappedBy = "categories")
    private Set<Book> book;
    private String category;

}
