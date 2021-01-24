package com.sun.exception;

/**
 * 异常枚举基类，业务异常枚举需要继承本类
 */

public interface ErrorEnum {
    /**
     * 获取异常编码
     *
     * @return 异常编码
     */
    String getCode();

    /**
     * 获取异常文本
     *
     * @return 异常文本
     */
    String getText();
}
