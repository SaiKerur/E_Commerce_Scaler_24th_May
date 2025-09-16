package com.scaler_04.e_commerce_24th_may.controller;

import com.scaler_04.e_commerce_24th_may.model.User;
import com.scaler_04.e_commerce_24th_may.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> me() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody User updated) {
        userService.updateProfile(updated);
        return ResponseEntity.ok().build();
    }
}


