package com.bee.sample.ch1.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import java.util.Objects;

@JSONType(orders = {"id", "name", "sex", "imagePath"})// 转FastJson时按照此顺序排序，默认按照字母排序
public class Student {
    private String name;
    private String id;
    private int sex;
    @JSONField(serialize = false)// 转FastJson时忽略此参数
    private String imagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex=" + sex +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sex == student.sex &&
                name.equals(student.name) &&
                id.equals(student.id) &&
                imagePath.equals(student.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, sex, imagePath);
    }
}
