package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<CartEntity, Integer> {
    @Query("select c from CartEntity c where c.customerId = :customerId and c.status <> :status")
    List<CartEntity> findAllCartsByCustomerWithStatusActive(String customerId, int status);
}
