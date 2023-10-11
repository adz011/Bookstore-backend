package com.bookstore.item;

import com.bookstore.item.book.BookNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) throws BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        return ResponseEntity.ok().body(itemService.createItem(itemDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable("id") long id) throws BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PutMapping()
    public ResponseEntity<Void> updateItem(@RequestBody Item item){
        itemService.updateItem(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok().body(itemService.getItems(12));
    }
}
