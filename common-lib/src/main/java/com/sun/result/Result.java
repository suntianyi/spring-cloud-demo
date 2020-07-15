package com.sun.result;

import com.sun.constants.MessageConstants;
import com.sun.constants.SysConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<T>(SysConstants.SUCCESS, MessageConstants.SUCCESS, null);
    }

    public static <T> Result<T> success(String message) {
        return new Result<T>(SysConstants.SUCCESS, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(SysConstants.SUCCESS, MessageConstants.SUCCESS, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(SysConstants.SUCCESS, message, data);
    }

    public static <T> Result<T> warn() {
        return new Result<T>(SysConstants.WARN, MessageConstants.WARN, null);
    }

    public static <T> Result<T> warn(String message) {
        return new Result<T>(SysConstants.WARN, message, null);
    }

    public static <T> Result<T> warn(T data) {
        return new Result<T>(SysConstants.WARN, MessageConstants.WARN, data);
    }

    public static <T> Result<T> warn(String message, T data) {
        return new Result<T>(SysConstants.WARN, message, data);
    }

    public static <T> Result<T> fail() {
        return new Result<T>(SysConstants.FAIL, MessageConstants.FAIL, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<T>(SysConstants.FAIL, message, null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<T>(SysConstants.FAIL, MessageConstants.FAIL, data);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<T>(SysConstants.FAIL, message, data);
    }
}