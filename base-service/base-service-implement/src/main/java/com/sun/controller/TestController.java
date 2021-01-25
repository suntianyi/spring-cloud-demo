package com.sun.controller;

import com.sun.annotation.RedisLock;
import com.sun.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author sunzh
 */

@RestController
@RequestMapping(value = "/test")
@Api(value = "TestController", tags = {"测试"})
@Slf4j
public class TestController {

    @GetMapping("/redisLock1")
    @ApiOperation(value = "测试Redis分布式锁方法一")
    @RedisLock(lockName = "TEST_LOCK")
    public Result<String> testRedisLock1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("测试Redis分布式锁方法一");
        return Result.success("方法一获取到了锁");
    }

    @GetMapping("/redisLock2")
    @ApiOperation(value = "测试Redis分布式锁方法二")
    @RedisLock(lockName = "TEST_LOCK")
    public Result<String> testRedisLock2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("测试Redis分布式锁方法二");
        return Result.success("方法二获取到了锁");
    }
}
