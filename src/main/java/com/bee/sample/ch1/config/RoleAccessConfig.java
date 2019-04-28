package com.bee.sample.ch1.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class RoleAccessConfig {
    @Around("within(@org.springframework.stereotype.Controller *) && @annotation(function)")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        if (function != null) {
            String functionName = function.value();
            if (!canAccess(functionName)) {
                MethodSignature ms = (MethodSignature) pjp.getSignature();
                throw new RuntimeException("Can not Access" + ms.getMethod().getName());
            }
        }
        // 继续处理原有调用
        return pjp.proceed();
    }

    protected boolean canAccess(String functionName) {
        return functionName == null || functionName.length() == 0;
    }
}
