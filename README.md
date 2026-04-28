# 📝 Todo API

JWT 인증 기반 할 일 관리 REST API 서버

## 🛠 기술 스택
- Java 25
- Spring Boot 3.5
- Spring Security
- JWT (jjwt 0.11.5)
- JPA / Hibernate
- MySQL

## ✅ 구현 기능
- [x] 회원가입 (비밀번호 BCrypt 암호화)
- [x] 로그인 (JWT 토큰 발급)
- [x] 할 일 생성
- [x] 할 일 조회
- [x] 할 일 수정
- [x] 할 일 삭제

## 📡 API 명세

### 회원가입
- Method: `POST`
- URL: `/auth/register`
- Request Body:
```json
{
  "email": "test@test.com",
  "password": "1234"
}
```
- Response: `회원가입 성공!`

### 로그인
- Method: `POST`
- URL: `/auth/login`
- Request Body:
```json
{
  "email": "test@test.com",
  "password": "1234"
}
```
- Response: `eyJhbGci...` (JWT 토큰)

### 할 일 생성
- Method: `POST`
- URL: `/todos`
- Headers: `Authorization: Bearer {JWT토큰}`
- Request Body:
```json
{
  "title": "할 일 내용"
}
```

### 할 일 조회
- Method: `GET`
- URL: `/todos`
- Headers: `Authorization: Bearer {JWT토큰}`

### 할 일 수정
- Method: `PUT`
- URL: `/todos/{id}`
- Headers: `Authorization: Bearer {JWT토큰}`
- Request Body:
```json
{
  "title": "수정된 내용",
  "completed": true
}
```

### 할 일 삭제
- Method: `DELETE`
- URL: `/todos/{id}`
- Headers: `Authorization: Bearer {JWT토큰}`

## 🚀 실행 방법
1. MySQL 실행 후 `todo_db` 데이터베이스 생성
2. `application.properties` 에서 DB 설정 확인
3. 프로젝트 실행 후 `http://localhost:8080` 으로 접속
