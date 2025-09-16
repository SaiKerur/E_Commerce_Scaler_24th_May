package com.scaler_04.e_commerce_24th_may.repositories;

import com.scaler_04.e_commerce_24th_may.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}


