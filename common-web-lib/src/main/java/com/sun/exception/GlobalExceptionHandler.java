package com.sun.exception;

import com.sun.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalStateException.class})
    public Result<String> handleException(IllegalStateException e) {
        log.error("业务异常:{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    Result<String> handleBindException(BindException e) {
        log.error("参数异常:{}", e.getMessage());
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String msg = errors.get(0).getDefaultMessage();
        return Result.fail(msg);
    }

    @ExceptionHandler(Exception.class)
    Result<String> handleException(Exception e) {
        log.error("系统异常:{}", e.getMessage());
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
