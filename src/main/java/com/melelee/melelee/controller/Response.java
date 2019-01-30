package com.melelee.melelee.controller;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Response<T> {
	private String code;
	private String msg;
	private T data;

	public static void main(String[] args) {
		Response response = new Response();
		response.setCode("asd");
		response.setMsg("asd");
		System.out.println(JSON.toJSONString(response));
	}
}
