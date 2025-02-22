# 🚀 Fitness Hub API Documentation

![Fitness Hub API](https://img.shields.io/badge/API-Fitness%20Hub-blue.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-brightgreen) ![Java](https://img.shields.io/badge/Java-22-orange)

## 📌 Overview
The **Fitness Hub API** provides a platform for users to manage workouts, exercises, feedback, and authentication. The API supports **JWT authentication** for secure access.

### 🌍 Base URL
```
http://localhost:8080
```

## 🔑 Authentication
The API uses **JWT-based authentication**. Include the token in the `Authorization` header:
```http
Authorization: Bearer YOUR_TOKEN_HERE
```

### ✨ Signup
**Endpoint:** `POST /api/auth/signup`
```json
{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "password": "securePassword"
}
```

### 🔐 Signin
**Endpoint:** `POST /api/auth/signin`
```json
{
  "email": "john.doe@example.com",
  "password": "securePassword"
}
```

### 🔄 Token Refresh
**Endpoint:** `POST /api/auth/refresh`
```json
{
  "token": "your_refresh_token"
}
```

---

## 📌 Endpoints

### 🏋️ Workouts

#### ➕ Create Workout
**Endpoint:** `POST /api/v1/workout/create`
```json
{
  "title": "Morning Yoga",
  "duration": "30 mins",
  "calories": 200,
  "bodyType": "ANY",
  "age": "ADULTS",
  "workoutType": "YOGA",
  "gender": "ANY"
}
```

#### 📋 Get All Workouts
**Endpoint:** `GET /api/v1/workout`

#### 🔍 Get Workout By ID
**Endpoint:** `GET /api/v1/workout/{workoutId}`

#### ✏️ Update Workout
**Endpoint:** `PUT /api/v1/workout/{workoutId}`
```json
{
  "title": "Updated Title",
  "duration": "45 mins",
  "calories": 300
}
```

#### ❌ Delete Workout
**Endpoint:** `DELETE /api/v1/workout/{workoutId}`

---

### 💪 Exercises

#### ➕ Add Exercise to Workout
**Endpoint:** `POST /api/v1/workout/exercises/{workoutId}`
```json
{
  "name": "Push-ups",
  "targetMuscle": "Chest",
  "description": "Push-ups for upper body strength."
}
```

#### 📋 Get Exercises By Workout ID
**Endpoint:** `GET /api/v1/workout/exercises/{workoutId}`

#### ✏️ Update Exercise
**Endpoint:** `PUT /api/v1/workout/exercises/{exerciseId}`
```json
{
  "name": "Updated Exercise Name",
  "targetMuscle": "Arms"
}
```

#### ❌ Delete Exercise
**Endpoint:** `DELETE /api/v1/workout/exercises/{exerciseId}`

---

### 💬 Feedback

#### ➕ Create Feedback
**Endpoint:** `POST /api/v1/{workoutId}`
```json
{
  "feedback": "Great workout!"
}
```

#### ✏️ Update Feedback
**Endpoint:** `PUT /api/v1/{feedbackId}`
```json
{
  "feedback": "Updated feedback message."
}
```

#### ❌ Delete Feedback
**Endpoint:** `DELETE /api/v1/{feedbackId}`

---


## 🔒 Security
- **JWT-based authentication** required for secured endpoints.
- Admin endpoints are restricted to users with the **ADMIN** role.

---

## 📜 API Documentation
This API follows the **OpenAPI 3.0** specification.
To view interactive documentation:
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **API Docs (JSON):** `http://localhost:8080/v3/api-docs`

---


### 🔍 Access the API
- Base URL: `http://localhost:8080`
- Explore the API using Swagger UI: `http://localhost:8080/swagger-ui.html`

---

