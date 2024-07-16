package com.vms.medxbid.services;

import com.vms.medxbid.exception.UsernameAlreadyExistsException;
import com.vms.medxbid.models.Customer;
import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.models.FileUpload; // Correct import
import com.vms.medxbid.models.User;
import com.vms.medxbid.repositories.CustomerRepository;
import com.vms.medxbid.repositories.DistUserRepo;
import com.vms.medxbid.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerUserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DistUserRepo distUserRepo;

    public User saveCustomerUserDetails(User user) throws IOException {
        Optional<User> existingUser = userRepo.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        Optional<DistUser> existingDistUser = distUserRepo.findByUserName(user.getUserName());
        if (existingDistUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists in distributor");
        }

        return userRepo.save(user);
    }

    public Optional<String> getAddressByUserId(Long userId) {
        return customerRepository.findAddressByUserId(userId);
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepo.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setApproved(updatedUser.isApproved());
            existingUser.setCreatedby(updatedUser.getCreatedby());
            existingUser.setCreateddate(updatedUser.getCreateddate());
            existingUser.setModifiedby(updatedUser.getModifiedby());
            existingUser.setModifieddate(updatedUser.getModifieddate());

            List<Customer> updatedCustomers = updatedUser.getCustomers();
            if (updatedCustomers != null) {
                for (Customer updatedCustomer : updatedCustomers) {
                    Optional<Customer> existingCustomerOptional = customerRepository.findById(updatedCustomer.getCustomer_id());
                    if (existingCustomerOptional.isPresent()) {
                        Customer existingCustomer = existingCustomerOptional.get();
                        existingCustomer.setFirm_name(updatedCustomer.getFirm_name());
                        existingCustomer.setDrug_license(updatedCustomer.getDrug_license());
                        existingCustomer.setDl_number(updatedCustomer.getDl_number());
                        existingCustomer.setGst(updatedCustomer.getGst());
                        existingCustomer.setLatitude(updatedCustomer.getLatitude());
                        existingCustomer.setLongitude(updatedCustomer.getLongitude());
                        existingCustomer.setAddress(updatedCustomer.getAddress());
                        existingCustomer.setPincode(updatedCustomer.getPincode());
                        customerRepository.save(existingCustomer);
                    }
                }
            }

            return userRepo.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
