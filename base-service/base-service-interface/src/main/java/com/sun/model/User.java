package com.sun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -1;

    private String id;
    private String phone;
    private String userName;
    private String nickName;
    private String userIcon;

    private String orgId;
    private String orgName;
    private String deptId;
    private String deptName;
}
