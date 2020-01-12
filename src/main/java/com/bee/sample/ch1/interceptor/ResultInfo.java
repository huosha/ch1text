package com.bee.sample.ch1.interceptor;

/**
 * @author jiangcan on 2020/1/4
 */
public class ResultInfo {
    private Integer code;

    private String message;


    public ResultInfo() {
        this.code = 0 ;
    }

    public ResultInfo(Integer code) {
        this.code = code;
    }

    public ResultInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
