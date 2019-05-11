package com.bee.sample.ch1.service.impl;

import com.bee.sample.ch1.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Async("serviceExecutor")
    @Override
    public void updateBookingStatusByCity(String cityCode) {
        log.info("task1" + cityCode);
    }

    @Override
    public void update(String cityCode) {
        log.info("task2" + cityCode);
    }
}
