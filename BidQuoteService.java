package com.vms.medxbid.services;

import com.vms.medxbid.models.BidQuote;
import com.vms.medxbid.models.BidRequest;
import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.repositories.BidQuoteRepo;

import com.vms.medxbid.repositories.BidRequestRepository;
import com.vms.medxbid.repositories.DistUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidQuoteService {

    @Autowired
    private BidQuoteRepo bidQuoteRepo;

    @Autowired
    private BidRequestRepository bidRequestRepo;

    @Autowired
    private DistUserRepo distUserRepo;

    public BidQuote saveBidQuote(BidQuote bidQuote, Integer bidRequestId, Long distUserId, Long userId) {
        System.out.println("distUserIddistUserIddistUserIddistUserId"+distUserId);
        if (bidRequestId == null) {
            throw new IllegalArgumentException("bidRequestId cannot be null");
        }
        if (distUserId == null) {
            throw new IllegalArgumentException("distUserId cannot be null");
        }

        BidRequest bidRequest = bidRequestRepo.findById(Long.valueOf(bidRequestId))
                .orElseThrow(() -> new RuntimeException("BidRequest not found"));
        DistUser distUser = distUserRepo.findById(Math.toIntExact(distUserId))
                .orElseThrow(() -> new RuntimeException("DistUser not found"));

        bidQuote.setBidRequest(bidRequest);
        bidQuote.setDistUser(distUser);

        BidQuote savedBidQuote = bidQuoteRepo.save(bidQuote);

        // Update the pricedone field
        bidRequest.setPricedone(true);
        bidRequestRepo.save(bidRequest);

        return savedBidQuote;
    }



    public List<Object[]> findDistUserIdAndPriceByBidRequestId(Long bidRequestId) {
        return bidQuoteRepo.findDistUserIdAndPriceByBidRequestId(bidRequestId);
    }
    public BidQuoteService(BidQuoteRepo bidQuoteRepo) {
        this.bidQuoteRepo = bidQuoteRepo;
    }

    public List<Object[]> getAllDistUserIdAndPrice(String userId) {
        return bidQuoteRepo.findAllDistUserIdAndPrice(userId);
    }
}
