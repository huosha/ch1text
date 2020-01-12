package com.bee.sample.ch1.i18n;

/**
 * api异常
 *
 * @author jiangcan on 2020/1/12
 */
public class ApiException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(Exception e) {
        super(e);
    }

    public ApiException(ApiError error) {
        this(error.getMessage());
        this.code = error.getCode();
    }

    public ApiException(String msg, String code) {
        super(msg);
        this.code = code;
    }

}
