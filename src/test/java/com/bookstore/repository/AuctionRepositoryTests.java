package com.bookstore.repository;

import com.bookstore.auction.Auction;
import com.bookstore.auction.AuctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuctionRepositoryTests {

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    public void AuctionRepository_SaveAll_ReturnSavedAuction() {
        Auction auction = Auction.builder()
                .ownerEmail("abc123@a.a")
                .itemId("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction savedAuction = auctionRepository.save(auction);

        Assertions.assertNotNull(savedAuction);
        Assertions.assertTrue(savedAuction.getId()>=0);
    }


    @Test
    public void AuctionRepository_FindAll_ReturnsMoreThanOneAuction(){
        Auction auction1 = Auction.builder()
                .ownerEmail("abc123@a.a")
                .itemId("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction auction2 = Auction.builder()
                .ownerEmail("abc123@a.a")
                .itemId("9780470383278")
                .price(new BigDecimal("20.00")).build();

        auctionRepository.save(auction1);
        auctionRepository.save(auction2);

        List<Auction> auctionList = auctionRepository.findAll();

        Assertions.assertNotNull(auctionList);
        Assertions.assertTrue(auctionList.size()>1);
    }


    @Test
    public void AuctionRepository_FindById_ReturnsNotNull(){
        Auction auction = Auction.builder()
                .ownerEmail("abc123@a.a")
                .itemId("9780470383278")
                .price(new BigDecimal("10.00")).build();

        auctionRepository.save(auction);

        Auction retrievedAuction = auctionRepository.findAuctionById(auction.getId()).get();

        Assertions.assertNotNull(retrievedAuction);
    }


    @Test
    public void AuctionRepository_DeleteAuction_ReturnsEmpty(){
        Auction auction = Auction.builder()
                .ownerEmail("abc123@a.a")
                .itemId("9780470383278")
                .price(new BigDecimal("10.00")).build();

        auctionRepository.save(auction);

        auctionRepository.deleteById(auction.getId());
        Optional<Auction> retrievedAuction = auctionRepository.findAuctionById(auction.getId());

        Assertions.assertTrue(retrievedAuction.isEmpty());
    }
}
