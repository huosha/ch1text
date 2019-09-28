package com.bee.sample.ch1.controller;

import com.bee.sample.ch1.config.Function;
import com.bee.sample.ch1.model.Student;
import com.bee.sample.ch1.until.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloworldController {
    @RequestMapping("/say.html")
    public @ResponseBody
    String say() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello Spring Boot";
    }

    @RequestMapping("/sayhello.html")
    public @ResponseBody
    String say(String name) {
        return "hello " + name;
    }

    @RequestMapping("/adduser.html")
    @Function("user.add")
    public @ResponseBody
    String addUser(String name) {
        return "addUser " + name;
    }

    @RequestMapping("/check")
    public @ResponseBody
    List<Student> checkList() {
        return JsonUtil.checkResult();
    }
}
