package com.bee.sample.ch1.thread;

/**
 * 大厅里的出号机器
 *
 * @author jiangcan on 2019/10/6
 */
public class TicketWindow extends Thread {
    // 柜台名称
    private final String name;

    // 最多受理50笔业务
    private static final int MAX = 50;
    private static int index = 1;

    private static final Object MUTE = new Object();

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (MUTE) {
            while (index <= MAX) {
                System.out.println("柜台" + name + "当前的号码是：" + (index++));
            }
        }
//        while (index <= MAX) {
//            System.out.println("柜台" + name + "当前的号码是：" + (index++));
//        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();
        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();


    }
}
