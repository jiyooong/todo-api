package com.myproject.todo_api.repository;

import com.myproject.todo_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속받으면 save, findById, findAll 등 DB 기본 기능이 자동으로 생겨요
public interface UserRepository extends JpaRepository<User, Long> {

    // email로 User 찾기 (로그인할 때 사용)
    User findByEmail(String email);
}