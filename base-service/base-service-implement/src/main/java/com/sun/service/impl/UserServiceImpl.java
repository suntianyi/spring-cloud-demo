package com.sun.service.impl;

import com.sun.dto.UserInsertDTO;
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
    public boolean insert(UserInsertDTO user) {
        return true;
    }
}
