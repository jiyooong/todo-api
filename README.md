# 📝 Todo API

JWT 인증 기반 할 일 관리 REST API 서버

## 🛠 기술 스택
- Java 25
- Spring Boot 3.5
- Spring Security
- JWT
- JPA / Hibernate
- MySQL

## ✅ 구현 기능
- [x] 회원가입 (비밀번호 BCrypt 암호화)
- [ ] 로그인 (JWT 토큰 발급)
- [ ] 할 일 생성
- [ ] 할 일 조회
- [ ] 할 일 수정
- [ ] 할 일 삭제

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

## 🚀 실행 방법
1. MySQL 실행 후 `todo_db` 데이터베이스 생성
2. `application.properties` 에서 DB 설정 확인
3. 프로젝트 실행 후 `http://localhost:8080` 으로 접속
