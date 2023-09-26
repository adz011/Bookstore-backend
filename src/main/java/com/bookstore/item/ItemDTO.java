package com.bookstore.item;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private long ID;
    private String itemID;
    private ItemType itemType;

}
