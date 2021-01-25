package com.sun.aspect;

import com.sun.annotation.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class RedisLockAspect {
    private final RedissonClient redissonClient;

    public RedisLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(redisLock)")
    public Object around(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        String key = redisLock.key();
        String lockName = redisLock.lockName();
        RLock rLock = redissonClient.getLock(lockName + ":" + key);
        boolean r;
        if (redisLock.retry()) {
            r = rLock.tryLock(redisLock.retryTime(), redisLock.expireTime(), TimeUnit.MILLISECONDS);
        } else {
            r = rLock.tryLock(0, redisLock.expireTime(), TimeUnit.MILLISECONDS);
        }
        Object result = null;
        // 获取到锁才执行
        if (r) {
            log.info(Thread.currentThread().getName() + "获取到锁");
            try {
                result = joinPoint.proceed();
            } finally {
                rLock.unlock();
            }
        } else {
            log.info(Thread.currentThread().getName() + "没有获取到锁");
        }
        return result;
    }

}
