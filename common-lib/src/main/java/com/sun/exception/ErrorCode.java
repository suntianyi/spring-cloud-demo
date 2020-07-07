package com.sun.exception;

/**
 * 通用业务异常定义
 * @author sunzh
 */

public enum ErrorCode implements ErrorEnum {

    /**
     * 主键为空
     */
    EMPTY_ID("主键为空"),

    /**
     * 参数非法
     */
    ILLEGAL_PARAM("参数非法"),

    /**
     * 更新失败
     */
    UPDATE_FAIL("更新失败！"),

    /**
     * 删除失败
     */
    DELETE_FAIL("删除失败！"),

    /**
     * 添加失败
     */
    ADD_FAIL("添加失败！"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR("未知错误");

    private final String text;

    ErrorCode(String text) {
        this.text = text;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getText() {
        return text;
    }
}
