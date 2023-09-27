package com.bookstore.item;

import com.bookstore.item.book.Book;
import com.bookstore.item.book.BookMapper;
import com.bookstore.item.book.BookService;
import com.bookstore.item.book.BookNotFoundException;
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

    public ItemDTO getItem(long id) throws ItemNotFoundException, BookNotFoundException, JsonProcessingException {
        Item item = itemRepository.findByID(id).orElseThrow(ItemNotFoundException::new);
        ItemDTO itemDTO = itemMapper.mapToItemDTO(item);
        if (item.getType() == ItemType.Book) {
            Book book = bookService.getBookByISBN(item.getItemID());
            System.out.println(bookMapper.mapToDTO(itemDTO, book));
            itemDTO = bookMapper.mapToDTO(itemDTO, book);
        }

        return itemDTO;
    }


    public void createItem(Item item) {
        itemRepository.save(item);
    }

    public void updateItem(Item updatedItem) {
        Optional<Item> outdatedItem = itemRepository.findByID(updatedItem.getID());
        outdatedItem.ifPresent(value -> itemRepository.save(updatedItem));

    }

    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }
}
