package com.ohgiraffers.crud.menu.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public String defaultException(Exception exception) {
        System.out.println("에러 발생" + exception);
        return "error/default";
    }
}
