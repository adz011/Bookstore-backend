package com.bookstore.item.book.imageLink;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class ImageLinks {
    @Id
    @GeneratedValue
    private long Id;
    private String smallThumbnail;
    private String thumbnail;

}