package com.bee.sample.ch1.controller;

import com.bee.sample.ch1.service.TaskService;
import com.bee.sample.ch1.service.impl.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class TaskController {
    private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskService taskService;

    private final ThreadPoolTaskExecutor  taskExecutor;

    public TaskController(TaskService taskService, ThreadPoolTaskExecutor taskExecutor) {
        this.taskService = taskService;
        this.taskExecutor = taskExecutor;
    }

    @RequestMapping("/task")
    public String updateBooking(
            @RequestParam("list") List<String> list) {
        log.info("start!!!!!!!!!!!");
        for (String cityCode : list) {
            taskService.updateBookingStatusByCity(cityCode);
        }
        log.info("end!!!!!!!!!!!");
        return "IS OK !";
    }

    @RequestMapping("/task3")
    public String task(
            @RequestParam("list") List<String> list) {
        log.info("start!!!!!!!!!!!");
        for (String cityCode : list) {
            taskExecutor.submit(() -> taskService.update(cityCode));

        }
        log.info("end!!!!!!!!!!!");
        return "IS OK !";
    }

    @RequestMapping("/task2")
    public String updateBookingStatus(
            @RequestParam("list") List<String> list) {
        log.info("start!!!!!!!!!!!");
        int num = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(num);

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < 2)
                list1.add(list.get(i));
            if (i >= 2 && i < 4)
                list2.add(list.get(i));
            if (i >= 4)
                list3.add(list.get(i));
        }
        Future<Object> f1 = executorService.submit(new Task<>(list1));
        Future<Object> f2 = executorService.submit(new Task<>(list2));
        Future f3 = executorService.submit(new Task<>(list3));
        while (true) {
            if (f1.isDone() && f2.isDone() && f3.isDone()) break;
        }
        String result = "";
        try {
            result = String.format("%s%s%s", f1.get(), f2.get(), f3.get());
        } catch (Exception e) {
            e.printStackTrace();
            executorService.shutdown();
        }
        log.info("end!!!!!!!!!!!");
        return result;
    }

    private  String update(List<String> list) {
        for (String cityCode : list) {
            taskService.update(cityCode);
        }
        return "ok";
    }


    class Task<T> implements Callable<T> {
        private List<String> cityCode;

        Task(List<String> cityCode) {
            this.cityCode = cityCode;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T call() {
            return (T) update(cityCode);
        }

    }

}
