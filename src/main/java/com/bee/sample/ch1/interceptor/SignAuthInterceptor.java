package com.bee.sample.ch1.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bee.sample.ch1.model.User;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * 拦截器 验证签名 目前只支持GET/POST请求
 *
 * @author zangdaiyang
 */
@Component
public class SignAuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SignAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 1、获取请求的参数
        Map<String, Object> params;
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
                params = JSONObject.parseObject(paramStr, new TypeReference<Map<String, Object>>() {});
            } else {
                params = null;
            }
//            Enumeration headerNames = request.getHeaderNames();
//            //使用循环遍历请求头，并通过getHeader()方法获取一个指定名称的头字段
//            Map<String, Object> heards = new HashMap<>();
//            String headerName = (String) headerNames.nextElement();
//            heards.put(headerName, request.getHeader(headerName));
//            params.putAll(heards);
        } else {
            log.warn("Not supporting non-get or non-post requests, signature verification failed.");
            return false;
        }

        // 2、验证签名是否匹配
        boolean checkSign = params != null && params.getOrDefault(Constants.SIGN_KEY, "").equals(MD5Utils.creatSign(params));
        log.info("Signature verification ok: {}", checkSign);
        request.setAttribute("accessUser", new User("zhangsan","1"));
        return true;
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
}

