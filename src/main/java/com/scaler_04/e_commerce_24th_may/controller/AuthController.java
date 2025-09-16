package com.scaler_04.e_commerce_24th_may.controller;

import com.scaler_04.e_commerce_24th_may.dto.AuthDtos;
import com.scaler_04.e_commerce_24th_may.model.User;
import com.scaler_04.e_commerce_24th_may.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody AuthDtos.RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDtos.AuthResponse> login(@RequestBody AuthDtos.LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(new AuthDtos.AuthResponse(token));
    }

    @PostMapping("/password/reset")
    public ResponseEntity<Void> requestPasswordReset(@RequestParam String email) {
        userService.requestPasswordReset(email);
        return ResponseEntity.ok().build();
    }
}


