package com.bookstore.item;

import com.bookstore.item.book.*;
import com.bookstore.item.book.author.AuthorRepository;
import com.bookstore.item.book.category.Category;
import com.bookstore.item.book.category.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    private final BookMapper bookMapper = new BookMapper();


    private final ItemMapper itemMapper = new ItemMapper();

    public ItemDTO getItem(long id) throws ItemNotFoundException, BookNotFoundException, JsonProcessingException {
        Item item = itemRepository.findByID(id).orElseThrow(ItemNotFoundException::new);
        ItemDTO itemDTO = itemMapper.mapToItemDTO(item);
        if (item.getType() == ItemType.Book) {
            Book book = bookService.getBookByISBN(item.getItem_id());
            System.out.println(bookMapper.mapToDTO(itemDTO, book));
            itemDTO = bookMapper.mapToDTO(itemDTO, book);
        }

        return itemDTO;
    }


    public ItemDTO createItem(ItemDTO itemDTO) throws BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        Item item = itemRepository.save(itemMapper.mapToItem(itemDTO));
        return getItem(item.getID());
    }

    public void updateItem(Item updatedItem) {
        Optional<Item> outdatedItem = itemRepository.findByID(updatedItem.getID());
        outdatedItem.ifPresent(value -> itemRepository.save(updatedItem));

    }

    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> getItems(long quantity) {
        return itemRepository.findAll().stream().limit(quantity).collect(Collectors.toList());
    }

    public Set<String> getAllCurrentCategories() {
        List<Category> categoriesData = categoryRepository.findAll();
        Set<String> categories = new HashSet<>();
        for (Category category : categoriesData) {
            categories.add(category.getCategory());
        }
        return categories;
    }
}
