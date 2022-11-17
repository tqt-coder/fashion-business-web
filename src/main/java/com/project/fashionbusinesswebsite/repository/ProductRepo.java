package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    Page<ProductEntity> getAllByProductTitleContains(String title, Pageable pageable);

    List<ProductEntity> getAllByProductTitleContains(String title);

    Page<ProductEntity> getAllByCategoryId(int categoryId, Pageable pageable);

    List<ProductEntity> getAllByCategoryId(int categoryId);
}
