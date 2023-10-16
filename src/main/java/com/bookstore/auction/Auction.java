package com.bookstore.auction;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue
    private Long id;
    private String ownerEmail;
    private Long itemId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
