package com.melelee.melelee.service;

import com.melelee.melelee.entity.User;
import com.melelee.melelee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type User service.
 *
 * @author melelee
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    public User save(User user) {
        return userRepository.save(user);
    }
}
