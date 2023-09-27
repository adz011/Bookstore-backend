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
    List<String> authors;
    String publisher;

    public BookDTO(String itemID, ItemType itemType, String title, List<String> authors, String publisher) {
        super.setItemID(itemID);
        super.setItemType(itemType);
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
    }
}
