package com.bee.sample.ch1.thread;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断，打断
 *
 * @author jiangcan on 2019/10/7
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh, i am be interruptes.");
                e.printStackTrace();
            }
        });
        thread.start();
        // short block and make sure thread is started
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();

    }
}
