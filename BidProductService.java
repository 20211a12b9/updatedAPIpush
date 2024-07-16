package com.vms.medxbid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.medxbid.models.BidProduct;
import com.vms.medxbid.models.BidRequest;
import com.vms.medxbid.repositories.BidProductRepository;
import com.vms.medxbid.repositories.BidRequestRepository;

@Service
public class BidProductService {

    @Autowired
    BidProductRepository bidProductRepository;
    @Autowired
    BidRequestRepository bidRequestRepository;

    public List<BidProduct> fetchBidProductsByBidRequest(Long id) {
        System.out.println("from service" + id);
        List<BidProduct> bidProducts = bidProductRepository.findByBidRequest(bidRequestRepository.findById(id).get());
        for(BidProduct bp: bidProducts){
           System.out.println(bp.getProduct());
        }
        return bidProducts;
    }
    
}
