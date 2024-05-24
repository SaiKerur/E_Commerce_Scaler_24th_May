package com.scaler_04.e_commerce_24th_may.model;

import com.scaler_04.e_commerce_24th_may.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getCategory() != null ? product.getCategory().getTitle() : null // assuming Category has a getName method
        );
    }
}
