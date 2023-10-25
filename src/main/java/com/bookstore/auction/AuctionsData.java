package com.bookstore.auction;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuctionsData {
    List<AuctionDTO> auctionDTOList;
    int totalItems;
}
