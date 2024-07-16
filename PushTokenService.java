package com.vms.medxbid.services;

import com.vms.medxbid.models.PushToken;
import com.vms.medxbid.repositories.PushTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PushTokenService {

    @Autowired
    private PushTokenRepository pushTokenRepository;

    public void savePushToken(Long userId, String token) {
        PushToken pushToken = new PushToken();
        pushToken.setUserId(userId);
        pushToken.setToken(token);
        pushTokenRepository.save(pushToken);
    }

    public void deletePushToken(Long id) {
        pushTokenRepository.deleteById(id);
    }

    public Optional<PushToken> findPushTokenByUserId(Long userId) {
        return pushTokenRepository.findByUserId(userId);
    }
}
