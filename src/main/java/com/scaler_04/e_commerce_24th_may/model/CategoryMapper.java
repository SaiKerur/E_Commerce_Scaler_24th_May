package com.scaler_04.e_commerce_24th_may.model;

import com.scaler_04.e_commerce_24th_may.dto.CategoryDTO;
import com.scaler_04.e_commerce_24th_may.dto.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDTO toCategoryDTO(Category category) {
        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());

        return new CategoryDTO(
                category.getTitle(),
                productDTOs
        );
    }
}

