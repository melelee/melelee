package com.melelee.melelee.repository;

import com.melelee.melelee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author melelee
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    User findByUsername(String userName);

    /**
     * Find by user name or email user.
     *
     * @param username the username
     * @param email    the email
     * @return the user
     */
    User findByUsernameOrEmail(String username, String email);
}
