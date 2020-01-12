package com.bee.sample.ch1.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Locale;

/**
 * 错误工厂
 *
 * @author jiangcan on 2020/1/12
 */
public class ErrorFactory {

    private static final Logger log = LoggerFactory.getLogger(ErrorFactory.class);

    private static final String I18N_ERROR = "static/i18n/message";

    /** 错误信息的国际化信息 */
    private static MessageSourceAccessor messageSourceAccessor;

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        ErrorFactory.messageSourceAccessor = messageSourceAccessor;
    }

    /**
     * 设置国际化资源信息
     */
    public static void initMessageSource() {
        HashSet<String> baseNameSet = new HashSet<>();
        baseNameSet.add(I18N_ERROR);
        String[] totalBaseNames = baseNameSet.toArray(new String[0]);
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasenames(totalBaseNames);
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(bundleMessageSource);
        setMessageSourceAccessor(messageSourceAccessor);
    }

    /**
     * 返回本地化信息
     * @param module 错误模块
     * @param locale 本地化
     * @param params 参数
     * @return 返回信息
     */
    public static String getErrorMessage(String module, Locale locale, Object... params) {
        try {
            return messageSourceAccessor.getMessage(module, params, locale);
        } catch (NoSuchMessageException e) {
            return "system error is not exist";
        }
    }

    /**
     * 通过ErrorMeta，Locale，params构建国际化错误消息
     * @param errorMeta 错误信息
     * @param locale 本地化
     * @param params 参数
     * @return 如果没有配置国际化消息，则直接返回errorMeta中的信息
     */
    public static ApiError getError(ErrorMeta errorMeta, Locale locale, Object... params) {
        final String code = errorMeta.getCode();
        String errorMessage = getErrorMessage(errorMeta.getIsvModule(), locale, params);
        if(StringUtils.isEmpty(errorMessage)) {
            errorMessage = errorMeta.getMessage();
        }
        final String errorMsg = errorMessage;
        return new ApiError() {
            @Override
            public String getMessage() {
                return errorMsg;
            }

            @Override
            public String getCode() {
                return code;
            }
        };

    }



}
