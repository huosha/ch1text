package com.bee.sample.ch1.controller;

import com.bee.sample.ch1.model.Book;
import com.bee.sample.ch1.until.SortCompartor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SortController {

    @RequestMapping("/sort")
    public String sortBook(int so) {
        List<Book> bookList1 = new ArrayList<>();
        String name1 = "语文";
        createBook(bookList1, name1);
        String name2 = "数学";
        createBook(bookList1, name2);
        SortCompartor sc = new SortCompartor();
        if (so == 1) {
            bookList1.sort(sc);
        } else {
            bookList1.sort(sc);
            Collections.reverse(bookList1); //反转整个数组
        }
        return bookList1.toString();
    }

    private void createBook(List<Book> books, String name) {
        Integer id = 1;
        Date time = new Date();
        String version = "1.0";
        Calendar cal = Calendar.getInstance();
        books.add(new Book(name, id, time, version));
        for (int i = 1; i < 20; i++) {
            int addDay = 1;
            cal.add(Calendar.DATE, addDay);
            books.add(new Book(name, id, cal.getTime(), version));
        }
    }
}
