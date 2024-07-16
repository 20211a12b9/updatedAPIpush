package com.vms.medxbid.controllers;

import com.vms.medxbid.models.User;
import com.vms.medxbid.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile/{userId}")
    public String getUsername(@PathVariable Long userId)
    {

         return profileService.getProfile(userId);
    }
}
