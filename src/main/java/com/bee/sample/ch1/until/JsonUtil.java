package com.bee.sample.ch1.until;

import com.bee.sample.ch1.model.Student;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static List<Student> checkResult() {
        List<Student> list = new ArrayList<>();
        String name = "123";
        String id = "1";
        Integer sex = 1;
        list.add(new Student(name, id, sex));
        list.add(new Student());
        list.add(new Student());
        list.add(new Student());
        return check(list);
        //return list;
    }

    private static List<Student> check(List<Student> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) == null || list.get(i).getId() == null) {
                    list.remove(i);
                }
            }
            if (list.size() == 0) {
                return null;
            }
        }
        return list;
    }
}
