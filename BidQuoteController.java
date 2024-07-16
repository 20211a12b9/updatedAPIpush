package com.vms.medxbid.controllers;

import com.vms.medxbid.models.BidQuote;
import com.vms.medxbid.services.BidQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BidQuoteController {

    @Autowired
    private BidQuoteService bidQuoteService;

    public static class BidQuoteRequest {
        public double price;
        public Integer bidRequestId;
        public Long distUserId;
        public Integer discount;
        public String deliveryIn;
        public  String userId;
    }

    @PostMapping("/bidQuote")
    public BidQuote fetchQuote(@RequestBody BidQuoteRequest bidQuoteRequest) {
        if (bidQuoteRequest.bidRequestId == null || bidQuoteRequest.distUserId == null) {
            throw new IllegalArgumentException("bidRequestId and distUserId cannot be null");
        }
        System.out.println("distUserIddistUserIddistUserIddistUserId"+bidQuoteRequest.distUserId);
        BidQuote bidQuote = new BidQuote();
        bidQuote.setPrice(bidQuoteRequest.price);
        bidQuote.setDiscount(bidQuoteRequest.discount);
        bidQuote.setDeliveryIn(bidQuoteRequest.deliveryIn);
        bidQuote.setUserId(bidQuoteRequest.userId);
        return bidQuoteService.saveBidQuote(bidQuote, bidQuoteRequest.bidRequestId, bidQuoteRequest.distUserId, Long.valueOf(bidQuoteRequest.userId));
    }

    @GetMapping("/bidRequestPrice/{bidRequestId}")
    public List<Object[]> getDistUserIdAndPriceByBidRequestId(@PathVariable Long bidRequestId) {
        return bidQuoteService.findDistUserIdAndPriceByBidRequestId(bidRequestId);
    }

    public BidQuoteController(BidQuoteService bidQuoteService) {
        this.bidQuoteService = bidQuoteService;
    }
    @GetMapping("/getAllDistUserIdAndPrice/{userId}")
    public List<Object[]> getAllDistUserIdAndPrice(@PathVariable("userId") String userId) {
        return bidQuoteService.getAllDistUserIdAndPrice(userId);
    }
}
