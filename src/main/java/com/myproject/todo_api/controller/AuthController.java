package com.myproject.todo_api.controller;

import com.myproject.todo_api.domain.User;
import com.myproject.todo_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // 생성자 주입
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API - POST /auth/register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.register(request.email(), request.password());
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 요청 데이터 형식 (email, password를 JSON으로 받아요)
    record RegisterRequest(String email, String password) {}
}