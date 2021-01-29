package com.sun.controller;

import com.sun.constants.MessageConstants;
import com.sun.dto.UserInsertDTO;
import com.sun.model.User;
import com.sun.result.Result;
import com.sun.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户管理")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    @ApiOperation(value = "用户列表")
    public Result<List<User>> list(@RequestHeader(value = "user") User user) {
        String orgId = user.getOrgId();
        return Result.success(userService.list(orgId));
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "根据ID查询")
    public Result<User> get(@PathVariable String id) {
        return Result.success(userService.get(id, null, null));
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "添加用户")
    public Result<String> insert(@RequestHeader(value = "user") User user, @Valid UserInsertDTO userInsertDTO) {
        if (StringUtils.isEmpty(userInsertDTO.getOrgId())) {
            userInsertDTO.setOrgId(user.getOrgId());
        }
        userService.insert(userInsertDTO);
        return Result.success(MessageConstants.ADD_SUCCESS);
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "修改用户")
    public Result<String> update() {
        userService.update();
        return Result.success(MessageConstants.UPDATE_SUCCESS);
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除用户")
    public Result<String> delete(@RequestHeader(value = "user") User user, String id) {
        userService.delete(id);
        return Result.success(MessageConstants.DELETE_SUCCESS);
    }
}
