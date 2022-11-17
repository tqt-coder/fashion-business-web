package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.service.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class})  // Có thể bắt nhiều loại exception
    public ResponseEntity<String> handleExceptionA(Exception e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        e.printStackTrace();  // Thực tế người ta dùng logger
        return ResponseEntity.status(500).body("Unknow error");
    }
}
