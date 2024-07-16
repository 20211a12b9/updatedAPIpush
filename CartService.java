package com.vms.medxbid.services;

import com.vms.medxbid.models.Cart;
import com.vms.medxbid.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findCartByProductIdAndUserId(Integer productId, Long userId) {
        return cartRepository.findByProductIdAndUserId(productId, userId);
    }

    public List<Cart> getDetailsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
    public int countProductsByUserId(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        return cartItems.size();
    }
    public void deleteCartItem(Integer productId, Long userId) {
        Optional<Cart> cartItemOptional = cartRepository.findByProductIdAndUserId(productId, userId);
        cartItemOptional.ifPresent(cartRepository::delete);
    }
}
