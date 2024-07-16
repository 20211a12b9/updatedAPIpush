package com.vms.medxbid.services;

import com.vms.medxbid.models.User;
import com.vms.medxbid.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private UserRepo userRepo;
    public String getProfile(Long userId)
    {
        System.out.println("**********"+userRepo.findUsernameById(userId));
        return userRepo.findUsernameById(userId);
    }
}
