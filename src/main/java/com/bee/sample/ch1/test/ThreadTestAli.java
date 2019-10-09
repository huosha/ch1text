package com.bee.sample.ch1.test;

public class ThreadTestAli {
    public static void main(String[] args) {
        A a = new A();
        Thread threadA = new Thread(a);
        B b = new B();
        Thread threadB = new Thread(b);
        C c = new C();
        Thread threadC = new Thread(c);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class A implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 101; i++)
        System.out.print("a");
    }
}
class B implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            System.out.print("l");
        }

    }
}
class C implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 101; i++)
        System.out.print("i");
    }
}
