package com.bee.sample.ch1.thread;

import java.util.concurrent.TimeUnit;

public class RepeatedStartThread {

    public static void main(String[] args) {
        repeatedStartTest1();
        repeatedStartTest2();

    }

    /**
     * 重复启动报错
     */
    private static void repeatedStartTest1() {
        // 写法1 继承了Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 启动线程
        thread.start();
        // 再次启动线程
        thread.start();
        // 写法2 java8 Lambda 实现了Runnable接口
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }

    /**
     * 已经终止（Terminated）的线程，再次启动报错
     */
    private static void repeatedStartTest2() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 启动线程
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次启动线程
        thread.start();
    }

}
