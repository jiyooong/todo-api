package com.myproject.todo_api.domain;

import jakarta.persistence.*;

@Entity // 이 클래스는 DB 테이블이랑 연결돼
@Table(name = "users") // DB에 테이블 이름을 users 로 만들음
public class User {

    @Id // 이 필드가 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 값을 자동으로 1, 2, 3... 올림
    private Long id;

    @Column(nullable = false, unique = true) //nullable = false 이 값은 반드시 있어야 함, unique = true중복 값은 안 됨
    private String email;

    @Column(nullable = false) // 비밀번호는 필수지만 중복은 상관없음
    private String password;
//  private으로 선언 캡슐화를 위해서 외부에서 직접 접근을 막고 getter/setter로만 제어

    // 기본 생성자 (JPA 필수)
    public User() {}

    // 생성자
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter/Setter
    public Long getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}