package com.scaler_04.e_commerce_24th_may.controller;

import com.scaler_04.e_commerce_24th_may.cart.CartDocument;
import com.scaler_04.e_commerce_24th_may.cart.CartItem;
import com.scaler_04.e_commerce_24th_may.cart.CartService;
import com.scaler_04.e_commerce_24th_may.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    private String currentEmail() {
        var u = userService.getCurrentUser();
        if (u == null) throw new IllegalStateException("Not authenticated");
        return u.getEmail();
    }

    @GetMapping
    public CartDocument getCart() {
        return cartService.getCart(currentEmail());
    }

    @PostMapping("/items")
    public CartDocument addItem(@RequestBody CartItem item) {
        return cartService.addItem(currentEmail(), item);
    }

    @PutMapping("/items")
    public CartDocument updateItem(@RequestBody CartItem item) {
        return cartService.updateItem(currentEmail(), item);
    }

    @DeleteMapping("/items/{productId}")
    public CartDocument removeItem(@PathVariable Long productId) {
        return cartService.removeItem(currentEmail(), productId);
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        cartService.clear(currentEmail());
        return ResponseEntity.noContent().build();
    }
}


