package com.melelee.melelee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by melelee
 * @date 2020/6/20 15:43
 */
@Controller
@RequestMapping("/product")
public class ProductTestController {

    @RequestMapping("/info")
    @ResponseBody
    public String productInfo(){
        return " some product info ";
    }
}
