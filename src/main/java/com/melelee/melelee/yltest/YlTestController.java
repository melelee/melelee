package com.melelee.melelee.yltest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YlTestController {

	@Autowired
	private YlTestService ylTestService;

	@GetMapping("/getToken")
	public String getToken() {
		return ylTestService.getToken();
	}


	@GetMapping("/getBusinessId/{loginUserName}")
	public String getBusinessId(@PathVariable String loginUserName) {
		return ylTestService.getBusinessId(loginUserName);
	}

	@GetMapping("/uploadFile/{loginUserName}")
	public JSONObject uploadFile(@PathVariable String loginUserName) {
		return ylTestService.uploadFile(loginUserName,
				"/data/saas/external/yltest/2019/1/1/5b3b7414-5f6c-4477-86fa-744425f172eb/1.2.840.113619.2.81.325.1.2900.20190101.250744/image/1.2.840.113619.2.81.325.1.2900.1.20190101.250744/dicom1.dcm");
	}
}
