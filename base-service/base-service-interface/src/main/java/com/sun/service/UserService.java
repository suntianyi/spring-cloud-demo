package com.sun.service;

import com.sun.dto.UserInsertDTO;
import com.sun.model.User;

import java.util.List;

public interface UserService {
    List<User> list(String orgId);

    User get(String id, String userName, String phone);

    boolean insert(UserInsertDTO user);
}
