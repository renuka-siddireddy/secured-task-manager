# 🔐 Secure Task Manager API

A Spring Boot REST API secured with JWT Authentication and Role-Based Access Control (RBAC).

---

## 🚀 Features

- ✅ User Registration & Login
- ✅ JWT Authentication
- ✅ Role-Based Authorization (USER / ADMIN)
- ✅ Task CRUD Operations
- ✅ Global Exception Handling
- ✅ Swagger API Documentation
- ✅ Spring Security Integration
- ✅ H2 / MySQL Database Support

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL / H2
- Swagger (OpenAPI)
- Maven

---

## 🔐 Role-Based Access

| Role  | Access |
|--------|--------|
| USER   | Create, View, Update, Delete Own Tasks |
| ADMIN  | View All Tasks |

---

## 📂 API Endpoints

### 🔑 Authentication

POST `/api/v1/auth/register`  
POST `/api/v1/auth/login`

---

### 📌 Tasks

GET `/api/v1/tasks`  
POST `/api/v1/tasks`  
PUT `/api/v1/tasks/{id}`  
DELETE `/api/v1/tasks/{id}`  

---

### 👑 Admin

GET `/api/v1/tasks/admin/all`

(Accessible only by ADMIN role)

---

## 📖 Swagger UI

After running the application:

👉 http://localhost:9090/swagger-ui/index.html

---

## ⚙️ How To Run

1. Clone the repository:

```bash
git clone https://github.com/renuka-siddireddy/secured-task-manager.git
```

2. Open project in IntelliJ / VS Code

3. Update `application.properties` with your database credentials

4. Run:

```bash
mvn spring-boot:run
```

5. Access Swagger at:
```
http://localhost:9090/swagger-ui/index.html
```

---

## 🧪 Testing

Use Swagger UI or Postman.

Steps:
1. Register User
2. Login to get JWT token
3. Click "Authorize" in Swagger
4. Enter: Bearer <your_token>
5. Access secured endpoints

---

## 📌 Author

Renuka Siddireddy
