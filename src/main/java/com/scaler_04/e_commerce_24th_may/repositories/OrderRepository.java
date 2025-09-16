package com.scaler_04.e_commerce_24th_may.repositories;

import com.scaler_04.e_commerce_24th_may.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmailOrderByCreatedAtDesc(String userEmail);
}


