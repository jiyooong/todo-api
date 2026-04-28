package com.myproject.todo_api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.myproject.todo_api.controller.AuthController;
import com.myproject.todo_api.controller.TodoController;

import java.util.Map;

@RestControllerAdvice(assignableTypes = {AuthController.class, TodoController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", 400,
                        "message", e.getMessage()
                ));
    }
}