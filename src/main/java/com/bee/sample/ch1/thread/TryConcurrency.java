package com.bee.sample.ch1.thread;

import java.util.concurrent.TimeUnit;

/**
 * 2019-09-30
 */
public class TryConcurrency {

    public static void main(String[] args) {
//        browseNews();
//        enjoyMusic();
        // 写法1
        new Thread() {
            @Override
            public void run() {
                enjoyMusic();
            }
        }.start();
        // 写法2 java8 Lambda
        new Thread(TryConcurrency::enjoyMusic).start();
        browseNews();

    }

    /**
     * Browse the latest news.
     */
    private static void browseNews() {
        for (; ; ) {
            System.out.println("Uh-huh, the good news.");
            sleep(1);
        }
    }

    /**
     * Listening and enjoy the Music.
     */
    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("Uh-huh, the nice music.");
            sleep(1);
        }
    }

    /**
     * Simulate the wait and ignore exception.
     *
     * @param seconds
     */
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
