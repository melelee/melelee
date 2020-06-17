package com.melelee.melelee.demo.thread;

/**
 * Join测试
 *
 * @author mengll
 * @create 2019-03-12 16:40
 **/
public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(10000);
				System.out.println("thread end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		thread.setPriority(Thread.MAX_PRIORITY);

		thread.start();
		thread.join();
		System.out.println("main end");
	}
}
