package com.bee.sample.ch1.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bee.sample.ch1.i18n.Errors;
import com.bee.sample.ch1.model.User;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/query")
    public ResultInfo query(HttpServletRequest request, User user) {
        Map<String, String[]> paramsArr = request.getParameterMap();
        Map<String, Object> params = convertParamsArr(paramsArr);
        String sign = MD5Utils.creatSign(params);
        String error = request.getHeader("error");
        if ("error".equals(error)) {
            throw Errors.SYS_ERROR.getException();
        }
        log.info("DemoController query..sign{},params{},user{}", sign, params, user);
        return new ResultInfo();
    }

    private Map<String, Object> convertParamsArr(Map<String, String[]> paramsArr) {
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : paramsArr.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            if (values.length == 1) {
                params.put(name, values[0]);
            } else if (values.length > 1) {
                params.put(name, values);
            } else {
                params.put(name, "");
            }
        }
        return params;
    }

    @PostMapping("/clear")
    public ResultInfo clear(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        String paramStr = IOUtils.toString(request.getInputStream(), "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(paramStr);
        String sign = MD5Utils.creatSign(jsonObject);
        log.info("DemoController clear.sign{},params{},user{}", sign, jsonObject, user);
        return new ResultInfo();
    }

}

