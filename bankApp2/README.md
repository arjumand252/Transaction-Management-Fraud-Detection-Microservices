# BankApplication

<!-- PROJECT LOGO -->
<h1 align="center">Bank Management System</h1>

<p align="center">
  A Spring Boot–based banking application for managing accounts, transactions, clerks, and managers.
</p>

---

## Overview

The **Bank Management System** is a Spring Boot application that follows a **layered architecture**:

1. **Controller Layer** – Handles HTTP requests and responses  
2. **Service Layer** – Implements business logic and validation  
3. **DAO / Repository Layer** – Interacts with the database using JPA/Hibernate  
4. **Database Layer** – Stores accounts, transactions, clerks, and managers  

The system ensures **data integrity**, **clean API design**, and avoids common issues such as:
- Transient entity errors  
- Infinite JSON serialization loops  

---

## Features

- **Account Management**
  - Create, read, update, and delete accounts
- **Transaction Management**
  - Deposit, withdraw, and transfer funds
- **Clerk & Manager Management**
  - Many-to-one relationship between clerks and managers
  - Prevents saving clerks with non-existent managers
- **Exception Handling**
  - Business exceptions in the service layer
  - Persistence exceptions handled in DAO layer
- **JSON Serialization Safety**
  - Uses `@JsonManagedReference` and `@JsonBackReference`
- **RESTful APIs**
  - Clean, consistent endpoints following best practices

---

## Technologies

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA / Hibernate**
- **MySQL / H2 Database**
- **Maven**
- **RESTful APIs (JSON)**

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL (or H2 for in-memory testing)

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/bank-management-system.git
   cd bank-management-system

2. **Configure the DB**
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

## API Endpoints

### Account APIs

| Method | Endpoint              | Description        |
|------|----------------------|--------------------|
| GET  | /v1/accounts         | Get all accounts   |
| GET  | /v1/accounts/{id}    | Get account by ID  |
| POST | /v1/accounts         | Create account     |
| PUT  | /v1/accounts/{id}    | Update account     |
| DELETE | /v1/accounts/{id}  | Delete account     |

---

### Manager APIs

| Method | Endpoint              | Description        |
|------|----------------------|--------------------|
| GET  | /v1/managers         | Get all managers   |
| GET  | /v1/managers/{id}    | Get manager by ID  |
| POST | /v1/managers         | Create manager     |
| PUT  | /v1/managers/{id}    | Update manager     |
| DELETE | /v1/managers/{id}  | Delete manager     |

---

### Clerk APIs

| Method | Endpoint              | Description        |
|------|----------------------|--------------------|
| GET  | /v1/clerks           | Get all clerks     |
| GET  | /v1/clerks/{id}      | Get clerk by ID    |
| POST | /v1/clerks           | Create clerk       |
| PUT  | /v1/clerks/{id}      | Update clerk       |
| DELETE | /v1/clerks/{id}    | Delete clerk       |

---

### Clerk DTO Example

> Use DTOs to avoid transient entity issues

```json
{
  "clerkName": "Alice Smith",
  "managerId": 1
}
```

### Entity Relationships

Manager ↔ Clerk

One manager → many clerks (bidirectional)

Account ↔ Transaction

One account → many transactions

JSON Handling

@JsonManagedReference / @JsonBackReference prevent infinite recursion

### Exception Handling

Service Layer

Throws business exceptions (e.g., BankAccountNotFoundException)

DAO Layer

Handles persistence exceptions

Controller Layer

Returns proper HTTP status codes:

400 Bad Request

404 Not Found

500 Internal Server Error
