package com.scaler_04.e_commerce_24th_may.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "carts")
public class CartDocument {
    @Id
    private String id; // user email as id for simplicity
    private List<CartItem> items = new ArrayList<>();
}


