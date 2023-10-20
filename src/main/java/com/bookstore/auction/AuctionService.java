package com.bookstore.auction;

import com.bookstore.item.ItemDTO;
import com.bookstore.item.ItemNotFoundException;
import com.bookstore.item.ItemService;
import com.bookstore.item.ItemType;
import com.bookstore.item.book.BookNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {
    @Autowired
    private final AuctionRepository auctionRepository;

    private final AuctionMapper auctionMapper = new AuctionMapper();

    @Autowired

    private final ItemService itemService;

    public void createAuction(Auction auction) throws BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        auctionRepository.save(auction);
        itemService.createItem(new ItemDTO(auction.getItemId(), ItemType.Book));
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

    public List<AuctionDTO> getAuctionsSortedPriceDescending(int page, int pageSize) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        //Page operation is done due to site not starting at page 0
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Auction> auctions = auctionRepository.findAllByPriceDescending(pageable).orElseThrow(AuctionNotFoundException::new);
        return  convertToDTOS(auctions, auctionRepository.count());

    }

    public List<AuctionDTO> getAuctionsSortedPriceAscending(int page, int pageSize) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        //Page operation is done due to site not starting at page 0
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Auction> auctions = auctionRepository.findAllByPriceAscending(pageable).orElseThrow(AuctionNotFoundException::new);
        return  convertToDTOS(auctions, auctionRepository.count());
    }

    private List<AuctionDTO> convertToDTOS(Page<Auction> auctions, long totalItems) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        List<Auction> auctionList = auctions.stream().toList();
        List<AuctionDTO> auctionDTOS = new ArrayList<>();
        if (auctionList.size() == 0) {
            throw new AuctionNotFoundException();
        }
        for (Auction auction: auctions ){

            auctionDTOS.add(auctionMapper.mapToDTO(auction, itemService.getItem(auction.getId()), totalItems));
        }
        return auctionDTOS;
    }

    @Transactional
    public Auction updateAuction(
            Long id,
            String ownerEmail,
            String itemId,
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
