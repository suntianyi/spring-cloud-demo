package com.sun.exception;

import com.sun.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Result<String> handleException(Exception e){
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    Result<String> handleBindException(BindException e) {
        List<ObjectError> errors =  e.getBindingResult().getAllErrors();
        String msg = errors.get(0).getDefaultMessage();
        return Result.fail(msg);
    }
}
