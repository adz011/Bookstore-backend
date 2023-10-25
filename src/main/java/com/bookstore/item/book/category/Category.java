package com.bookstore.item.book.category;

import com.bookstore.item.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class Category {
    @Id
    private String category;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
