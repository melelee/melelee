package com.melelee.melelee.demo.thread.basic;

import java.util.Date;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + new Date().toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
                    break;
                }
            }
        });
        thread.start();

        Thread.sleep(5500);

        thread.interrupt();
        Thread.sleep(1000);

        System.out.println(thread.getName() + " out " + thread.getState());
    }
}
