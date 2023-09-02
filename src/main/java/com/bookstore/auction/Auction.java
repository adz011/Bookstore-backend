package com.bookstore.auction;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
public class Auction {
    @Getter
    private String id;

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

    public Auction(String id, String ownerEmail, Long itemId, LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.itemId = itemId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

}
