package com.bee.sample.ch1.thread;

/**
 * 守护线程
 *
 * @author jiangcan on 2019/10/6
 */
public class DaemonThread {
    private static int i = 0;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 将thread设置为守护线程
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread finished lifecycle.");
    }
}
