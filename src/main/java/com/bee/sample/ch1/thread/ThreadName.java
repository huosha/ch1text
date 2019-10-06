package com.bee.sample.ch1.thread;

import java.util.stream.IntStream;

/**
 * 线程默认的名称
 *
 * @author jiangcan on 2019/10/6
 */
public class ThreadName {

    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().map(integer -> new Thread(() -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);
    }

}
