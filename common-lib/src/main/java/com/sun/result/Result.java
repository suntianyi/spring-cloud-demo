package com.sun.result;

import com.sun.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunzh
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private boolean success = true;
    private T data;
    private String msg;
    private String errCode;

    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Result(boolean success, String msg, T data) {
        this(success, msg);
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<T>(true, "操作成功");
    }

    public static <T> Result<T> success(String msg) {
        return new Result<T>(true, msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(true, "操作成功", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<T>(true, msg, data);
    }

    public static <T> Result<T> fail() {
        return new Result<T>(false, "操作失败");
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(false, msg);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<T>(false, "操作失败", data);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return new Result<T>(false, msg, data);
    }

    public static <T> Result<T> fail(BaseException e) {
        return new Result<T>(false, null, e.getMessage(), e.getErrorCode());
    }
}