package com.bee.sample.ch1.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程是否中断（interrupted会擦除中断标识flag）
 *
 * @author jiangcan on 2019/10/7
 */
public class Threadinterrupted {

    /**
     * 获取线程的是否中断标识flag（interrupted会擦除中断标识flag）
     *
     * @param args
     * @throws InterruptedException
     */
    public static void getInterrupted(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        // shortly block make sure the thread is started.
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }

    public static void main(String[] args) {
        // 1.判断当前线程是否被中断（interrupted会擦除中断标识flag）
        System.out.println("Main Thread is interrupted? " + Thread.interrupted());

        // 2.中断当前线程
        Thread.currentThread().interrupt();

        // 3.判断当前线程是否已经被中断（isInterrupted不会擦除中断标识flag）
        System.out.println("Main Thread is interrupted? " + Thread.currentThread().isInterrupted());

        try {
            // 4.当前线程执行可中断方法
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            // 5.捕获中断信号
            System.out.println("I will be interrupted still.");
            e.printStackTrace();
        }
    }
}
