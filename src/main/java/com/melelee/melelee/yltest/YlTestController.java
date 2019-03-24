package com.melelee.melelee.yltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YlTestController {

	@Autowired
	private YlTestService ylTestService;

	@GetMapping("/getToken")
	public String getToken(){
		return ylTestService.getToken();
	}
}
