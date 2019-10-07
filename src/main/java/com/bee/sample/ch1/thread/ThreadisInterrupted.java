package com.bee.sample.ch1.thread;

import java.util.concurrent.TimeUnit;

/**
 * 判断当前线程是否被中断（isInterrupted不会擦除中断标识flag）
 *
 * @author jiangcan on 2019/10/7
 */
public class ThreadisInterrupted {

    /**
     * 获取线程的中断状态
     *
     * @param args
     * @throws InterruptedException
     */
    public static void getIsInterrupted(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // do nothing, just empty loop.
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %b\n", thread.isInterrupted());
//        System.out.println(String.format("Thread is interrupted ? %b", thread.isInterrupted()));
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        // ignore the exception
                        // here the interrupt flag will be clear.
                        System.out.printf("I am be interrupted ? %s\n", isInterrupted());
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
    }

}
