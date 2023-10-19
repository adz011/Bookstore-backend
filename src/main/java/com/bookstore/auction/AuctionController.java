package com.bookstore.auction;

import com.bookstore.item.ItemNotFoundException;
import com.bookstore.item.book.BookNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/auctions")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuctionController {
    @Autowired
    private final AuctionService auctionService;

    @PostMapping
    public ResponseEntity<String> createAuction(
            @RequestBody Auction auction
    ) throws BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        auctionService.createAuction(auction);
        return ResponseEntity.ok("Auction was created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Auction>> getAllAuctions() throws AuctionNotFoundException {
        return ResponseEntity.ok(auctionService.getAllAuctions());
    }

    @GetMapping(params = "id")
    public ResponseEntity<Auction> getById(
            @RequestParam Long id
    ) throws AuctionNotFoundException {
        return ResponseEntity.ok(auctionService.getById(id));
    }

    @GetMapping(path = "/price/descending", params = {"page", "pageSize"})
    public ResponseEntity<List<AuctionDTO>> getSortedPriceDescending(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        return ResponseEntity.ok(auctionService.getAuctionsSortedPriceDescending(page, pageSize));
    }

    @GetMapping(path = "/price/ascending", params = {"page", "pageSize"})
    public ResponseEntity<List<AuctionDTO>> getSortedPriceAscending(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        return ResponseEntity.ok(auctionService.getAuctionsSortedPriceAscending(page, pageSize));
    }


    @PutMapping
    public ResponseEntity<String> updateAuction(
            @RequestParam Long id,
            @RequestParam(required = false) String ownerEmail,
            @RequestParam(required = false) String itemId,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) BigDecimal price
    ) throws AuctionNotFoundException {
        auctionService.updateAuction(id, ownerEmail, itemId, endDate, price);
        return ResponseEntity.ok("Auction with id " + id + " was updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAuction(
            @RequestParam Long id
    ) throws AuctionNotFoundException {
        auctionService.deleteAuction(id);
        return ResponseEntity.ok("Auction with id " + id + " was deleted successfully");
    }
}
