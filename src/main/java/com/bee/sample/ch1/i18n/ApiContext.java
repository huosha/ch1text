package com.bee.sample.ch1.i18n;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * api上下文
 *
 * @author jiangcan on 2020/1/12
 */
public class ApiContext {

    private static ThreadLocal<HttpServletRequest> request = new InheritableThreadLocal<>();

    /**
     * 设置request，保存在ThreadLocal中
     * @param req
     */
    public static void setRequest(HttpServletRequest req) {
        request.set(req);
    }

    /**
     * 获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest req = request.get();
        if (req == null) {
            RequestAttributes atri = RequestContextHolder.getRequestAttributes();
            if(atri != null) {
                req = ((ServletRequestAttributes) atri).getRequest();
            }
        }
        return req;
    }

    /**
     * 获取本地化，从HttpServletRequest中获取，没有则返回Locale.SIMPLIFIED_CHINESE
     *
     * @return Locale
     */
    public static Locale getLocal() {
        HttpServletRequest req = getRequest();
        if (req == null) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        return req.getLocale();
    }

}
