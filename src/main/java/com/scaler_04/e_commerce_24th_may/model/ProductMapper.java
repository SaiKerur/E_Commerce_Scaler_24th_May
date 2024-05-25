package com.scaler_04.e_commerce_24th_may.model;

import com.scaler_04.e_commerce_24th_may.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setCategoryName(product.getCategory().getTitle());
        }
        return productDTO;
    }

    public static Product toProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        product.setCategory(category);
        return product;
    }
}

