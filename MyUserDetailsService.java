package com.vms.medxbid.services;

import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.repositories.DistUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.vms.medxbid.models.User;
import com.vms.medxbid.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vms.medxbid.models.MyUserDetails;
//MyUserDetailsService
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DistUserRepo distUserRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            return user.map(MyUserDetails::new).get();
        }

        Optional<DistUser> distUser = distUserRepo.findByUserName(userName);
        if (distUser.isPresent()) {
            return distUser.map(MyUserDetails::new).get();
        }

        throw new UsernameNotFoundException("Not found: " + userName);
    }
    public boolean isCustUser(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    public boolean isDistUser(String userName) {
        return distUserRepo.findByUserName(userName).isPresent();
    }
}
