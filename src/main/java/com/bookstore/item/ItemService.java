package com.bookstore.item;

import com.bookstore.item.book.Book;
import com.bookstore.item.book.BookDTO;
import com.bookstore.item.book.BookMapper;
import com.bookstore.item.book.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BookService bookService;

    private final BookMapper bookMapper = new BookMapper();


    private final ItemMapper itemMapper = new ItemMapper();

    public ItemDTO getItem(long id) {
        Optional<Item> item = itemRepository.findByID(id);
        ItemDTO itemDTO = null;
        if (item.isPresent()) {
            itemDTO = itemMapper.mapToItemDTO(item.get());
            System.out.println(itemDTO);
            if (item.get().getType() == ItemType.Book) {
                try {
                    Book book = bookService.getBookByISBN(item.get().getItemID());
                    System.out.println(bookMapper.mapToDTO(itemDTO, book));
                    itemDTO = bookMapper.mapToDTO(itemDTO, book);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return itemDTO;
    }

    public void createItem(ItemDTO itemDTO) {
        itemRepository.save(itemMapper.mapToItem(itemDTO));
    }
}
