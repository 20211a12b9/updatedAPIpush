package com.vms.medxbid.controllers;

import java.util.ArrayList;
import java.util.List;

import com.vms.medxbid.models.*;
import com.vms.medxbid.services.CustomerUserService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vms.medxbid.services.BidRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class BidRequestController {
    @Autowired
    BidRequestService bidRequestService;

    @Autowired
    CustomerUserService customerUserService;
///generate post to save bid request

    @PostMapping("/saveBidRequest")
    public List<BidRequestQty> saveBidRequest(@RequestBody BidRequest bidRequest) {
        try {

            BidRequest savedBidRequest = bidRequestService.saveBidRequest(bidRequest);
            return bidRequestService.fetchBidRequests(bidRequest.getCustUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @GetMapping("/bidRequests/{id}")
    public List<BidRequestQty> getBidRequests(@PathVariable Integer id) {


            return bidRequestService.fetchBidRequests(id);

    }

    @GetMapping("/bidRequest/{id}")
    public List<BidRequestProducts> getBidRequest(@PathVariable("id") Long id) {

            return bidRequestService.fetchBidRequest(id);

    }

    @GetMapping("/getCountAllDistRequests")
    public Long getCountAllDistRequests() {
        return bidRequestService.getCountAllBidDistRequestsDetails();
    }
    @GetMapping("/getAllDistRequests")
    public List<BidRequestQty> getAllDistRequests() {
        return bidRequestService.getAllBidDistRequestsDetails();
    }
    @GetMapping("/getAllRequests")
    public List<BidRequestQty> getAllRequests() {
        return bidRequestService.getAllBidRequestsDetails();
    }
    @GetMapping("/getAllPendingRequests")
    public List<BidRequestQty> getAllPendingRequests() {
        return bidRequestService.getAllBidPendingRequestsDetails();
    }
    @GetMapping("/getAllProcessRequests")
    public List<BidRequestQty> getAllProcessRequests() {
        return bidRequestService.getAllBidProcessRequestsDetails();
    }
//customer orders
    @GetMapping("/getAllCustomerBidRequests/{userId}")
    public List<BidRequestQty> getAllCustomerRequests(@PathVariable Long userId) {
        return bidRequestService.getAllCustomerBidRequestsDetails(userId);
    }

    @GetMapping("/getAllDeliveredRequests")
    public List<BidRequestQty> getAllDeliveredRequests() {
        return bidRequestService.getAllBidDeliveredRequestsDetails();
    }
        @DeleteMapping("/deleteRequest/{id}")
        public ResponseEntity<Void> deleteBidRequest(@PathVariable Long id) {
            bidRequestService.deleteBidRequest(id);
            return ResponseEntity.noContent().build();
        }
    @PutMapping("/acceptBidRequest/{id}")
    public ResponseEntity<Void> acceptBidRequest(@PathVariable Long id) {
        bidRequestService.acceptBidRequest(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/sendBidRequest/{id}")
    public ResponseEntity<Void> sendBidRequest(@PathVariable Long id) {
        bidRequestService.sendBidRequest(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/deliveredBidRequest/{id}")
    public ResponseEntity<Void> deliverdBidRequest(@PathVariable Long id) {
        bidRequestService.deliverdBidRequest(id);
        return ResponseEntity.ok().build();
    }



    @Autowired
    public BidRequestController(BidRequestService bidRequestService) {
        this.bidRequestService = bidRequestService;
    }

    @GetMapping("/{id}/product-details")
    public List<BidRequestProductDetailsDTO> getBidRequestProductDetailsWithPrice(@PathVariable Long id) {
        return bidRequestService.fetchBidRequestProductDetailsWithPrice(id);
    }


    @GetMapping("/{bidRequestId}/user-id")
    public ResponseEntity<Integer> getUserIdByBidRequestId(@PathVariable Integer bidRequestId) {
        Integer userId = bidRequestService.findUserIdByBidRequestId(bidRequestId);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{bidRequestId}/paymentin")
    public String getPaymentIN(@PathVariable Integer bidRequestId) {
        return bidRequestService.findPaymentINById(bidRequestId);
    }

}