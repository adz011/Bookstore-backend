package com.bookstore.item.book;

import com.bookstore.item.ItemDTO;
import com.bookstore.item.ItemType;
import com.bookstore.item.book.author.Author;
import com.bookstore.item.book.category.Category;
import com.bookstore.item.book.imageLink.ImageLinks;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO extends ItemDTO {

    String title;
    String publisher;
    Set<Author> authors;
    String publishedDate;
    String description;
    int pageCount;
    Set<Category> categories;
    ImageLinks imageLinks;
    String language;

    public BookDTO(String itemID, ItemType itemType, String title, String publisher, Set<Author> authors,
                   String publishedDate, String description, int pageCount,
                   Set<Category> categories, String language, ImageLinks imageLinks) {

        super.setItemID(itemID);
        super.setItemType(itemType);
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.language = language;
        this.imageLinks = imageLinks;
    }
}
