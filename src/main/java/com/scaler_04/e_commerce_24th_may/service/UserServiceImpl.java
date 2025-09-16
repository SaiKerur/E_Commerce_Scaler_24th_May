package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.AuthDtos;
import com.scaler_04.e_commerce_24th_may.model.User;
import com.scaler_04.e_commerce_24th_may.repositories.UserRepository;
import com.scaler_04.e_commerce_24th_may.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public User register(AuthDtos.RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        User saved = userRepository.save(user);
        try {
            kafkaTemplate.send("user.events", "user_registered", saved.getEmail());
        } catch (Exception ignored) {}
        return saved;
    }

    @Override
    public String login(AuthDtos.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getEmail(), Map.of("role", user.getRole().name()));
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User u) {
            return userRepository.findByEmail(u.getUsername()).orElse(null);
        }
        return null;
    }

    @Override
    public void requestPasswordReset(String email) {
        // In real implementation, generate token and send email via Kafka/Notification
        kafkaTemplate.send("notification.events", "password_reset_requested", email);
    }

    @Override
    public void updateProfile(User updated) {
        User current = getCurrentUser();
        if (current == null) {
            throw new IllegalStateException("Not authenticated");
        }
        current.setFullName(updated.getFullName());
        current.setPhoneNumber(updated.getPhoneNumber());
        current.setAddress(updated.getAddress());
        userRepository.save(current);
    }
}


