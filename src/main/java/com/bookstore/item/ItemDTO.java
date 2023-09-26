package com.bookstore.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ItemDTO {
    private long ID;
    private String itemID;
    private ItemType itemType;

}
