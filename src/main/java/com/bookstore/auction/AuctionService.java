package com.bookstore.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionService {
    @Autowired
    private final AuctionRepository auctionRepository;

    public void createAuction(Auction auction) {
        auctionRepository.save(auction);
    }

    public List<Auction> getAllAuctions() throws AuctionNotFoundException {
        List<Auction> auctionList = auctionRepository.findAll().stream().toList();
        if (auctionList.size() == 0) {
            throw new AuctionNotFoundException();
        }
        return auctionList;
    }

    public Auction getById(Long id) throws AuctionNotFoundException {
        return auctionRepository.findAuctionById(id).orElseThrow(AuctionNotFoundException::new);
    }

    public List<Auction> getAuctionsSortedPriceDescending(int page, int pageSize) throws AuctionNotFoundException {
        //Page operation is done due to site not starting at page 0
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Auction> auctions = auctionRepository.findAllByPriceDescending(pageable).orElseThrow(AuctionNotFoundException::new);
        List<Auction> auctionList = auctions.stream().toList();
        if (auctionList.size() == 0) {
            throw new AuctionNotFoundException();
        }
        return auctionList;
    }

    public List<Auction> getAuctionsSortedPriceAscending(int page, int pageSize) throws AuctionNotFoundException {
        //Page operation is done due to site not starting at page 0
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Auction> auctions = auctionRepository.findAllByPriceAscending(pageable).orElseThrow(AuctionNotFoundException::new);
        List<Auction> auctionList = auctions.stream().toList();
        if (auctionList.size() == 0) {
            throw new AuctionNotFoundException();
        }
        return auctionList;
    }

    @Transactional
    public Auction updateAuction(
            Long id,
            String ownerEmail,
            Long itemId,
            LocalDate endDate,
            BigDecimal price
    ) throws AuctionNotFoundException {
        Auction optionalAuction = auctionRepository.findAuctionById(id).orElseThrow(AuctionNotFoundException::new);
        if (ownerEmail != null) {
            optionalAuction.setOwnerEmail(ownerEmail);
        }
        if (itemId != null) {
            optionalAuction.setItemId(itemId);
        }
        if (endDate != null && endDate.isAfter(LocalDate.now())) {
            optionalAuction.setEndDate(endDate);
        }
        if (price != null && price.floatValue() > 0) {
            optionalAuction.setPrice(price);
        }
        return optionalAuction;
    }

    public Auction deleteAuction(Long id) throws AuctionNotFoundException {
        Auction auction = auctionRepository.findAuctionById(id).orElseThrow(AuctionNotFoundException::new);
        auctionRepository.delete(auction);
        return auction;
    }
}
