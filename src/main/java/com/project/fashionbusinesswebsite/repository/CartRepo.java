package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartEntity, Integer> {

}
