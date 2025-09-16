package com.scaler_04.e_commerce_24th_may.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItem {
    private Long productId;
    private String title;
    private Double price;
    private Integer quantity;
}


