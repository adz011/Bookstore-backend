package com.bookstore.item.book;

import com.bookstore.item.ItemDTO;
import com.bookstore.item.ItemType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO extends ItemDTO {

    String title;
    String publisher;
    List<String> authors;
    String publishedDate;
    String description;
    int pageCount;
    String category;
    String thumbnail;
    String language;

    public BookDTO(String itemID, ItemType itemType, String title, String publisher, List<String> authors,
                   String publishedDate, String description, int pageCount,
                   String category, String language, ImageLinks imageLinks) {

        super.setItemID(itemID);
        super.setItemType(itemType);
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.category = category;
        this.language = language;
        thumbnail = imageLinks.getThumbnail();
    }
}
