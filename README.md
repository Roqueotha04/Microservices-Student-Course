# 🚀 Students Enrollment Microservice Project

## 📌 Project Status

Most of the features that will be added soon are already implemented in my other projects:

- `book_loan`
- `superhero backend`

You can find these fully completed end-to-end projects on my GitHub profile. 
The upcoming features planned for this repository are detailed at the end of this document.

---

# 🏗 Architecture Overview

This system follows a production-oriented microservices architecture built with:

- **Spring Boot**
- **Spring Cloud**
- **Netflix Eureka** (Service Discovery)
- **Spring Cloud Config** (Centralized Configuration)
- **OpenFeign** (Synchronous communication)
- **RabbitMQ** (Asynchronous communication)

Each service is independently deployable and registered in Eureka.

---

# 📦 Modules

| Service | Responsibility |
|----------|----------------|
| **API Gateway** | Entry point and request routing |
| **Eureka Server** | Service discovery |
| **Config Server** | Centralized configuration management |
| **Student Service** | Student domain logic |
| **Course Service** | Course domain logic |
| **Notification Service** | Event consumption and email delivery |

---

# ☁️ Communication Strategy (Spring Cloud)

## 🔁 Asynchronous Communication — RabbitMQ

Event-driven communication is implemented using **RabbitMQ**.

### Implemented Flow

1. A student enrolls in a course  
2. Student Service publishes an event  
3. Notification Service consumes the event  
4. Email confirmation is sent using `JavaMailSender`

### Architectural Purpose

- Service decoupling  
- Non-blocking processing  
- Improved scalability  
- Event-driven architecture  

This ensures the enrollment process is not directly coupled to the email delivery process.

---

## 🔗 Synchronous Communication — OpenFeign

Synchronous service-to-service calls are handled using **OpenFeign**.

### Characteristics

- Declarative HTTP clients  
- Integrated with Eureka for service name resolution  
- No hardcoded URLs  
- Cleaner inter-service contracts  

This is used for direct communication between core domain services when immediate responses are required.

---

# 🎯 Design Principles

This project aims to reflect a production-like microservices environment by applying:

- ✔ Service discovery  
- ✔ Centralized configuration  
- ✔ Synchronous + asynchronous communication  
- ✔ Clear service boundaries  
- ✔ Scalable architectural patterns  

---

# 🔜 Upcoming Features

The following improvements will be incorporated:

- 🔐 **RBAC** implemented at API Gateway level  
- 🔑 **JWT-based authentication**
- 🧪 Unit and integration testing  
- 🐳 Full Dockerization  

> These capabilities are already implemented in other personal projects and will be progressively integrated here to complete a production-ready stack

---

## 📈 Objective

This repository demonstrates practical implementation of modern microservices architecture patterns commonly used in enterprise environments.










