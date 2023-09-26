package com.bookstore.item;

public class ItemMapper {

    public ItemDTO mapToItemDTO(Item item) {
        return new ItemDTO(
                item.getID(),
                item.getItemID(),
                item.getType()
        );
    }

    public Item mapToItem(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getID(),
                itemDTO.getItemID(),
                itemDTO.getItemType()
        );
    }
}

