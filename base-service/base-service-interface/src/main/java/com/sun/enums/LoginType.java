package com.sun.enums;

public enum LoginType {
    PASSWORD("密码登录"),
    CAPTCHA("验证码登录");

    private String value;

    private LoginType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
