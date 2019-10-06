package com.bee.sample.ch1.test;

import java.util.ArrayList;
import java.util.List;

public class listTest {
    public static void main(String[] args) {
        List<String> listA = new ArrayList<>();
        test1List(listA);
        System.out.println("listA======" + listA);
        List<String> listB = test2List(listA);
        System.out.println("listA======" + listA);
        System.out.println("listB======" + listB);
    }

    private static void test1List(List<String> list) {
        list.add("abc");
    }

    private static List<String> test2List(List<String> list) {
        list = new ArrayList<>();
        list.add("def");
        return list;
    }
}
