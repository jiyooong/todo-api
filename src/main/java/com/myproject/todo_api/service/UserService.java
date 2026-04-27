package com.myproject.todo_api.service;

import com.myproject.todo_api.config.JwtUtil;
import com.myproject.todo_api.domain.User;
import com.myproject.todo_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // JWT 토큰 만들 때 필요

    // 생성자 주입
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입
    public User register(String email, String password) {
        // 이미 가입된 이메일인지 확인
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }
        // 비밀번호 암호화해서 저장
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword);
        return userRepository.save(user);
    }

    // 로그인
    public String login(String email, String password) {
        // 1. 이메일로 유저 찾기
        User user = userRepository.findByEmail(email);

        // 2. 유저가 없으면 에러
        if (user == null) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        }

        // 3. 비밀번호 확인
        // 4. 비밀번호가 틀리면 에러
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        // 5. 다 맞으면 JWT 토큰 반환
        return jwtUtil.generateToken(email);
    }
}