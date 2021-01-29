package com.sun.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserAspect {

    @AfterReturning(returning = "phone", pointcut = "@annotation(com.sun.annotation.UserDelete)")
    public void delete(final String phone) {
        System.out.println("删除用户，通知用户退出登录");
//        messageSender.sendMessage(phone, MessageTypeConstants.DELETE);
    }
}
