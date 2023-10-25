package com.bookstore.auction;

import com.bookstore.item.book.category.Category;
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
    Page<Auction> findAllByPriceDescending(Pageable pageable);

    @Query("SELECT a FROM Auction a ORDER BY a.price ASC")
    Page<Auction> findAllByPriceAscending(Pageable pageable);

    @Query("SELECT a FROM Auction a JOIN Book b JOIN b.categories c WHERE b.ISBN = a.itemId AND c.category = :category  ORDER BY a.price ASC")
    Page<Auction> findByCategoryAscending(Pageable pageable, @Param("category") String category);

    @Query("SELECT a FROM Auction a JOIN Book b JOIN b.categories c WHERE b.ISBN = a.itemId AND c.category = :category  ORDER BY a.price DESC")
    Page<Auction> findByCategoryDescending(Pageable pageable, @Param("category") String category);

    @Query("SELECT a FROM Auction a JOIN Book b JOIN b.categories c WHERE b.ISBN = a.itemId AND c.category = :category")
    List<Auction> findAllByCategory(@Param("category") String category);

}
