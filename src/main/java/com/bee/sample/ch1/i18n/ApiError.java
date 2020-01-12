package com.bee.sample.ch1.i18n;

/**
 * api错误
 *
 * @author jiangcan on 2020/1/12
 */
public interface ApiError {

    /**
     * 获取错误信息
     * @return 返回错误信息
     */
    String getMessage();

    /**
     * 获取状态码
     * @return 返回状态码
     */
    String getCode();

}
