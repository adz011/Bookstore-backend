package com.bookstore.repository;

import com.bookstore.item.Item;
import com.bookstore.item.ItemRepository;
import com.bookstore.item.ItemType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ItemRepositoryTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void ItemRepository_SaveAll_ReturnSavedItem() {
        Item item = Item.builder()
                .itemID("9780470383278")
                .type(ItemType.Book)
                .build();

        Item savedItem = itemRepository.save(item);

        Assertions.assertNotNull(savedItem);
        Assertions.assertTrue(savedItem.getID()>=0);
    }


    @Test
    public void ItemRepository_FindAll_ReturnsMoreThanOneItem(){
        Item item1 = Item.builder()
                .itemID("9780470383278")
                .type(ItemType.Book)
                .build();

        Item item2 = Item.builder()
                .itemID("9780201715941")
                .type(ItemType.Book)
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> itemList = itemRepository.findAll();

        Assertions.assertNotNull(itemList);
        Assertions.assertEquals(itemList.size(), 2);
    }


    @Test
    public void ItemRepository_FindById_ReturnsNotNull(){
        Item item = Item.builder()
                .itemID("9780470383278")
                .type(ItemType.Book)
                .build();

        itemRepository.save(item);

        Item retrievedItem = itemRepository.findById(item.getID()).get();

        Assertions.assertNotNull(retrievedItem);
    }


    @Test
    public void ItemRepository_DeleteItem_ReturnsEmpty(){
        Item item = Item.builder()
                .itemID("9780470383278")
                .type(ItemType.Book)
                .build();

        itemRepository.save(item);

        itemRepository.deleteById(item.getID());
        Optional<Item> retrievedBook = itemRepository.findById(item.getID());

        Assertions.assertTrue(retrievedBook.isEmpty());
    }
}
