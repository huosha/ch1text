package com.bee.sample.ch1.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
/**
 * 拦截器 验证签名 目前只支持GET/POST请求
 *
 * @author zangdaiyang
 */
@Component
@Slf4j
public class SignAuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SignAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 1、获取请求的参数
        Map<String, String> params;
        String method = request.getMethod();
         if (Constants.HTTP_GET.equalsIgnoreCase(method)) {
            Map<String, String[]> paramsArr = request.getParameterMap();
            if (CollectionUtils.isEmpty(paramsArr)) {
                log.warn("Request for get method, param is empty, signature verification failed.");
                return false;
            }
            params = convertParamsArr(paramsArr);
        } else if (Constants.HTTP_POST.equalsIgnoreCase(method)) {
            // 此处读取了request中的inputStream，因为只能被读取一次，后面spring框架无法读取了，所以需要添加wrapper和filter解决流只能读取一次的问题
//            BufferedReader reader = request.getReader();
//            if (reader == null) {
//                log.warn("Request for post method, body is empty, signature verification failed.");
//                return false;
//            }
//            params = new Gson().fromJson(reader, Map.class);
            String paramStr = IOUtils.toString(request.getInputStream(), "UTF-8");
            if (!StringUtils.isEmpty(paramStr)) {
                params = JSONObject.parseObject(paramStr, new TypeReference<Map<String, String>>() {});
            } else {
                params = null;
            }
//            Enumeration headerNames = request.getHeaderNames();
//            //使用循环遍历请求头，并通过getHeader()方法获取一个指定名称的头字段
//            Map<String, String> heards = new HashMap<>();
//            String headerName = (String) headerNames.nextElement();
//            heards.put(headerName, request.getHeader(headerName));
//            params.putAll(heards);
             params.put("v", request.getHeader("v"));
        } else {
            log.warn("Not supporting non-get or non-post requests, signature verification failed.");
            return false;
        }

        // 2、验证签名是否匹配
        boolean checkSign = params != null && params.getOrDefault(Constants.SIGN_KEY, "").equals(MD5Utils.stringToMD5(params));
        log.info("Signature verification ok: {}, URI: {}, method: {}, params: {}", checkSign, request.getRequestURI(), method, params);
        return checkSign;
    }

    private Map<String, String> convertParamsArr(Map<String, String[]> paramsArr) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : paramsArr.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        return params;
    }
}

