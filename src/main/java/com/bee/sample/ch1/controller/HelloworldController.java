package com.bee.sample.ch1.controller;

import com.bee.sample.ch1.config.Function;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloworldController {
    @RequestMapping("/say.html")
    public @ResponseBody
    String say() {
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
}
