package com.melelee.melelee.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射测试
 *
 * @author mengll
 * @create 2019-03-21 9:11
 **/
public class ReflectTest {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Demo demo = new Demo();

		Field field = demo.getClass().getDeclaredField("name");
		field.setAccessible(true);
		field.set(demo, "sdf");
		System.out.println(demo.getName());
		Class[] classTypes = {String.class, int.class};
		Method method = demo.getClass().getMethod("setName", classTypes);


		method.invoke(demo, "sdf", 1);
	}
}
