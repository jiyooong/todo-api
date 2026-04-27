package com.myproject.todo_api.controller;

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

    // 로그인 API - POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // UserService에서 로그인 처리 후 JWT 토큰 받아서 반환
        String token = userService.login(request.email(), request.password());
        return ResponseEntity.ok(token);
    }

    // 회원가입 요청 형식
    record RegisterRequest(String email, String password) {}

    // 로그인 요청 형식
    record LoginRequest(String email, String password) {}
}