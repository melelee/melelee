package com.melelee.melelee.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e){
		Response response = new Response();
		response.setCode("999999");
		response.setMsg(e.getMessage());
		return response;
	}
}
