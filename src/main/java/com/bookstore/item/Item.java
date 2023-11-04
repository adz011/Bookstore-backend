package com.bookstore.item;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue
    private long ID;
    private String itemID;
    @Enumerated(EnumType.STRING)
    private ItemType type;

    public Item(String itemID, ItemType type) {
        this.itemID = itemID;
        this.type = type;
    }
}
