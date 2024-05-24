package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
}
