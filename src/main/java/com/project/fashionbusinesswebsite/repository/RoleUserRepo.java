package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.RoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleUserRepo extends JpaRepository<RoleUserEntity, Integer> {
    @Query("select roleId from RoleUserEntity where userId = :userId")
    List<Integer> findAllRoleIdByUserId(int userId);
}
