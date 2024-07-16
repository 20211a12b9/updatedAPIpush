package com.vms.medxbid.controllers;

import com.vms.medxbid.services.PushTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class PushTokenController {

    @Autowired
    private PushTokenService pushTokenService;

    @PostMapping("/store")
    public ResponseEntity<String> storePushToken(@RequestParam Long userId, @RequestParam String token) {
        pushTokenService.savePushToken(userId, token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Push token stored successfully.");
    }

    // Add more endpoints as needed for updating, deleting, or fetching push tokens
}
