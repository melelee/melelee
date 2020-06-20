package com.melelee.melelee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by melelee
 * @date 2020/6/20 15:42
 */
@Controller
@RequestMapping("/admin")
public class AdminTestController {

    @RequestMapping("/home")
    @ResponseBody
    public String productInfo(){
        return " admin home page ";
    }
}
