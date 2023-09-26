package com.bookstore.item;

import com.bookstore.item.book.BookDTO;
import com.bookstore.item.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BookService bookService;


    private final ItemMapper itemMapper = new ItemMapper();

    public ItemDTO getItem(long id) {
        Optional<Item> item = itemRepository.findByID(id);
        ItemDTO itemDTO = null;
        if (item.isPresent()) {
            itemDTO = itemMapper.mapToItemDTO(item.get());
            System.out.println(itemDTO);
            if (item.get().getType() == ItemType.Book) {
                itemDTO = bookService.getBookByISBN(item.get().getItemID());
                System.out.println(itemDTO);
            }
        }
        return itemDTO;
    }

    public void createTrip(ItemDTO itemDTO) {
        itemRepository.save(itemMapper.mapToItem(itemDTO));
    }
}
