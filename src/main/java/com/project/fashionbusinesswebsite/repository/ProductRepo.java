package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}
