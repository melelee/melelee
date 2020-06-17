package com.melelee.melelee.demo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * synchronized测试
 *
 * @author mengll
 * @create 2019-06-04 11:05
 **/
@Slf4j
public class SynchronizedTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }


    public static void test3(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SynchronizedTest.method3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronizedTest.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void test2(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SynchronizedTest.method3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SynchronizedTest.method4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void test1(){
        SynchronizedTest synchronizedTest = new SynchronizedTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronizedTest.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronizedTest.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public synchronized void method1() throws InterruptedException {
        log.info("method1 start");
        Thread.sleep(10000);
        log.info("method1 end");
    }

    public synchronized void method2() throws InterruptedException {
        log.info("method2 start");
        Thread.sleep(10000);
        log.info("method2 end");
    }
    public synchronized static void method3() throws InterruptedException {
        log.info("method3 start");
        Thread.sleep(10000);
        log.info("method3 end");
    }
    public synchronized static void method4() throws InterruptedException {
        log.info("method4 start");
        Thread.sleep(10000);
        log.info("method4 end");
    }

}
