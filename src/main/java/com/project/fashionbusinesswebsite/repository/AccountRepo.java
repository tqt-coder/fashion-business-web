package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepo extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findCustomerEntityByCustomerEmail(String email);

    Optional<CustomerEntity> findCustomerEntityByUserName(String userName);

    Optional<CustomerEntity> findCustomerEntityByUserNameAndCustomerPass(String userName, String pass);

}
