package com.melelee.melelee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mengll
 * @create 2018-12-17 9:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HfghwApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@WebAppConfiguration
public class HfghwApplicationTests {

	@Before
	public void init() {
		System.out.println("开始测试-----------------");
	}

	@Test
	public void test(){
		System.out.println("test.........");
	}
	@After
	public void after() {
		System.out.println("测试结束-----------------");
	}
}