package com.bookstore.item.book;

import com.bookstore.item.ItemDTO;

public class BookMapper {
    public BookDTO mapToDTO(ItemDTO itemDTO, Book book){
        return new BookDTO(
                itemDTO.getItemID(),
                itemDTO.getItemType(),
                book.getTitle(),
                book.getPublisher(),
                book.getAuthors(),
                book.getPublishedDate(),
                book.getDescription(),
                book.getPageCount(),
                book.getCategories(),
                book.getLanguage(),
                book.getImageLinks()
        );
    }
}
