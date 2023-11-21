package com.bookstore.repository;

import com.bookstore.auction.Auction;
import com.bookstore.auction.AuctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

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
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction savedAuction = auctionRepository.save(auction);

        Assertions.assertNotNull(savedAuction);
        Assertions.assertTrue(savedAuction.getId() >= 0);
    }


    @Test
    public void AuctionRepository_FindAll_ReturnsMoreThanOneAuction() {
        Auction auction1 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction auction2 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("20.00")).build();

        auctionRepository.save(auction1);
        auctionRepository.save(auction2);

        List<Auction> auctionList = auctionRepository.findAll();

        Assertions.assertNotNull(auctionList);
        Assertions.assertTrue(auctionList.size() > 1);
    }


    @Test
    public void AuctionRepository_FindById_ReturnsNotNull() {
        Auction auction = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        auctionRepository.save(auction);

        Auction retrievedAuction = auctionRepository.findAuctionById(auction.getId()).get();

        Assertions.assertNotNull(retrievedAuction);
    }


    @Test
    public void AuctionRepository_DeleteAuction_ReturnsEmpty() {
        Auction auction = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        auctionRepository.save(auction);

        auctionRepository.deleteById(auction.getId());
        Optional<Auction> retrievedAuction = auctionRepository.findAuctionById(auction.getId());

        Assertions.assertTrue(retrievedAuction.isEmpty());
    }

    @Test
    public void AuctionRepository_FindAll_ReturnByPriceDescending() {
        Auction auction1 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction auction2 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780201715941")
                .price(new BigDecimal("30.00")).build();

        Auction auction3 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9781794880016")
                .price(new BigDecimal("20.00")).build();

        auctionRepository.save(auction1);
        auctionRepository.save(auction2);
        auctionRepository.save(auction3);

        List<Auction> auctionList = auctionRepository.findAllByPriceAscending(PageRequest.of(0, 3)).stream().toList();

        for (int i = 1; i < auctionList.size(); i++) {
            Assertions.assertTrue(auctionList.get(i).getPrice().compareTo(auctionList.get(i - 1).getPrice()) >= 0);
        }
    }

    @Test
    public void AuctionRepository_FindAll_ReturnByPriceAscending() {
        Auction auction1 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction auction2 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780201715941")
                .price(new BigDecimal("30.00")).build();

        Auction auction3 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9781794880016")
                .price(new BigDecimal("20.00")).build();

        auctionRepository.save(auction1);
        auctionRepository.save(auction2);
        auctionRepository.save(auction3);

        List<Auction> auctionList = auctionRepository.findAllByPriceDescending(PageRequest.of(0, 3)).stream().toList();

        for (int i = 1; i < auctionList.size(); i++) {
            Assertions.assertTrue(auctionList.get(i).getPrice().compareTo(auctionList.get(i - 1).getPrice()) <= 0);
        }
    }

    @Test
    public void AuctionRepository_FindAllByCategory_ReturnByPriceDescending() {
        Auction auction1 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction auction2 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9781000094657")
                .price(new BigDecimal("30.00")).build();

        Auction auction3 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780123810281")
                .price(new BigDecimal("20.00")).build();

        auctionRepository.save(auction1);
        auctionRepository.save(auction2);
        auctionRepository.save(auction3);

        List<Auction> auctionList = auctionRepository.findByCategoryDescending(PageRequest.of(0, 3), "Computers").stream().toList();

        for (int i = 1; i < auctionList.size(); i++) {
            Assertions.assertTrue(auctionList.get(i).getPrice().compareTo(auctionList.get(i - 1).getPrice()) >= 0);
        }
    }

    @Test
    public void AuctionRepository_FindAllByCategory_ReturnByPriceAscending() {
        Auction auction1 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        Auction auction2 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9781000094657")
                .price(new BigDecimal("30.00")).build();

        Auction auction3 = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780123810281")
                .price(new BigDecimal("20.00")).build();

        auctionRepository.save(auction1);
        auctionRepository.save(auction2);
        auctionRepository.save(auction3);

        List<Auction> auctionList = auctionRepository.findByCategoryAscending(PageRequest.of(0, 3), "Computers").stream().toList();

        for (int i = 1; i < auctionList.size(); i++) {
            Assertions.assertTrue(auctionList.get(i).getPrice().compareTo(auctionList.get(i - 1).getPrice()) >= 0);
        }
            Assertions.assertEquals(3, auctionList.size());
    }

    @Test
    public void AuctionRepository_FindAllByCategory_ReturnNotNull() {
        Auction auction = Auction.builder()
                .owner_email("abc123@a.a")
                .item_id("9780470383278")
                .price(new BigDecimal("10.00")).build();

        auctionRepository.save(auction);

        List<Auction> auctionList = auctionRepository.findAllByCategory("Computers");

        Assertions.assertEquals(1, auctionList.size());
    }
}
