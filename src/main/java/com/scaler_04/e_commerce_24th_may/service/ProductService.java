package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}

