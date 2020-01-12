package com.bee.sample.ch1.interceptor;

import com.bee.sample.ch1.i18n.ErrorFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器 以便http post请求body输入流可多次读取
 *
 * @author zangdaiyang
 * @since 2019.11.08
 */
@Component
public class HttpServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ErrorFactory.initMessageSource();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) request);
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }

}

