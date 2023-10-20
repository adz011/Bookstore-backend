package com.bookstore.auction;

import com.bookstore.item.ItemDTO;

public class AuctionMapper {

    public AuctionDTO mapToDTO(Auction auction, ItemDTO itemDTO, long totalItems) {
        return new AuctionDTO(
                auction.getId(),
                auction.getOwnerEmail(),
                auction.getItemId(),
                auction.getStartDate(),
                auction.getEndDate(),
                auction.getPrice(),
                itemDTO,
                totalItems
        ) {
        };
    }
}