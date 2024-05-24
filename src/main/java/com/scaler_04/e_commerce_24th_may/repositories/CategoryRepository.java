package com.scaler_04.e_commerce_24th_may.repositories;

import com.scaler_04.e_commerce_24th_may.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
