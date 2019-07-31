package com.bee.sample.ch1.test;

import com.bee.sample.ch1.model.Book;

import java.util.*;

public class TestMethod {

    public static void main(String[] args) {
        Book b1 = new Book("1","2019-05-10 10:22:00");
        Book b2 = new Book("2","2019-05-10 10:21:00");
        Book b3 = new Book("3","2019-05-10 10:20:00");
        Book b4 = new Book("3","2019-05-10 10:20:00");
        Book b5 = new Book("5","2019-05-10 10:20:00");
        List<Book> l1 = new ArrayList<>();
        l1.add(b1);
        l1.add(b2);
        l1.add(b3);
        System.out.println("l1" + l1);
        List<Book> l2 = new ArrayList<>();
        l2.add(b4);
        l2.add(b5);
        System.out.println("l2" + l2);
//        List<Book> l3 = new ArrayList<>();
//        l3.addAll(l1);
//        l3.addAll(l2);
//        System.out.println("l3" + l3);
//        l3 = new ArrayList<Book>(new LinkedHashSet<>(l3));
//        System.out.println("l3" + l3);
//
//        LinkedHashSet<Book> h = new LinkedHashSet<Book>();
//        h.addAll(l3);
//        System.out.println("h" + h);

        Map<String, Book> map1 = new HashMap<>();
        Map<String, Book> map2 = new HashMap<>();
        for(Book book: l1) {
            map1.put(book.getName(),book);
        }
        for(Book book: l2) {
            map2.put(book.getName(),book);
        }
        for(String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                l1.add(map2.get(key));
            }
        }
        System.out.println("l1" + l1);
    }
}
