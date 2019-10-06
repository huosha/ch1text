package com.bee.sample.ch1.thread;

/**
 * 实现Runnable接口的大厅叫号机
 *
 * @author jiangcan on 2019/10/6
 */
public class TicketWindowRunnable implements Runnable {

    // 最多受理50笔业务
    private static final int MAX = 50;
    private static int index = 1;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnable task = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(task, "一号出号机");
        Thread windowThread2 = new Thread(task, "二号出号机");
        Thread windowThread3 = new Thread(task, "三号出号机");
        Thread windowThread4 = new Thread(task, "四号出号机");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}
