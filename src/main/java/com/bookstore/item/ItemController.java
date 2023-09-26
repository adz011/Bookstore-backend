package com.bookstore.item;

import com.bookstore.item.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable("id") long id){
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTrip(@RequestBody ItemDTO itemDTO) {
        itemService.createTrip(itemDTO);
        return ResponseEntity.ok().build();
    }
}
