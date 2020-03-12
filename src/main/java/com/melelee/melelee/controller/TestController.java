package com.melelee.melelee.controller;

import com.melelee.melelee.controller.bean.Response;
import com.melelee.melelee.entity.User;
import com.melelee.melelee.service.UserService;
import com.melelee.melelee.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 *
 * @author melelee
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "test")
    public Response saveUser() {
        log.info("test");
        return Response.successMessage("用户保存成功");
    }

}
