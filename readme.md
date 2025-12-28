# Task Management System API

This is a RESTful API for managing tasks, built with **Spring Boot**, **Hibernate**, and **JWT authentication**. Users can register, login, and manage their tasks.

---

## Base URL

```
http://localhost:8080
```

---

## Authentication Endpoints

### 1. **Register User**
**POST** `/auth/register`  

**Request Body:**
```json
{
  "name": "ibrahim",
  "email": "ibrahim@gmail.com",
  "password": "your_password"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "ibrahim",
  "email": "ibrahim@gmail.com"
}
```

---

### 2. **Login User**
**POST** `/auth/login`  

**Request Body:**
```json
{
  "email": "ibrahim@gmail.com",
  "password": "your_password"
}
```

**Response:**
```json
{
  "token": "Bearer your_jwt_token"
}
```

> Use this token in the `Authorization` header for all `/api/tasks` endpoints.

---

## Task Endpoints

All `/api/tasks` endpoints require authentication via JWT.

**Header Example:**
```
Authorization: Bearer your_jwt_token
```

---

### 1. **Create Task**
**POST** `/api/tasks`  

**Request Body:**
```json
{
  "title": "first task",
  "description": "task description",
  "status": "IN_PROGRESS"
}
```

**Response:**
```json
{
  "id": 1,
  "title": "first task",
  "description": "task description",
  "status": "IN_PROGRESS",
  "createdAt": "2025-12-28T23:25:45"
}
```

---

### 2. **Get All Tasks for Logged-in User**
**GET** `/api/tasks`  

**Response:**
```json
[
  {
    "id": 1,
    "title": "first task",
    "description": "task description",
    "status": "IN_PROGRESS",
    "createdAt": "2025-12-28T23:25:45"
  },
  {
    "id": 2,
    "title": "second task",
    "description": "another task",
    "status": "COMPLETED",
    "createdAt": "2025-12-29T10:15:00"
  }
]
```

---

### 3. **Update Task**
**PUT** `/api/tasks/{taskId}`  

**Request Body:**
```json
{
  "title": "updated task title",
  "description": "updated description",
  "status": "COMPLETED"
}
```

**Response:**
```json
{
  "id": 1,
  "title": "updated task title",
  "description": "updated description",
  "status": "COMPLETED",
  "createdAt": "2025-12-28T23:25:45"
}
```

---

### 4. **Delete Task**
**DELETE** `/api/tasks/{taskId}`  

**Response:**
```json
{
  "message": "Task deleted successfully"
}
```

---

## Notes

- **JWT Authentication**: All task endpoints require a valid JWT token in the `Authorization` header.  
- **Task Status**: Can be one of `IN_PROGRESS`, `COMPLETED`, `PENDING`.  
- **User Tasks**: A user can only see, update, or delete their own tasks.  

---

## Tech Stack

- Java 17+  
- Spring Boot 3+  
- Spring Security (JWT)  
- Hibernate / JPA  
- H2 / MySQL Database  
- Lombok  

---

## Future Improvements

- Add pagination for task listing  
- Add user roles (admin/user)  
- Add task search/filter endpoints

