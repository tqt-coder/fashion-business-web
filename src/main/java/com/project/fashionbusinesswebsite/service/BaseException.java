package com.project.fashionbusinesswebsite.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    protected String errorCode;

    protected String message;

    public BaseException() {
    }

    protected BaseException(String errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = errorCode;
    }
}

