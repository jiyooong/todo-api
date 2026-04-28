package com.myproject.todo_api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// 모든 컨트롤러에서 발생하는 예외를 여기서 처리해요
@RestControllerAdvice
public class GlobalExceptionHandler {

    // RuntimeException 발생하면 여기서 잡아요
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(Map.of(
                        "status", 400,
                        "message", e.getMessage()
                ));
    }

    // 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(Map.of(
                        "status", 500,
                        "message", "서버 오류가 발생했습니다."
                ));
    }
}