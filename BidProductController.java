package com.vms.medxbid.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vms.medxbid.models.BidProduct;
import com.vms.medxbid.services.BidProductService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class BidProductController {
@Autowired
BidProductService bidProductService;

    @GetMapping("/bidProducts/{id}")
    public  List<BidProduct>  getBidProducts(@PathVariable("id") Long id) {
       
             System.out.println("from controller" + id);            
            return bidProductService.fetchBidProductsByBidRequest(id);
    
    }
    
}
