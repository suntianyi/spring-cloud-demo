package com.sun.config;

import com.alibaba.fastjson.JSON;
import com.sun.po.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * 将请求头中的User字符串转换成对象
 */

@Component
public class UserFromHeaderConverter implements Converter<String, User> {

    @Override
    public User convert(@NonNull String userJson) {
        return JSON.parseObject(userJson, User.class);
    }
}
