package com.scaler_04.e_commerce_24th_may.payment;

import com.scaler_04.e_commerce_24th_may.model.Order;
import com.scaler_04.e_commerce_24th_may.model.OrderStatus;
import com.scaler_04.e_commerce_24th_may.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Simulate payment processing upon order creation event
    @KafkaListener(topics = "order.events", groupId = "ecommerce-group")
    public void onOrderEvent(String message) {
        // message key pattern: order_created:orderId
        if (message == null) return;
        if (message.contains("order_created")) {
            String orderId = message.substring(message.lastIndexOf(':') + 1).trim();
            try {
                Long id = Long.parseLong(orderId);
                Order order = orderRepository.findById(id).orElse(null);
                if (order != null) {
                    order.setStatus(OrderStatus.PAID);
                    orderRepository.save(order);
                    kafkaTemplate.send("payment.events", "payment_success:" + id);
                    kafkaTemplate.send("notification.events", "order_paid:" + order.getUserEmail());
                }
            } catch (Exception ignored) {}
        }
    }
}


