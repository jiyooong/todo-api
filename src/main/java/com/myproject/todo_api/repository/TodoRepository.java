package com.myproject.todo_api.repository;

import com.myproject.todo_api.domain.Todo;
import com.myproject.todo_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 특정 유저의 할 일 목록 전체 조회
    List<Todo> findByUser(User user);
}