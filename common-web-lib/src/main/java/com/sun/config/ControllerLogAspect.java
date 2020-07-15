package com.sun.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Around("execution(* com.sun..*Controller*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object ret = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("响应结果：{}", JSON.toJSONString(ret));
        log.info("方法耗时: {}毫秒", end -begin);
        return ret;
    }
}
