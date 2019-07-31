package com.bee.sample.ch1.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private String name;
    private Integer id;
    private Date time;
    private String version;

    public Book() {
    }

    public Book(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public Book(String name, Integer id, Date time, String version) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return "Book{" +
                "name='" + name + '\'' +
                ", id=" + id +
               // ", time=" + sdf.format(time) +
                ", version='" + version + '\'' +
                '}';
    }



}
