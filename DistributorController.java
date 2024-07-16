package com.vms.medxbid.controllers;

import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.models.Distributor;
import com.vms.medxbid.services.DistrubutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class DistributorController {
    private static final Logger logger = LoggerFactory.getLogger(DistributorController.class);
    @Autowired
    private DistrubutorService distrubutorService;

    @PostMapping("/Distributor")
    public void postDistDetails(@RequestBody DistUser distUser)throws IOException
    {
         distrubutorService.saveDistDetails(distUser);
    }
    @GetMapping("/names/{distUserId}")
    public List<String> getDistributorNamesByDistUserId(@PathVariable Long distUserId) {
        List<String> distributorNames = distrubutorService.getDistributorNamesByDistUserId(distUserId);
        logger.debug("Returned Distributor Names: {}", distributorNames);
        return distributorNames;
    }
}