package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepo extends JpaRepository<ProductCategoryEntity, Integer> {
}
