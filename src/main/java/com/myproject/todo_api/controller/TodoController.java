package com.myproject.todo_api.controller;

import com.myproject.todo_api.domain.Todo;
import com.myproject.todo_api.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 할 일 생성 - POST /todos
    @PostMapping
    public ResponseEntity<Todo> createTodo(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateRequest request) {
        // "Bearer eyJhbGci..." 에서 "Bearer " 제거
        String jwt = token.replace("Bearer ", "");
        Todo todo = todoService.createTodo(jwt, request.title());
        return ResponseEntity.ok(todo);
    }

    // 할 일 전체 조회 - GET /todos
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(
            @RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        List<Todo> todos = todoService.getTodos(jwt);
        return ResponseEntity.ok(todos);
    }

    // 할 일 수정 - PUT /todos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UpdateRequest request) {
        String jwt = token.replace("Bearer ", "");
        Todo todo = todoService.updateTodo(jwt, id, request.title(), request.completed());
        return ResponseEntity.ok(todo);
    }

    // 할 일 삭제 - DELETE /todos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {
        String jwt = token.replace("Bearer ", "");
        todoService.deleteTodo(jwt, id);
        return ResponseEntity.ok("삭제 완료!");
    }

    // 요청 형식
    record CreateRequest(String title) {}
    record UpdateRequest(String title, boolean completed) {}
}