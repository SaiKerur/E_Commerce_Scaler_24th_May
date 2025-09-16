package com.scaler_04.e_commerce_24th_may.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    @KafkaListener(topics = "notification.events", groupId = "ecommerce-group")
    public void onNotification(String message) {
        // In real life, integrate with SES/SMS providers.
        log.info("Notification event: {}", message);
    }
}


