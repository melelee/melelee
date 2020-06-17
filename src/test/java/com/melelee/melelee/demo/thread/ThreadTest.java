package com.melelee.melelee.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 *
 * @author mengll
 * @create 2019-05-14 19:37
 **/
@Slf4j
public class ThreadTest {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int a = atomicInteger.incrementAndGet();
        System.out.println(a);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 7; i++) {
            threadPoolExecutor.execute(new MyThread());
        }
        threadPoolExecutor.shutdown();
        System.out.println("asd");
    }
}
@Slf4j
class MyThread extends Thread {

    @Override
    public void run() {
       log.info("sdf");
    }
}
