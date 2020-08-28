package com.sun.dto;

import com.sun.constants.MessageConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserInsertDTO {

    @NotNull
    @Size(min = 2, max = 20, message = MessageConstants.USERNAME_FORMAT_ERROR)
    private String userName;

    @NotNull
    @Size(min = 11, max = 11, message = MessageConstants.PHONE_FORMAT_ERROR)
    private String phone;

    @NotNull
    @Size(min = 6, max = 20, message = MessageConstants.PASSWORD_FORMAT_ERROR)
    private String password;

    private String orgId;

    private String deptId;
}
