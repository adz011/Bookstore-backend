package com.bookstore.item.book.imageLink;

import com.bookstore.item.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class ImageLinks {
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Id
    private String smallThumbnail;
    private String thumbnail;

}