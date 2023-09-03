package com.bookstore.auction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@ToString
public class Auction {

    @Id
    @SequenceGenerator(
            name= "auction_sequence",
            sequenceName = "auction_sequence",
            allocationSize = 1
    )



    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "auction_sequence"
    )

    @Getter
    private Long id;

    @Getter
    private String ownerEmail;

    @Getter
    private Long itemId;

    @Getter
    private LocalDate startDate;

    @Getter
    private LocalDate endDate;

    @Getter
    private BigDecimal price;



    public Auction(Long id, String ownerEmail, Long itemId, LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.itemId = itemId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Auction() {

    }
}
