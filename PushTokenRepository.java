package com.vms.medxbid.repositories;

import com.vms.medxbid.models.PushToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PushTokenRepository extends JpaRepository<PushToken, Long> {
    Optional<PushToken> findByUserId(Long userId);
}
