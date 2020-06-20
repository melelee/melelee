package com.melelee.melelee.repository;

import com.melelee.melelee.entity.User;
import com.melelee.melelee.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface User role repository.
 *
 * @author by melelee
 * @date 2020 /6/17 18:21
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    /**
     * Find by user id user role.
     *
     * @param userId the user name
     * @return the user role
     */
    List<UserRole> findByUserId(long userId);
}
