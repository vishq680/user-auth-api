# Cloud-Native Web Application - User Management API

This is a Spring Boot RESTful web service for user management, developed as part of a cloud-native web application. It supports basic authentication and uses PostgreSQL as the database. The application bootstraps the database automatically and adheres to REST API design best practices.

## 🚀 Features

- User Registration (`POST /user`)
- Get Authenticated User Info (`GET /user/self`)
- Update User Info (`PUT /user/self`)
- Secure Password Storage with BCrypt
- Token-Based Basic Authentication
- Auto-bootstrapping of database schema using Hibernate (no manual SQL scripts)
- Follows Swagger documentation for field formats and validations

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **BCrypt PasswordEncoder**

## 🗃️ Database

The application uses **PostgreSQL**, and the schema is auto-generated at runtime using Hibernate. No need for manual table creation.

## 🔐 Authentication

This app uses **Basic Authentication** (username = email, password = user password). JWT or session-based authentication is not supported as per requirements.

### Example (with `curl`):

```bash
curl -u john.doe@example.com:mypassword http://localhost:8080/user/self
```

API Endpoints
➕ Create a User
POST /user

```json
{
  "email": "john.doe@example.com",
  "password": "mypassword",
  "firstName": "John",
  "lastName": "Doe"
}
```
👤 Get User Details
GET /user/self

Requires Basic Auth.

Returns all user fields except password.


4. Build & Run

```bash

mvn clean install
mvn spring-boot:run
```


📂 Folder Structure
bash
Copy
Edit
src/
├── main/
│   ├── java/com/example/bootstrapping/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── security/
│   └── resources/
│       └── application.properties
└── test/