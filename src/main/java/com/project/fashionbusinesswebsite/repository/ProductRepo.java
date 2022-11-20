package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    Page<ProductEntity> getAllByProductTitleContains(String title, Pageable pageable);

    List<ProductEntity> getAllByProductTitleContains(String title);

    @Query("select count(p.categoryId) from ProductEntity p where p.productTitle like %:title%")
    int getSizePageByProductTitle(String title);

    Page<ProductEntity> getAllByProductCategoryId(int pCategoryId, Pageable pageable);

}
