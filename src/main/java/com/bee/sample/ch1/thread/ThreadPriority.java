package com.bee.sample.ch1.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 *
 * @author jiangcan on 2019/10/7
 */
public class ThreadPriority {

    /**
     * 设置优先级
     *
     * @param args
     */
    public static void updatePriority(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("t1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setPriority(3);

        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("t2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.setPriority(10);
        t1.start();
        t2.start();
    }

    /**
     * 线程的优先级不能超过线程组的优先级
     *
     * @param args
     */
    public static void threadMaxPriority(String[] args) {
        //定义一个线程组
        ThreadGroup group = new ThreadGroup("test");
        //将线程组的优先级指定为7
        group.setMaxPriority(7);
        Thread thread = new Thread(group, "test-thread");
        //企图将线程的优先级设定为10
        thread.setPriority(10);
        //企图未遂
        System.out.println(thread.getPriority());
    }

    /**
     * 默认情况子线程的优先级与父线程的优先级一致，也就是5
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread();
        System.out.println("t1 priority " + t1.getPriority());
        System.out.println("t1 id " + t1.getId());
        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println("t3 priority " + t3.getPriority());
            System.out.println("t3 id " + t3.getId());
        });
        t2.setPriority(6);
        t2.start();
        System.out.println("t2 priority " + t2.getPriority());
        System.out.println("t2 id " + t2.getId());
    }

}
