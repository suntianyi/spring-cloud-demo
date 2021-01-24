package com.sun.enums;

public enum OrgType {
    GOVERNMENT("政府"),
    ENTERPRISE("企业"),
    CANTEEN("食堂"),
    BUSINESS("商家");

    private String value;

    private OrgType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
