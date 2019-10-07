package com.bee.sample.ch1.thread;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取当前线程
 *
 * @author jiangcan on 2019/10/7
 */
public class CurrentThread {
    public static void main(String[] args) {
      Thread thread =  new Thread(){
            @Override
            public void run() {
                // always true
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();
        String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));
        System.out.println(JSONObject.toJSONString(thread.getContextClassLoader()));
    }
}
