package com.sun.controller;

import com.sun.constants.MessageConstants;
import com.sun.dto.UserInsertDTO;
import com.sun.po.User;
import com.sun.result.Result;
import com.sun.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public Result<List<User>> list(@RequestHeader(value = "user") User user) {
        String orgId = user.getOrgId();
        return Result.success(userService.list(orgId));
    }

    @GetMapping(value = "{id}")
    public Result<User> get(@PathVariable String id) {
        return Result.success(userService.get(id, null, null));
    }

    @PostMapping(value = "/insert")
    public Result<String> insert(@Valid UserInsertDTO user) {
        return userService.insert(user) ? Result.success(MessageConstants.ADD_SUCCESS) : Result.fail(MessageConstants.ADD_FAIL);
    }
}
