package com.vms.medxbid.controllers;

import com.vms.medxbid.models.Cart;
import com.vms.medxbid.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @GetMapping("/cart/check")
    @ResponseBody
    public boolean checkCart(@RequestParam Integer productId, @RequestParam Long userId) {
        Optional<Cart> existingCart = cartService.findCartByProductIdAndUserId(productId, userId);
        return existingCart.isPresent();
    }

    @GetMapping("/cart/{userId}")
    public List<Cart> getDetailsByUserId(@PathVariable Long userId) {
        return cartService.getDetailsByUserId(userId);
    }
    @GetMapping("/cart/count/{userId}")
    public int countProductsByUserId(@PathVariable Long userId) {
        return cartService.countProductsByUserId(userId);
    }
    @DeleteMapping("/cart/{productId}/{userId}")
    public void deleteCartItem(@PathVariable Integer productId, @PathVariable Long userId) {
        cartService.deleteCartItem(productId, userId);
    }
}
