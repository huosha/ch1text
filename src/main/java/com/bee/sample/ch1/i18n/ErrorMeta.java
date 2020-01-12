package com.bee.sample.ch1.i18n;

import java.util.Locale;

/**
 * 错误对象
 * 
 * @author tanghc
 *
 */
public class ErrorMeta implements ApiError {

    public ErrorMeta(String isvModule, String code, String msg) {
        super();
        this.isvModule = isvModule;
        this.code = code;
        this.msg = msg;
    }
    
    public ErrorMeta(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private String isvModule;
    private String code;
    private String msg;

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getIsvModule() {
        return isvModule;
    }

    public void setIsvModule(String isvModule) {
        this.isvModule = isvModule;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @param params i18n属性文件参数。顺序对应文件中的占位符{0},{1}
     * @return 返回exception
     */
    public ApiException getException(Object... params) {
        return this.getException(ApiContext.getLocal(), params);
    }
    
    /**
     * 返回exception，并且附带数据
     * @param data 数据
     * @param params i18n属性文件参数。顺序对应文件中的占位符{0},{1}
     * @return 返回exception
     */
    public ApiException getExceptionData(Object data, Object... params) {
        ApiException ex = this.getException(ApiContext.getLocal(), params);
        return ex;
    }

    public ApiException getException(Locale locale, Object... params) {
        ApiError error = ErrorFactory.getError(this, locale, params);
        return new ApiException(error);
    }

}
