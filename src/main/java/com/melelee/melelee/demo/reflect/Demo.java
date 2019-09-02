package com.melelee.melelee.demo.reflect;

/**
 * 反射测试
 *
 * @author mengll
 * @create 2019-03-21 9:11
 **/
public class Demo {
	private String name = "qmengll";

	public String getName() {
		return name;
	}

	public void setName(String name,int age) {
		System.out.println(name);
		System.out.println(age);
	}
}
