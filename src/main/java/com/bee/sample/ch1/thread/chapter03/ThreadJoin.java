package com.bee.sample.ch1.thread.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * 多线程join方法
 * (A线程调用join方法会使当前线程进入BLOCKED，知道A线程结束生命周期，或者到达给定的时间)
 *
 * @author jiangcan on 2019/10/9
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        // 1.定义2个线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(toList());
        // 2.启动这2个线程
        threads.forEach(Thread::start);
        // 3.执行这2个线程的join方法
        for (Thread thread : threads) {
            thread.join();
        }
        // 4.main线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
        // 5.已经结束的线程，在调用它的join方法时，当前线程不会被阻塞
        for (Thread thread : threads) {
            thread.join();
        }
    }

    /**
     * 构造一个简单的线程，每个线程只是简单的循环输出
     *
     * @param arg 线程名
     * @return
     */
    private static Thread create(int arg) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(arg));
    }

    /**
     * 线程休眠一秒
     */
    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
