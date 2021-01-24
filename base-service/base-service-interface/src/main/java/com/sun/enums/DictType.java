package com.sun.enums;

public enum DictType {
    ORG_TYPE("单位类型"),
    MENU_TYPE("菜单类型"),
    AD_TYPE("广告类型");

    private String value;

    private DictType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
