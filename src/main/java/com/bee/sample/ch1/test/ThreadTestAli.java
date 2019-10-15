package com.bee.sample.ch1.test;

public class ThreadTestAli {

    static final Object str1 = new Object();

    private static int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (str1) {
                    if (count % 3 == 0) {
                        System.out.print("a");
                        count++;
                        if (count == 304) {
                            break;
                        }
                        str1.notifyAll();
                    } else {
                        try {
                            str1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (str1) {
                    if (count % 3 == 1) {
                        System.out.print("l");
                        count++;
                        str1.notifyAll();
                        if (count == 305) {
                            break;
                        }
                    } else {
                        try {
                            str1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (str1) {
                    if (count % 3 == 2) {
                        System.out.print("i,");
                        count++;
                        if (count == 306) {
                            break;
                        }
                        str1.notifyAll();
                    } else {
                        try {
                            str1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

    }
}

