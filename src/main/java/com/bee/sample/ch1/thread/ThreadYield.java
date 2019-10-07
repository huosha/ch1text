package com.bee.sample.ch1.thread;

import java.util.stream.IntStream;

/**
 * 多线程的yield（让路,暂停）
 *
 * @author jiangcan on 2019/10/7
 */
public class ThreadYield {

    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYield::create).forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
            // 暂停当前正在执行的线程对象，并执行其他线程
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
