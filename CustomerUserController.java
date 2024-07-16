package com.vms.medxbid.controllers;

import com.vms.medxbid.models.User;
import com.vms.medxbid.services.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")

public class CustomerUserController {

    @Autowired
    private CustomerUserService customerUserService;

    @PostMapping("/Customeruserdata")
    public User createUser(@RequestBody User user) throws IOException {
        return customerUserService.saveCustomerUserDetails(user);
    }

    @GetMapping("/address/{userId}")
    public ResponseEntity<String> getAddressByUserId(@PathVariable Long userId) {
        Optional<String> address = customerUserService.getAddressByUserId(userId);
        return address.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        try {
            User user = customerUserService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
