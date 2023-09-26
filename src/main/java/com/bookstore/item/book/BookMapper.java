package com.bookstore.item.book;

import com.bookstore.item.ItemDTO;

public class BookMapper {
    public BookDTO mapToDTO(ItemDTO itemDTO, Book book){
        return new BookDTO(
            itemDTO.getID(),
            itemDTO.getItemID(),
            itemDTO.getItemType(),
            book.getTitle(),
            book.getAuthors(),
            book.getPublisher()
        );
    }
}
