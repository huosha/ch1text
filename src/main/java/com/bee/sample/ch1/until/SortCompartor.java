package com.bee.sample.ch1.until;


import com.bee.sample.ch1.model.Book;

import java.util.Comparator;

public class SortCompartor implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        //if (book1.getVersion().equals(book2.getVersion())) {
        if (book1.getTime() == null && book2.getTime() == null) {
            return 0;
        }
        if (book1.getTime() == null) {
            return -1;
        }
        if (book2.getTime() == null) {
            return 1;
        }
        if (book1.getTime().before(book2.getTime())) {
            return -1;
        }
        if (book1.getTime().equals(book2.getTime())) {
            return 0;
        }
        if (book1.getTime().after(book2.getTime())) {
            return 1;
        }
        return 0;
    }

}
