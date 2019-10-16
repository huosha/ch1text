package com.bee.sample.ch1.thread.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * 事件队列测试
 *
 * @author jiangcan on 2019/10/15
 */
public class EventClient {

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
//        final EventQueue eventQueue1 = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Produce").start();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}
