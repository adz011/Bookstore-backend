package com.bookstore.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Optional<Auction> findAuctionById(Long id);

    @Query("SELECT a FROM Auction a ORDER BY a.price DESC")
    Optional<Page<Auction>> findAllByPriceDescending(Pageable pageable);

    @Query("SELECT a FROM Auction a ORDER BY a.price ASC")
    Optional<Page<Auction>> findAllByPriceAscending(Pageable pageable);
}
