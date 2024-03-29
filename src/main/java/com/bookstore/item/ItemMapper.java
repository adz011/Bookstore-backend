package com.bookstore.item;

public class ItemMapper {

    public ItemDTO mapToItemDTO(Item item) {
        return new ItemDTO(
                item.getItem_id(),
                item.getType()
        );
    }

    public Item mapToItem(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getItemID(),
                itemDTO.getItemType()
        );
    }
}

