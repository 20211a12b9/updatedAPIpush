package com.vms.medxbid.repositories;

import com.vms.medxbid.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByProductIdAndUserId(Integer productId, Long userId);
    List<Cart> findByUserId(Long userId);
}
