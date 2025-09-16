package com.scaler_04.e_commerce_24th_may.cart;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<CartDocument, String> {
}


