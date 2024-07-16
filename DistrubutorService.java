package com.vms.medxbid.services;

import com.vms.medxbid.exception.UsernameAlreadyExistsException;
import com.vms.medxbid.models.Customer;
import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.models.Distributor;
import com.vms.medxbid.models.User;
import com.vms.medxbid.repositories.DistUserRepo;
import com.vms.medxbid.repositories.DistrubutorRepo;
import com.vms.medxbid.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DistrubutorService {

    private static final Logger logger = LoggerFactory.getLogger(DistrubutorService.class);
    @Autowired
    private DistrubutorRepo distrubutorRepo;
    @Autowired
    private DistUserRepo distUserRepo;
    @Autowired
    private UserRepo userRepo;



    public DistUser saveDistDetails(DistUser distUser) throws IOException
    {


        Optional<DistUser> existingDistuser = distUserRepo.findByUserName(distUser.getUserName());
        if (existingDistuser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        Optional<User> existingUser = userRepo.findByUserName(distUser.getUserName());
        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists in CustomerUser");
        }
////        byte[] decodedBytes = Base64.getDecoder().decode(distributor.getContent().split(",")[1]);
////        File uploadedFile = new File(String.valueOf(distributor.getDrug_license()));
////        FileOutputStream outputStream = new FileOutputStream(uploadedFile);
//////        outputStream.write(decodedBytes);
////        Distributor fileEntity = new Distributor();
////        fileEntity.setDrugLicensePath(uploadedFile.getAbsolutePath());
////        distrubutorRepo.save(fileEntity);
////        byte[] decodedBytes = Base64.getDecoder().decode(customer.getContent());
//        File uploadedFile = new File(String.valueOf(distributor.getdrugliseance()));
//        FileOutputStream outputStream = new FileOutputStream(uploadedFile);
////        outputStream.write(decodedBytes);
//        distributor.setDrug_license(uploadedFile.getAbsolutePath());
//        System.out.println(uploadedFile.getAbsolutePath());

        return distUserRepo.save(distUser);
    }

    public List<String> getDistributorNamesByDistUserId(Long distUserId) {
        List<String> distributorNames = distrubutorRepo.findDistributorNamesByDistUserId(distUserId);
        logger.debug("Fetched Distributor Names: {}", distributorNames);
        return distributorNames;
    }
}

