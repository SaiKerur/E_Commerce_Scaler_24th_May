package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.AuthDtos;
import com.scaler_04.e_commerce_24th_may.model.User;

public interface UserService {
    User register(AuthDtos.RegisterRequest request);
    String login(AuthDtos.LoginRequest request);
    User getCurrentUser();
    void requestPasswordReset(String email);
    void updateProfile(User updated);
}


