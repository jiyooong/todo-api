package com.myproject.todo_api.service;

import com.myproject.todo_api.domain.User;
import com.myproject.todo_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 생성자 주입 (의존성 주입)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}