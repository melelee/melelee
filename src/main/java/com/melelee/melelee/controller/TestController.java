package com.melelee.melelee.controller;

import com.melelee.melelee.controller.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by melelee
 * @date 2020/6/17 13:52
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public Result<Object> test() {
        return Result.success();
    }
}
