package com.melelee.melelee.thread;

import java.util.Date;

/**
 * 线程学习
 *
 * @author mengll
 * @create 2019-01-23 9:30
 **/
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

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new ThreadDemo());
		thread.start();
		Thread.sleep(10000);
		thread.interrupt();
	}
}
