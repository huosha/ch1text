package com.bee.sample.ch1.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@RestController
@Slf4j
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/query")
    public ResultInfo query() {
        log.info("DemoController query.");
        return new ResultInfo();
    }

    @PostMapping("/clear")
    public ResultInfo clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paramStr = IOUtils.toString(request.getInputStream(), "UTF-8");
        Map<String, String[]> paramsArr = request.getParameterMap();
        log.info("DemoController clear.{}", paramStr);
        return new ResultInfo();
    }

}

