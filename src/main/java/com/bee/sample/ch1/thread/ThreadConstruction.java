package com.bee.sample.ch1.thread;

import java.util.stream.IntStream;

/**
 * 线程构造函数
 *
 * @author jiangcan on 2019/10/6
 */
public class ThreadConstruction {

//    private final static String PREFIX = "ALEX-";
//
//    public static void main(String[] args) {
//        IntStream.range(0, 5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);
//    }
//
//    private static Thread createThread(final int intName) {
//        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
//    }

    public static void main(String[] args) {
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("TestGroup");
        Thread t2 = new Thread(group,"t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main thread belong group:" + mainThreadGroup.getName());
        System.out.println("t1 and main belong the same group:" + (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 and main belong the same group:" + (mainThreadGroup == t2.getThreadGroup()));
        System.out.println("t2 thread group belong main TestGroup:" + (group == t2.getThreadGroup()));
    }

}
