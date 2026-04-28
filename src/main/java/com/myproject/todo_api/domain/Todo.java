package com.myproject.todo_api.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 할 일 내용
    @Column(nullable = false)
    private String title;

    // 완료 여부 (기본값 false)
    private boolean completed = false;

    // 어떤 유저의 할 일인지 (N:1 관계)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 기본 생성자 (JPA 필수)
    public Todo() {}

    // 생성자
    public Todo(String title, User user) {
        this.title = title;
        this.user = user;
    }

    // Getter/Setter
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}