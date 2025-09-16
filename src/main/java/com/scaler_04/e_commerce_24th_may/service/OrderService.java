package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.OrderDtos;
import com.scaler_04.e_commerce_24th_may.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(String userEmail, OrderDtos.CreateOrderRequest request);
    List<Order> getMyOrders(String userEmail);
    Order getOrder(Long id, String userEmail);
}


