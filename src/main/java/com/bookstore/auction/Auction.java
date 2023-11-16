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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AUCTIONS")
public class Auction {
    @Id
    @GeneratedValue
    private Long id;
    private String owner_email;
    private String item_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private BigDecimal price;
}
