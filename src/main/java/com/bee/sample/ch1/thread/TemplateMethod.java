package com.bee.sample.ch1.thread;

/**
 * 模板设计模式
 *
 * @author jiangcan
 */
public class TemplateMethod {
    /**
     * print类似于Thread的start方法
     * @param message 消息
     */
    public final void print(String message) {
        System.out.println("################");
        wrapPrint(message);
        System.out.println("################");
    }

    /**
     * wrapPrint类似于run方法
     * @param message 消息
     */
    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        t1.print("Hello Thread");
        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };
        t2.print("Hello Thread");
    }
}