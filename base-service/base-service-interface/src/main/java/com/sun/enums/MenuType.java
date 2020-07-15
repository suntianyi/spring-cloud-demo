package com.sun.enums;

public enum MenuType {
    DIRECTORY("目录"),
    MENU("菜单");

    private String value;

    private MenuType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
