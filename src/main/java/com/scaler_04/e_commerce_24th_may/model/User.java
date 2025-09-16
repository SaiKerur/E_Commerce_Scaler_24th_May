package com.scaler_04.e_commerce_24th_may.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseModel {
    @Column(unique = true, nullable = false)
    private String email;
    private String passwordHash;
    private String fullName;
    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CUSTOMER;
}


