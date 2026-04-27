package com.myproject.todo_api.repository;

import com.myproject.todo_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속받으면 save, findById, findAll 등 DB 기본 기능이 자동으로 생겨요
public interface UserRepository extends JpaRepository<User, Long> {

    // email로 User 찾기 (로그인할 때 사용)
    User findByEmail(String email);
} //JpaRepository를 상속받으면 기본 CRUD가 자동으로 생겨요. 추가로 필요한 쿼리는 메서드 이름 규칙으로 선언만 하면 JPA가 자동으로 SQL을 만들어줌