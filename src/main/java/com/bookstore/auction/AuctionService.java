package com.bookstore.auction;

import com.bookstore.item.ItemDTO;
import com.bookstore.item.ItemNotFoundException;
import com.bookstore.item.ItemService;
import com.bookstore.item.ItemType;
import com.bookstore.item.book.BookDTO;
import com.bookstore.item.book.BookNotFoundException;
import com.bookstore.item.book.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final BookRepository bookRepository;

    public void createAuction(Auction auction) throws BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        auction.setStartDate(LocalDate.now());
        auction.setEndDate(LocalDate.from(LocalDate.now().plusDays(7)));
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


    public AuctionsData getAuctions(int page, int pageSize, String category, String sort) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        List<Auction> auctions = auctionRepository.findAll();
        List<AuctionDTO> auctionDTOS = convertToDTOS(auctions);
        if (category.equals("undefined")) {
            if (sort.equals("descending")) {
                return new AuctionsData(convertToDTOS(auctionRepository.findAllByPriceDescending(pageable).stream().toList()), auctions.size());

            } else if (sort.equals("ascending")) {
                return new AuctionsData(convertToDTOS(auctionRepository.findAllByPriceAscending(pageable).stream().toList()), auctions.size());

            } else {
                return new AuctionsData(auctionDTOS, auctions.size());
            }

        } else {
            if (sort.equals("descending")) {
                return new AuctionsData(convertToDTOS(auctionRepository.findByCategoryDescending(pageable, category).stream().toList()), auctionRepository.findAllByCategory(category).size());

            } else if (sort.equals("ascending")) {
                return new AuctionsData(convertToDTOS(auctionRepository.findByCategoryAscending(pageable, category).stream().toList()), auctionRepository.findAllByCategory(category).size());

            } else {
                auctions = auctionRepository.findAllByCategory(category);
                return new AuctionsData(convertToDTOS(auctions), auctions.size());
            }
        }
    }

    private List<AuctionDTO> searchByCategory(List<AuctionDTO> auctionDTOS, String category) {
        for (AuctionDTO auctionDTO : auctionDTOS) {
            //TODO fix if items are of other type than book
            if (auctionDTO.getItem() instanceof BookDTO) {
                if (!((BookDTO) auctionDTO.getItem()).getCategories().equals(category)) {
                    auctionDTOS.remove(auctionDTO);
                }
            }
        }
        return auctionDTOS;
    }


    private List<AuctionDTO> convertToDTOS(List<Auction> auctions) throws AuctionNotFoundException, BookNotFoundException, JsonProcessingException, ItemNotFoundException {
        List<AuctionDTO> auctionDTOS = new ArrayList<>();
        if (auctions.size() == 0) {
            throw new AuctionNotFoundException();
        }
        for (Auction auction : auctions) {

            auctionDTOS.add(auctionMapper.mapToDTO(auction, itemService.getItem(auction.getId())));
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
