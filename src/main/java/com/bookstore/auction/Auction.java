package com.bookstore.auction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String itemId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
