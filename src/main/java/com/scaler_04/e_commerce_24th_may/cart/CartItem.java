package com.scaler_04.e_commerce_24th_may.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String title;
    private Double price;
    private Integer quantity;
}


