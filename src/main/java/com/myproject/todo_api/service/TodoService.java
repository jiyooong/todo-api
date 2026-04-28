package com.myproject.todo_api.service;

import com.myproject.todo_api.config.JwtUtil;
import com.myproject.todo_api.domain.Todo;
import com.myproject.todo_api.domain.User;
import com.myproject.todo_api.repository.TodoRepository;
import com.myproject.todo_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 생성자 주입
    public TodoService(TodoRepository todoRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // 토큰에서 유저 꺼내기 (내부 메서드)
    private User getUserFromToken(String token) {
        // 1. 토큰에서 이메일 꺼내기
        String email = jwtUtil.getEmailFromToken(token);
        // 2. 이메일로 유저 찾기
        return userRepository.findByEmail(email);
    }

    // 할 일 생성
    public Todo createTodo(String token, String title) {
        User user = getUserFromToken(token);
        Todo todo = new Todo(title, user);
        return todoRepository.save(todo);
    }

    // 할 일 전체 조회
    public List<Todo> getTodos(String token) {
        User user = getUserFromToken(token);
        return todoRepository.findByUser(user);
    }

    // 할 일 수정
    public Todo updateTodo(String token, Long id, String title, boolean completed) {
        User user = getUserFromToken(token);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));

        // 본인 것만 수정 가능
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("권한이 없습니다.");
        }

        todo.setTitle(title);
        todo.setCompleted(completed);
        return todoRepository.save(todo);
    }

    // 할 일 삭제
    public void deleteTodo(String token, Long id) {
        User user = getUserFromToken(token);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));

        // 본인 것만 삭제 가능
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("권한이 없습니다.");
        }

        todoRepository.delete(todo);
    }
}