package com.sun.service.impl;

import com.sun.model.User;
import com.sun.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author sunzh
 */

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Override
    public User get(String id) {
        User user = new User();
        user.setId(id);
        user.setUserName("sun");
        user.setPhone("18326089515");
        return user;
    }
}
