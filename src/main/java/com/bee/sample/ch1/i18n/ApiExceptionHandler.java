package com.bee.sample.ch1.i18n;

import com.bee.sample.ch1.interceptor.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author jiangcan on 2020/1/12
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResultInfo handerException(ApiException e) {
        return new ResultInfo(Integer.valueOf(e.getCode()), e.getMessage());
    }
}
