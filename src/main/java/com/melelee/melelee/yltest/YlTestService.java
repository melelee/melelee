package com.melelee.melelee.yltest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
}
