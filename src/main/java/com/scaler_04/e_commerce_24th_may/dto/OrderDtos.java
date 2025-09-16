package com.scaler_04.e_commerce_24th_may.dto;

import com.scaler_04.e_commerce_24th_may.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtos {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderRequest {
        private List<OrderItem> items;
        private String deliveryAddress;
    }
}


