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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private String ISBN;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonDeserialize(using = AuthorListDeserializer.class)
    private Set<Author> authors;
    private String publisher;
    private String publishedDate;
    @Column(length = 4000)
    private String description;
    private int pageCount;
    private String printType;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonDeserialize(using = CategoryListDeserializer.class)
    private Set<Category> categories;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    @JsonDeserialize(using = ImageLinkDeserializer.class)
    @ManyToOne(cascade = CascadeType.ALL)
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;


}
