package com.scaler_04.e_commerce_24th_may.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order extends BaseModel {
    private String userEmail;
    @ElementCollection
    private List<OrderItem> items = new ArrayList<>();
    private Double totalAmount;
    private String deliveryAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
}


