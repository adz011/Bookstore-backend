package com.bookstore.auction;

import com.bookstore.item.ItemDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuctionDTO {
    private long id;
    private String ownerEmail;
    private String itemId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private ItemDTO item;

}