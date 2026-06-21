# PromptHub-mybatis Project

기존 Servlet + JDBC 기반으로 개발된 PromptHub를 Spring Boot + MyBatis 구조로 리팩토링한 프로젝트입니다.

</br>

## 프로젝트 소개

PromptHub는 AI 프롬프트를 등록, 조회, 수정, 삭제할 수 있는 게시판 서비스입니다.

기존 프로젝트에서 직접 구현했던 Servlet, JDBC 코드를 Spring Boot와 MyBatis 기반으로 변경하여 유지보수성과 생산성을 향상시키는 것을 목표로 진행하였습니다.


</br></br></br>

## 기술 스택

### Backend
- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Validation
- MyBatis Spring Boot Starter 4.0.1

### Database
- MySQL 8.x
- MySQL Connector/J

### Build Tool
- Gradle

### Utilities
- Lombok
- Spring Boot DevTools


</br></br></br>

## 주요 기능

### 1. 사용자 관리 (User)
- 로그인/로그아웃
- 세션 기반 인증
- 사용자 정보 조회

### 2. 카테고리 관리 (Category)
- 프롬프트 카테고리 조회
- 카테고리별 프롬프트 필터링

### 3. 프롬프트 관리 (Prompt)
- 프롬프트 목록 조회
- 프롬프트 상세 조회
- 프롬프트 등록
- 프롬프트 수정
- 프롬프트 삭제
- 조회수 관리

</br></br></br>

## 프로젝트 구조

```
prompthub-mybatis/
├── src/
│   ├── main/
│   │   ├── java/com/prompthub/
│   │   │   ├── Application.java
│   │   │   ├── configuration/
│   │   │   │   └── CorsConfig.java
│   │   │   ├── user/
│   │   │   │   ├── controller/UserController.java
│   │   │   │   ├── service/UserService.java
│   │   │   │   └── model/
│   │   │   │       ├── dao/UserMapper.java
│   │   │   │       └── dto/
│   │   │   ├── category/
│   │   │   │   ├── controller/CategoryController.java
│   │   │   │   ├── service/CategoryService.java
│   │   │   │   └── model/
│   │   │   │       ├── dao/CategoryMapper.java
│   │   │   │       └── dto/
│   │   │   └── prompt/
│   │   │       ├── controller/PromptController.java
│   │   │       ├── service/PromptService.java
│   │   │       └── model/
│   │   │           ├── dao/PromptMapper.java
│   │   │           └── dto/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── mappers/
│   │           ├── UserMapper.xml
│   │           ├── CategoryMapper.xml
│   │           └── PromptMapper.xml
│   └── test/
├── sql/
│   └── prompthub.sql
├── api-test.http
├── build.gradle
└── README.md
```

</br>

### 아키텍처 구조
- **Controller**: HTTP 요청을 받아 처리하고 응답을 반환
- **Service**: 비즈니스 로직 처리
- **Mapper (DAO)**: MyBatis를 통한 데이터베이스 접근
- **DTO**: 데이터 전송 객체


</br></br></br>

## 데이터베이스 스키마

### 테이블 구조

#### users
- `user_id` (PK): 사용자 고유 ID
- `login_id`: 로그인 ID (UNIQUE)
- `password_hash`: 비밀번호 해시
- `nickname`: 닉네임 (UNIQUE)
- `created_at`: 생성일시
- `deleted_at`: 삭제일시

#### categories
- `category_id` (PK): 카테고리 고유 ID
- `name`: 카테고리 이름 (UNIQUE)

#### prompts
- `prompt_id` (PK): 프롬프트 고유 ID
- `user_id` (FK): 작성자 ID
- `category_id` (FK): 카테고리 ID
- `title`: 제목
- `description`: 설명
- `content`: 프롬프트 내용
- `view_count`: 조회수
- `created_at`: 생성일시
- `updated_at`: 수정일시
- `deleted_at`: 삭제일시


</br></br></br>

## 설치 및 실행

### 사전 요구사항
- Java 21 이상
- MySQL 8.x
- Gradle

</br>

### 데이터베이스 설정

1. MySQL에서 데이터베이스 생성 및 테이블 생성
```bash
mysql -u root -p < sql/prompthub.sql
```

2. `src/main/resources/application.yml` 파일에서 데이터베이스 연결 정보 수정
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/prompthub_db
    username: root
    password: your_password
```

</br>

### 애플리케이션 실행

```bash
# Gradle을 사용한 빌드 및 실행
./gradlew bootRun

# 또는 JAR 파일 생성 후 실행
./gradlew build
java -jar build/libs/prompthub-0.0.1-SNAPSHOT.jar
```

애플리케이션이 실행되면 `http://localhost:8080`에서 접근할 수 있습니다.

</br></br></br>

## API 엔드포인트

### User API
- `POST /api/users/login` - 로그인
- `GET /api/users/me` - 현재 로그인한 사용자 정보 조회
- `POST /api/users/logout` - 로그아웃

</br>

### Prompt API
- `GET /api/prompts/list` - 프롬프트 목록 조회
- `GET /api/prompts/detail?id={id}` - 프롬프트 상세 조회
- `POST /api/prompts/create` - 프롬프트 등록
- `POST /api/prompts/update` - 프롬프트 수정
- `POST /api/prompts/delete` - 프롬프트 삭제

</br>

### Category API
- 카테고리 관련 API (구현 상태에 따라 추가)

자세한 API 테스트는 `api-test.http` 파일을 참고하세요.

</br></br></br>

## 개발 환경

### IDE 설정
- IntelliJ IDEA 또는 Eclipse 사용 권장
- Lombok 플러그인 설치 필요

</br>

### 개발 시 주의사항
- MyBatis XML 매퍼 파일은 `src/main/resources/mappers/` 경로에 위치
- 매퍼 파일 수정 시 애플리케이션 재시작 필요 (DevTools 사용 시 자동 재시작)

</br>

## 기존 프로젝트와의 차이점

| 항목 | 기존 (Servlet/JDBC) | 현재 (Spring Boot/MyBatis) |
|------|---------------------|---------------------------|
| 웹 프레임워크 | Servlet | Spring Web MVC |
| 데이터베이스 접근 | JDBC Template (수동) | MyBatis |
| 의존성 관리 | 수동 라이브러리 관리 | Spring Boot Starter |
| 설정 방식 | web.xml, 수동 설정 | application.yml, 자동 설정 |
| 개발 생산성 | 낮음 | 높음 (자동 재시작, Lombok 등) |