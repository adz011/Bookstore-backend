package com.bookstore.item.book;

import com.bookstore.item.book.author.Author;
import com.bookstore.item.book.author.AuthorListDeserializer;
import com.bookstore.item.book.category.Category;
import com.bookstore.item.book.category.CategoryListDeserializer;
import com.bookstore.item.book.imageLink.ImageLinks;
import com.bookstore.item.book.imageLink.ImageLinkDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private String ISBN;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonDeserialize(using = AuthorListDeserializer.class)
    private List<Author> authors;
    private String publisher;
    private String publishedDate;
    @Column(length = 4000)
    private String description;
    private int pageCount;
    private String printType;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonDeserialize(using = CategoryListDeserializer.class)
    private List<Category> categories;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    @JsonDeserialize(using = ImageLinkDeserializer.class)
    @OneToOne(cascade = CascadeType.ALL)
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;


}
