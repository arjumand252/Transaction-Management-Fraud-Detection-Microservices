# Transaction-Management-Fraud-Detection-Microservices

A full-stack banking-style application built with **Spring Boot, Angular, and Apache Kafka Streams** for managing accounts, transactions, clerks, and managers, with secure role-based access and real-time transaction monitoring.

---

## Overview

The Bank Management System is designed as a modular, enterprise-style application with a layered backend and a separate frontend.

### Backend Architecture
- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Implements business logic and validation
- **DAO / Repository Layer** – Interacts with the database using JPA/Hibernate
- **Database Layer** – Stores accounts, transactions, clerks, and managers

### Frontend Architecture
- **Angular Frontend** – Provides role-based UI screens for clerks and managers, integrates with backend REST APIs, and supports secure user workflows

### Event Streaming / Real-Time Monitoring
- **Kafka Streams Pipeline** – Processes transaction-related events in real time and detects suspicious or anomalous activity patterns

The system focuses on clean API design, secure access control, data consistency, and real-time event-driven processing while avoiding common backend issues such as:
- Transient entity errors
- Infinite JSON serialization loops

---

## Features

### Account Management
- Create, read, update, and delete accounts

### Transaction Management
- Deposit, withdraw, and transfer funds
- Record transaction history for each account

### Clerk & Manager Management
- Many-to-one relationship between clerks and managers
- Prevents saving clerks with non-existent managers

### Authentication & Authorization
- JWT-based authentication
- Role-based access control for different user types such as clerks and managers
- Secured backend endpoints based on user roles

### Angular Frontend
- Separate frontend built with Angular
- Connects to Spring Boot REST APIs
- Supports form-based operations for account, clerk, manager, and transaction workflows
- Provides role-aware navigation and user interaction

### Real-Time Fraud / Anomaly Detection
- Built a real-time transaction monitoring pipeline using **Apache Kafka Streams**
- Processes transaction events asynchronously
- Flags suspicious transaction patterns for further review
- Demonstrates event-driven architecture and stream processing concepts

### Exception Handling
- Business exceptions in the service layer
- Persistence exceptions handled in DAO layer

### JSON Serialization Safety
- Uses `@JsonManagedReference` and `@JsonBackReference`

### RESTful APIs
- Clean, consistent endpoints following best practices

---

## Technologies

### Backend
- Java 17+
- Spring Boot 3.x
- Spring Data JPA / Hibernate
- Spring Security
- JWT Authentication
- MySQL / H2 Database
- Maven

### Frontend
- Angular
- TypeScript
- HTML / CSS
- Angular HTTP Client for API integration

### Streaming / Messaging
- Apache Kafka
- Kafka Streams

### APIs
- RESTful APIs (JSON)

---

## System Design Highlights

- Layered architecture for maintainability and separation of concerns
- Full-stack implementation with Angular frontend and Spring Boot backend
- Real-time event processing using Kafka Streams
- Secure API access using JWT authentication and role-based authorization
- Entity relationship handling for manager-clerk and account-transaction mappings
- DTO-based request handling to avoid transient persistence issues

---

## Microservice Architecture

This project was designed as a 2-service microservices-based system:

- Banking Service – Handles account, clerk, manager, and transaction operations through secure REST APIs using Spring Boot

- Fraud Detection Service – Consumes transaction events using Apache Kafka Streams and performs real-time anomaly detection on streaming data

Both services are decoupled by responsibility, enabling better scalability, maintainability, and event-driven processing.

---

## Getting Started

## Prerequisites
- Java 17+
- Maven
- MySQL (or H2 for in-memory testing)
- Node.js and Angular CLI
- Apache Kafka running locally or on a server

---

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/your-username/bank-management-system.git
cd bank-management-system
```

### 2. Configure the database

Set the following in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure Kafka

Add your Kafka bootstrap server configuration in `application.properties`:

```properties
spring.kafka.bootstrap-servers=localhost:9092
```

### 4. Run the Spring Boot backend

```bash
mvn spring-boot:run
```
The BankApp and Fraud microservices need to be run separately.

### 5. Run the Angular frontend

Navigate to the Angular project folder and start the frontend:

```bash
npm install
ng serve
```

By default, the Angular frontend runs on `http://localhost:4200`.

---

## API Endpoints

## Account APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/v1/accounts` | Get all accounts |
| GET | `/v1/accounts/{id}` | Get account by ID |
| POST | `/v1/accounts` | Create account |
| PUT | `/v1/accounts/{id}` | Update account |
| DELETE | `/v1/accounts/{id}` | Delete account |

## Manager APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/v1/managers` | Get all managers |
| GET | `/v1/managers/{id}` | Get manager by ID |
| POST | `/v1/managers` | Create manager |
| PUT | `/v1/managers/{id}` | Update manager |
| DELETE | `/v1/managers/{id}` | Delete manager |

## Clerk APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/v1/clerks` | Get all clerks |
| GET | `/v1/clerks/{id}` | Get clerk by ID |
| POST | `/v1/clerks` | Create clerk |
| PUT | `/v1/clerks/{id}` | Update clerk |
| DELETE | `/v1/clerks/{id}` | Delete clerk |

---

## Clerk DTO Example

Use DTOs to avoid transient entity issues:

```json
{
  "clerkName": "Alice Smith",
  "managerId": 1
}
```

---

## Entity Relationships

### Manager ↔ Clerk

- One manager → many clerks (bidirectional)

### Account ↔ Transaction

- One account → many transactions

---

## JSON Handling

- `@JsonManagedReference` / `@JsonBackReference` prevent infinite recursion

---

## Exception Handling

### Service Layer

Throws business exceptions such as:

- `BankAccountNotFoundException`

### DAO Layer

Handles persistence exceptions

### Controller Layer

Returns proper HTTP status codes:

- `400 Bad Request`
- `404 Not Found`
- `500 Internal Server Error`

---

## Event Streaming Flow

A typical transaction monitoring flow looks like this:

1. A transaction is created through the Angular frontend
2. The BankApp microservice processes and stores the transaction
3. A transaction event is published to Kafka
4. Kafka Streams consumes the event stream
5. The transaction event is processed by the Fraud Detection microservice 
6. Suspicious patterns are flagged for anomaly or fraud detection

This demonstrates real-time streaming and event-driven backend design.

---

## Future Enhancements

- Add notification/alert service for suspicious transactions
- Introduce dashboards for transaction analytics
- Add Docker-based deployment
- Add monitoring and logging for Kafka consumers and stream processors
