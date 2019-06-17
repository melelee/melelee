package com.melelee.melelee.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程池测试
 *
 * @author mengll
 * @create 2019-06-04 11:33
 **/
public class ThreadPoolsTest {
    public static void main(String[] args) {

        //JDK8 七种
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newWorkStealingPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
    }
}
