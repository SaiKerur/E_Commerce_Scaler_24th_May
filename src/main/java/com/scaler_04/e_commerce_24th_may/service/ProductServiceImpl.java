package com.scaler_04.e_commerce_24th_may.service;

import com.scaler_04.e_commerce_24th_may.dto.ProductDTO;
import com.scaler_04.e_commerce_24th_may.model.Product;
import com.scaler_04.e_commerce_24th_may.model.ProductMapper;
import com.scaler_04.e_commerce_24th_may.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toProductDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getTitle(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getImageUrl(),
                null // Assuming you'll set the category separately
        );
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        product.setCategory(null); // Assuming you'll set the category separately
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
