package com.melelee.melelee.reflect;

import java.lang.reflect.Field;

/**
 * 反射测试
 *
 * @author mengll
 * @create 2019-03-21 9:11
 **/
public class ReflectTest {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		Demo demo = new Demo();

		Field field
				= demo.getClass().getDeclaredField("name");
		field.setAccessible(true);
		field.set(demo, "sdf");
		System.out.println(demo.getName());

	}
}
