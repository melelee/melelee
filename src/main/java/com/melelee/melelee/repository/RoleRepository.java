package com.melelee.melelee.repository;

import com.melelee.melelee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Role repository.
 *
 * @author by melelee
 * @date 2020 /6/17 18:21
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
