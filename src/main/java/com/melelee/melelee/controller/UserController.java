package com.melelee.melelee.controller;


import com.melelee.melelee.entity.User;
import com.melelee.melelee.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
	public String save(@RequestBody User user) {
		boolean b = iUserService.save(user);
		log.info("b {}", b);
		System.out.println(1 / 0);
		return "success";
	}
}
