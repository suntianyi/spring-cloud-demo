package com.sun.controller;

import com.sun.constants.MessageConstants;
import com.sun.constants.RedisKeyPrefixConstants;
import com.sun.enums.LoginType;
import com.sun.enums.PlatformType;
import com.sun.model.User;
import com.sun.result.Result;
import com.sun.service.UserService;
import com.sun.utils.JWTUtil;
import com.sun.utils.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "")
@Api(tags = "登录")
public class LoginController {
    private final UserService userService;

    private final RedisTemplate<String, String> redisTemplate;

    public LoginController(UserService userService, RedisTemplate<String, String> redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public Result<Map<String, Object>> login(String userName, String phone, String password, String captcha, LoginType type, PlatformType platform) {
        Map<String, Object> resultMap = new HashMap<>();
        User user = null;
        if (LoginType.PASSWORD.equals(type)) {
            user = checkPassword(userName, phone, password);
        } else if (LoginType.CAPTCHA.equals(type)) {
            user = checkCaptcha(phone, captcha);
        }
        resultMap.put("user", user);
        resultMap.put("token", JWTUtil.generateToken(SystemUtil.objToMap(user)));
        return Result.success(resultMap);
    }

    private User checkPassword(String userName, String phone, String password) {
        Assert.hasText(password, MessageConstants.PARAM_ILLEGAL);
        User user = userService.get(null, userName, phone);
        Assert.isTrue(password.equalsIgnoreCase(user.getPassword()), MessageConstants.PASSWORD_ILLEGAL);
        return user;
    }

    private User checkCaptcha(String phone, String captcha) {
        String key = RedisKeyPrefixConstants.SHORT_MESSAGE + "-" + phone;
        String c = redisTemplate.opsForValue().get(key);
        Assert.isTrue(c != null && c.equals(captcha), MessageConstants.CAPTCHA_ILLEGAL);
        return userService.get(null, phone, phone);
    }
}
