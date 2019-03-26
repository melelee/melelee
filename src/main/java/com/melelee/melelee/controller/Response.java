package com.melelee.melelee.controller;

import lombok.Data;

@Data
public class Response<T> {
	private String code;
	private String msg;
	private T data;
}
