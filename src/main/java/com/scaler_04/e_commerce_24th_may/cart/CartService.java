package com.scaler_04.e_commerce_24th_may.cart;

import java.util.Optional;

public interface CartService {
    CartDocument getCart(String userEmail);
    CartDocument addItem(String userEmail, CartItem item);
    CartDocument updateItem(String userEmail, CartItem item);
    CartDocument removeItem(String userEmail, Long productId);
    void clear(String userEmail);
}


