package com.myproject.todo_api.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 토큰 서명에 사용할 비밀키 (32자 이상이어야 해요)
    private final String SECRET_KEY = "mySecretKey12345678901234567890123456";

    // 토큰 만료 시간 (24시간)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 비밀키 객체 생성
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ① 토큰 생성
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)           // 토큰에 이메일 담기
                .setIssuedAt(new Date())     // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(getSigningKey())   // 비밀키로 서명
                .compact();
    }

    // ② 토큰에서 이메일 꺼내기
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ③ 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}