package com.melelee.melelee.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 线程学习
 *
 * @author mengll
 * @create 2019-01-23 9:30
 **/
@Slf4j
public class ThreadDemo implements Runnable {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getId() + new Date().toString());
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
				break;
			}
		}
	}

	public void testBreak() throws InterruptedException {
		Thread thread = new Thread(new ThreadDemo());
		thread.start();
		Thread.sleep(10000);
		thread.interrupt();
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadLocal threadLocal = new ThreadLocal();

		ThreadDemo threadDemo = new ThreadDemo();
		new Thread(() -> {
			try {
				threadDemo.testWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "wait线程").start();
		Thread.sleep(1000);
		new Thread(() -> {
			try {
				threadDemo.testNotify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "notify线程").start();

	}

	private List<String> strings = new ArrayList<>();

	public void testWait() throws InterruptedException {
		synchronized (strings) {
			log.info("进入wait线程{}", new Date());
			Thread.sleep(1000);
			log.info("wait线程释放锁，等待执行{}", new Date());
			strings.wait();
			log.info("wait线程继续执行{}", new Date());
			Thread.sleep(1000);
			log.info("wait线程执行结束{}", new Date());
		}
	}

	public void testNotify() throws InterruptedException {
		synchronized (strings) {

			while (true) {
				log.info("{}", new Date());
				log.info("{}", new Date());
				Thread.sleep(1000);
				strings.notify();
			}
		}
	}
}
