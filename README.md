## Auth template
Kotlin + Spring Boot 를 사용하여 제작되었습니다. fork 후 pull 하여 자유롭게 사용하실 수 있습니다.

### 주 사용 라이브러리
- JPA
- Lombok
- MySQL Driver
- Spring Security
- JWT (0.12.5)

### API
#### 1. sign up
end-point : auth/sign-up <br/>
request-body : 
```json
{
  "email" : "String",
  "name" : "String",
  "password" : "String"
}
```
response : 
```json
{
  "status" : 201,
  "message" : "회원가입 성공"
}
```

#### 2. sign in
end-point : auth/sign-in <br/>
request-body : 
```json
{
  "email" : "String",
  "password" : "String"
}
```
response : 
```json
{
  "status": 200,
  "message": "로그인 성공",
  "data": {
    "accessToken": "string",
    "refreshToken": "string"
  }
}
```

#### 3. refresh
end-point : auth/refresh <br/>
request-body : 
```json
{
  "refreshToken": "string"
}
```
response : 
```json
{
  "status": 200,
  "message": "토큰 재발급 성공",
  "data": {
    "accessToken": "string"
  }
}
```
