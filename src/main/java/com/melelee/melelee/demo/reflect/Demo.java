package com.melelee.melelee.demo.reflect;

import lombok.extern.java.Log;
import lombok.val;

import java.util.HashSet;

/**
 * 反射测试
 *
 * @author mengll
 * @create 2019-03-21 9:11
 **/
@Log
public class Demo {
	private String name = "qmengll";

	public String getName() {
		return name;
	}

	public void setName(String name,int age) {
		System.out.println(name);
		System.out.println(age);
	}


	public static void main(String[] args) {
		val sets = new HashSet<String>();

	}
}
