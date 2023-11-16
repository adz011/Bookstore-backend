package com.bookstore.auction;

import com.bookstore.item.ItemDTO;

public class AuctionMapper {

    public AuctionDTO mapToDTO(Auction auction, ItemDTO itemDTO) {
        return new AuctionDTO(
                auction.getId(),
                auction.getOwner_email(),
                auction.getItem_id(),
                auction.getStart_date(),
                auction.getEnd_date(),
                auction.getPrice(),
                itemDTO
        ) {
        };
    }
}