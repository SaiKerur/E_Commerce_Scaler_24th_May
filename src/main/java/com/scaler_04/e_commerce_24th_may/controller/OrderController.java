package com.scaler_04.e_commerce_24th_may.controller;

import com.scaler_04.e_commerce_24th_may.dto.OrderDtos;
import com.scaler_04.e_commerce_24th_may.model.Order;
import com.scaler_04.e_commerce_24th_may.service.OrderService;
import com.scaler_04.e_commerce_24th_may.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    private String email() {
        var u = userService.getCurrentUser();
        if (u == null) throw new IllegalStateException("Not authenticated");
        return u.getEmail();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDtos.CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(email(), request));
    }

    @GetMapping
    public List<Order> mine() {
        return orderService.getMyOrders(email());
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return orderService.getOrder(id, email());
    }
}


