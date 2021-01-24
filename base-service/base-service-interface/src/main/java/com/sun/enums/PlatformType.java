package com.sun.enums;

public enum PlatformType {
    WEB("WEB"),
    APP("APP");

    private String value;

    private PlatformType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
