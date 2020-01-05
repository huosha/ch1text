package com.bee.sample.ch1.interceptor;

/**
 * @author jiangcan on 2020/1/4
 */
public class ResultInfo {
    private Integer code;

    public ResultInfo() {
        this.code = 0 ;
    }

    public ResultInfo(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
