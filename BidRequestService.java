package com.vms.medxbid.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.vms.medxbid.models.BidRequestProductDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.medxbid.models.BidRequest;
import com.vms.medxbid.models.BidRequestProducts;
import com.vms.medxbid.models.BidRequestQty;
import com.vms.medxbid.repositories.BidRequestRepository;

import jakarta.persistence.Tuple;

@Service
public class BidRequestService {

    @Autowired
    private BidRequestRepository bidRequestRepository;

    // Save bid request
    public BidRequest saveBidRequest(BidRequest bidRequest) {
        System.out.println("iiiiii"+bidRequest);
        return bidRequestRepository.save(bidRequest);
    }

    // Fetch bid requests by user ID
    public List<BidRequestQty> fetchBidRequests(int userId) {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        List<Tuple> tuples = bidRequestRepository.findBidRequestQty(userId);

        for (Tuple tuple : tuples) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId(tuple.get("bid_request_id", Integer.class));
            bidRequestQty.setBidDate(tuple.get("bid_date", String.class));
            bidRequestQty.setQty(tuple.get("qty", Number.class).intValue());
            bidRequestQty.setPaymentIN(tuple.get("paymentIN", String.class));
            bidRequestQtys.add(bidRequestQty);
        }

        return bidRequestQtys;
    }

    // Fetch bid request products by bid request ID
    public List<BidRequestProducts> fetchBidRequest(Long id) {
        List<BidRequestProducts> bidRequestProducts = new ArrayList<>();
        for (Tuple productQty : bidRequestRepository.fetchBidRequestProductDetails(id)) {
            BidRequestProducts bidRequestProduct = new BidRequestProducts();
            bidRequestProduct.setProductName((String) productQty.get(0));
            bidRequestProduct.setQuantity(((Long) productQty.get(1)).intValue());
            bidRequestProducts.add(bidRequestProduct);
        }
        return bidRequestProducts;
    }


    public List<BidRequestQty> getAllBidDistRequestsDetails() {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        for (Tuple tuple : bidRequestRepository.findAllBidDistRequests()) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId((Integer) tuple.get("bid_request_id"));
            bidRequestQty.setBidDate((String) tuple.get("bid_date"));
            bidRequestQty.setQty(((Long) tuple.get("qty")).intValue());
            bidRequestQtys.add(bidRequestQty);
        }
        return bidRequestQtys;
    }
    public Long getCountAllBidDistRequestsDetails() {

       return bidRequestRepository.findCountAllBidDistRequests();

    }
    // Fetch all bid requests
    public List<BidRequestQty> getAllBidRequestsDetails() {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        for (Tuple tuple : bidRequestRepository.findAllBidRequests()) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId((Integer) tuple.get("bid_request_id"));
            bidRequestQty.setBidDate((String) tuple.get("bid_date"));
            bidRequestQty.setQty(((Long) tuple.get("qty")).intValue());
            bidRequestQtys.add(bidRequestQty);
        }
        return bidRequestQtys;
    }
    public List<BidRequestQty> getAllBidPendingRequestsDetails() {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        for (Tuple tuple : bidRequestRepository.findAllBidPendingRequests()) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId((Integer) tuple.get("bid_request_id"));
            bidRequestQty.setBidDate((String) tuple.get("bid_date"));
            bidRequestQty.setQty(((Long) tuple.get("qty")).intValue());
            bidRequestQtys.add(bidRequestQty);
        }
        return bidRequestQtys;
    }
    public List<BidRequestQty> getAllBidProcessRequestsDetails() {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        for (Tuple tuple : bidRequestRepository.findAllBidProcessRequests()) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId((Integer) tuple.get("bid_request_id"));
            bidRequestQty.setBidDate((String) tuple.get("bid_date"));
            bidRequestQty.setQty(((Long) tuple.get("qty")).intValue());
            bidRequestQtys.add(bidRequestQty);
        }
        return bidRequestQtys;
    }

    public List<BidRequestQty> getAllCustomerBidRequestsDetails(Long userId) {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        for (Tuple tuple : bidRequestRepository.findAllCustomerBidRequests(userId)) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId((Integer) tuple.get("bid_request_id"));
            bidRequestQty.setBidDate((String) tuple.get("bid_date"));
            bidRequestQty.setQty(((Long) tuple.get("qty")).intValue());
            bidRequestQtys.add(bidRequestQty);
        }
        return bidRequestQtys;
    }


    public List<BidRequestQty> getAllBidDeliveredRequestsDetails() {
        List<BidRequestQty> bidRequestQtys = new ArrayList<>();
        for (Tuple tuple : bidRequestRepository.findAllBidDeliveredRequests()) {
            BidRequestQty bidRequestQty = new BidRequestQty();
            bidRequestQty.setBidRequestId((Integer) tuple.get("bid_request_id"));
            bidRequestQty.setBidDate((String) tuple.get("bid_date"));
            bidRequestQty.setQty(((Long) tuple.get("qty")).intValue());
            bidRequestQtys.add(bidRequestQty);
        }
        return bidRequestQtys;
    }
    public void deleteBidRequest(Long bidRequestId) {
        bidRequestRepository.deleteById(bidRequestId);
    }
    public void acceptBidRequest(Long bidRequestId) {

        BidRequest bidRequest = bidRequestRepository.findById(bidRequestId)
                .orElseThrow(() -> new RuntimeException("Bid Request not found"));
        bidRequest.setCustomerstatus(true);
        bidRequestRepository.save(bidRequest);
    }
    public void sendBidRequest(Long bidRequestId) {
        BidRequest bidRequest = bidRequestRepository.findById(bidRequestId)
                .orElseThrow(() -> new RuntimeException("Bid Request not found"));
        bidRequest.setdiststatus(true);
        bidRequestRepository.save(bidRequest);
    }
    public void deliverdBidRequest(Long bidRequestId) {
        BidRequest bidRequest = bidRequestRepository.findById(bidRequestId)
                .orElseThrow(() -> new RuntimeException("Bid Request not found"));
        bidRequest.setdeliverstatus(true);
        bidRequestRepository.save(bidRequest);
    }











    public List<BidRequestProductDetailsDTO> fetchBidRequestProductDetailsWithPrice(Long id) {
        List<Tuple> tuples = bidRequestRepository.fetchBidRequestProductDetailsWithPrice(id);
        return tuples.stream()
                .map(tuple -> {
                    BidRequestProductDetailsDTO dto = new BidRequestProductDetailsDTO();
                    dto.setProductName((String) tuple.get("product_name"));
                    dto.setTotalQuantity(((Number) tuple.get("total_quantity")).longValue());
                    dto.setPrice((Double) tuple.get("price"));
                    return dto;
                })
                .collect(Collectors.toList());
    }


//    public Integer getUserIdFromRequestId(Long requestId) {
//        BidRequest request = bidRequestRepository.findByRequestId(requestId);
//        return request != null ? request.getUserId() : null;
//    }

    public Integer findUserIdByBidRequestId(Integer bidRequestId) {
        return bidRequestRepository.findUserIdByBidRequestId(bidRequestId);
    }


    public String findPaymentINById(Integer bidRequestId) {
        BidRequest bidRequest = bidRequestRepository.findById(Long.valueOf(bidRequestId)).orElse(null);
        return bidRequest != null ? bidRequest.getPaymentIN() : null;
    }
}
