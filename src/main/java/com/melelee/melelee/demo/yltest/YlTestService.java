package com.melelee.melelee.demo.yltest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Slf4j
@Service
public class YlTestService {

	private static final String CLIENT_ID_NAME = "client_id";
	private static final String GRANT_TYPE_NAME = "grant_type";
	private static final String CLIENT_SECRET_NAME = "client_secret";
	private static final String ACCESS_TOKEN_NAME = "access_token";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${yltest.token.user}")
	private String tokenUser;
	@Value("${yltest.token.password}")
	private String tokenPassword;
	@Value("${yltest.token.url}")
	private String url;
	@Value("${yltest.token.granttype}")
	private String grantType;

	public String getToken() {
		log.info("token user {},token password {}", tokenUser, tokenPassword);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(CLIENT_ID_NAME, tokenUser);
		map.add(GRANT_TYPE_NAME, grantType);
		map.add(CLIENT_SECRET_NAME, tokenPassword);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(url, request, JSONObject.class);
		if (jsonObjectResponseEntity != null
				&& jsonObjectResponseEntity.getBody() != null
				&& jsonObjectResponseEntity.getBody().get(ACCESS_TOKEN_NAME) != null) {
			return jsonObjectResponseEntity.getBody().getString(ACCESS_TOKEN_NAME);
		} else {
			return null;
		}
	}

	@Value("${url.getbusinessid}")
	private String urlgetbusinessid;

	public String getBusinessId(String loginUserName) {

		String token = "&access_token=";
		String url = urlgetbusinessid + loginUserName + token + getToken();
		JSONObject jsonObject = restTemplate.postForObject(url, null, JSONObject.class);
		return jsonObject.getString("rspData");
	}


	@Value("${url.uploadfile}")
	private String urluploadfile;
	public JSONObject uploadFile(String loginUserName, String filePath) {
		String token = "&access_token=" + getToken();
		String businessId = "?businessId=" + getBusinessId(loginUserName);

		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		File file = new File(filePath);
		if (file.isDirectory()){
			for (File tmp : file.listFiles()) {
				FileSystemResource resource = new FileSystemResource(tmp);
				param.add("file", resource);
			}
		}else if (file.isFile()){
			FileSystemResource resource = new FileSystemResource(file);
			param.add("file", resource);
		}
		String postFileUrl = urluploadfile + businessId + token;
		JSONObject jsonObject = restTemplate.postForObject(postFileUrl, param, JSONObject.class);

		return jsonObject;

	}

	@Value("${dcm.path}")
	private String dcmPath;
	@Scheduled(fixedRate = 10000)
	public void dcmPath() {
		JSONObject jsonObject = this.uploadFile("yltest", dcmPath);

		log.info("单个文件上传：{}", jsonObject);

	}

	@Value("${dcm.dir}")
	private String dcmDir;
	@Scheduled(fixedRate = 14000)
	public void dcmDir() {
		JSONObject jsonObject = this.uploadFile("yltest", dcmPath);

		log.info("批量文件上传：{}", jsonObject);

	}
}
