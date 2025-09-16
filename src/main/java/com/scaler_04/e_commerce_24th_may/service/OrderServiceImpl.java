package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.OrderDtos;
import com.scaler_04.e_commerce_24th_may.model.Order;
import com.scaler_04.e_commerce_24th_may.model.OrderStatus;
import com.scaler_04.e_commerce_24th_may.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Order createOrder(String userEmail, OrderDtos.CreateOrderRequest request) {
        Order order = new Order();
        order.setUserEmail(userEmail);
        order.setItems(request.getItems());
        double total = request.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        order.setTotalAmount(total);
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setStatus(OrderStatus.PENDING);
        Order saved = orderRepository.save(order);
        try {
            kafkaTemplate.send("order.events", "order_created:" + saved.getId());
        } catch (Exception ignored) {}
        return saved;
    }

    @Override
    public List<Order> getMyOrders(String userEmail) {
        return orderRepository.findByUserEmailOrderByCreatedAtDesc(userEmail);
    }

    @Override
    public Order getOrder(Long id, String userEmail) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (!order.getUserEmail().equals(userEmail)) {
            throw new IllegalStateException("Not allowed");
        }
        return order;
    }
}


