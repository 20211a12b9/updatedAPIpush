package com.vms.medxbid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vms.medxbid.models.BidProduct;
import com.vms.medxbid.models.BidRequest;

public interface BidProductRepository extends JpaRepository<BidProduct, Long>{
     List<BidProduct> findByBidRequest(BidRequest bidRequest);
   
    
}
