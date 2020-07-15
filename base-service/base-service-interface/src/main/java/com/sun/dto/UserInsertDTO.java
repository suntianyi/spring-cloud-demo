package com.sun.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserInsertDTO {

    @NotNull
    @Size(min = 2, max = 20, message = "用户名需大于2位，小于20位")
    private String userName;

    @NotNull
    @Size(min = 11, max = 11, message = "手机号格式错误")
    private String phone;

    @NotNull
    @Size(min = 6, max = 20, message = "密码需大于6位，小于20位")
    private String password;

    @NotNull
    private String orgId;

    @NotNull
    private String deptId;
}
