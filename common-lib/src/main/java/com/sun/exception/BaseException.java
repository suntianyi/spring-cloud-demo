package com.sun.exception;

/**
 * @author sunzh
 */

public class BaseException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getText(), null);
    }

    public BaseException(ErrorEnum errorEnum, Throwable cause) {
        this(errorEnum.getCode(), errorEnum.getText(), cause);
    }
}
