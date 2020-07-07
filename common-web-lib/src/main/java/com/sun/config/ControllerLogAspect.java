package com.sun.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author sunzh
 */


@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Around("execution(* com.sun..*Controller*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        String method = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getName();
        Object ret = pjp.proceed();
        long cost = System.currentTimeMillis() - begin;
        log.info("响应结果：{}", JSON.toJSONString(ret));
        log.info("<" + className + "." + method + "> 方法耗时 <"
                + cost + "> ms");
        return ret;
    }
}
