package com.melelee.melelee.controller;


import com.melelee.melelee.entity.User;
import com.melelee.melelee.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author melelee
 * @since 2019-03-27
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "/save")
    public String save(HttpServletRequest httpServletRequest, @RequestBody User user) {
        boolean b = iUserService.save(user);
        log.info("b {}", b);
        System.out.println(1 / 0);
        return "success";
    }
    @GetMapping(value = "/test")
    public String save(HttpServletRequest httpServletRequest) {
        log.info("seseeion id {}", httpServletRequest.getSession().getId());
        log.info("seseeion id {}", httpServletRequest.getSession(false).getId());
        log.info("seseeion id {}", httpServletRequest.getSession(true).getId());
        log.info("isRequestedSessionIdValid {}", httpServletRequest.isRequestedSessionIdValid());
        log.info("getRealPath {}", httpServletRequest.getServletContext().getRealPath(File.separator));
        log.info("getRealPath {}", httpServletRequest.getServletContext().getRealPath("|"));

        return "success";
    }
}
