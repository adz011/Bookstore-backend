package com.bookstore.item.book;

import com.bookstore.item.ItemDTO;
import com.bookstore.item.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BookDTO extends ItemDTO {

    String title;

    public BookDTO(long ID, String itemID, ItemType itemType) {
        super(ID, itemID, itemType);
    }
}
