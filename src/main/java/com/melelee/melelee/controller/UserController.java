package com.melelee.melelee.controller;

import com.melelee.melelee.controller.bean.Response;
import com.melelee.melelee.entity.User;
import com.melelee.melelee.service.UserService;
import com.melelee.melelee.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 *
 * @author melelee
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Save user long.
     *
     * @param user the user
     * @return the long
     */
    @PostMapping
    public Response saveUser(@RequestBody User user) {
        User u = userService.findByUsername(user.getUsername());
        if (u != null) {
            return Response.failure("用户名已存在");
        }
        String encryptedPassword = PasswordUtils.encryptPassword(user.getPassword(), user.getUsername());
        user.setPassword(encryptedPassword);
        userService.save(user);
        return Response.successMessage("用户保存成功");

    }

}
