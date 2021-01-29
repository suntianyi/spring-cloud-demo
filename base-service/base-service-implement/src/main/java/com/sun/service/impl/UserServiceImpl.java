package com.sun.service.impl;

import com.sun.annotation.UserDelete;
import com.sun.dto.UserInsertDTO;
import com.sun.exception.BusinessException;
import com.sun.model.User;
import com.sun.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Override
    public List<User> list(String orgId) {
        User user = new User();
        user.setId("1");
        user.setUserName("sun");
        user.setPhone("18326089515");
        return Collections.singletonList(user);
    }

    @Override
    public User get(String id, String userName, String phone) {
        User user = new User();
        user.setId(id);
        user.setUserName("sun");
        user.setPhone("18326089515");
        user.setPassword("123456");
        user.setOrgId("111111");
        return user;
    }

    @Override
    public void insert(UserInsertDTO user) {
        if ("18300000000".equals(user.getPhone())) {
            throw new BusinessException("手机号已存在");
        }
        System.out.println("新增用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");
    }

    @Override
    @UserDelete
    public String delete(String id) {
        System.out.println("删除用户");
        return id;
    }
}
