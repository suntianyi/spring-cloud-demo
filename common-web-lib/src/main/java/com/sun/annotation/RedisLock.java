package com.sun.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    String lockName() default "";

    String key() default "";

    /**
     * 过期时间，单位毫秒
     */
    int expireTime() default 60000;

    /**
     * 是否等待
     */
    boolean retry() default false;

    /**
     * 等待时间，单位毫秒
     */
    int retryTime() default 5000;
}
