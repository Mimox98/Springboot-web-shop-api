# Web Shop API

This project contains the starter project for Part 2 of Mosh's Spring Boot course:
[https://codewithmosh.com/p/spring-boot-building-apis](https://codewithmosh.com/p/spring-boot-building-apis)

This project was done as a code-along with Mosh (course instructor) + some personal adjustments. 

#Project info

A modern RESTful Spring Boot API for a simple web shop.  
The project demonstrates clean layered architecture, DTO mapping, validations, MySQL persistence, Flyway migrations, and a static homepage that allows users to test the API directly in the browser.

---

## Live Demo

**Backend (Render):**  


**Swagger UI:**  
`/swagger-ui.html`

**Public Homepage:**  
`/`  
The homepage includes buttons to test live GET requests.

---

## Features

- Spring Boot 3
- MySQL on Aiven
- Flyway migrations
- DTO-based API design
- Global exception handling
- Swagger / OpenAPI documentation
- Static HTML/CSS/JS front page
- Fully functional CRUD endpoints:
  - Categories  
  - Users  
  - Products (requires categories)

---

# API Overview

Below is a structured overview of each resource and its CRUD operations.

---

## Category Controller

| Method | Path                     | Description              |
|--------|---------------------------|--------------------------|
| GET    | `/categories`            | List all categories      |
| GET    | `/categories/{id}`       | Get a category by id     |
| POST   | `/categories`            | Create a new category    |
| PUT    | `/categories/{id}`       | Update a category        |
| DELETE | `/categories/{id}`       | Delete a category        |

---

## User Controller

| Method | Path               | Description                |
|--------|---------------------|----------------------------|
| GET    | `/users`           | List all users             |
| GET    | `/users/{id}`      | Get user by id             |
| POST   | `/users`           | Register a new user        |
| PUT    | `/users/{id}`      | Update user information    |
| DELETE | `/users/{id}`      | Delete a user              |

Note: Users may have profiles and addresses as part of the data model.

---

## Product Controller

| Method | Path                     | Description                        |
|--------|---------------------------|------------------------------------|
| GET    | `/products`              | List all products                  |
| GET    | `/products/{id}`         | Get product by id                  |
| POST   | `/products`              | Create a product                   |
| PUT    | `/products/{id}`         | Update a product                   |
| DELETE | `/products/{id}`         | Delete a product                   |

**Important:**  
A product must be assigned to an existing category.  
At least one category must exist before creating products.

---

# Database

The project uses **MySQL hosted on Aiven**.

Schema migrations are fully managed using **Flyway**.

All migration files are located under:
`src/main/resources/db/migration/`

Current migration:
- `V1__initial_migration.sql`

Hibernate uses **validate** mode so all schema changes must happen through Flyway.

---

# Local Development

## Requirements

- Java SDK 23
- Maven
- MySQL (local or Docker)

### Run locally

```bash
mvn spring-boot:run
```
Local profile

Activate local development profile:
`SPRING_PROFILES_ACTIVE=local`

This profile expects a database at:
`jdbc:mysql://localhost:3306/store_api`
(this is all already configured in application.yaml)

# Production Deployment

I used the following: 

- **Render** for deploying the Spring Boot backend  
- **Aiven** for the managed MySQL instance  



---

# Frontend

The project includes a simple frontend for quick API testing, since the main focus are on backend with Spring boot and RESTful APIs. Frontend was mostly ai-generated.

Static files are located at:

`src/main/resources/static/`

It includes:

- Modern landing page styled with Poppins
- Alternating blue and grey sections
- Try buttons that scroll to a test console
- Live GET request console
- Link to Swagger UI

This replaces the need for Postman for quick demos.

---

# Project Structure

- src/main/java/com/mohamedFarag/store/
- controllers/
- dtos/
- entities/
- mappers/
- repositories/
- StoreApplication.java

- src/main/resources/
- application.yaml
- db/migration/
- static/

## Dependencies

- Spring Boot (Web, Validation, Test)
- Spring Data JPA
- MySQL Connector/J
- Flyway (core + MySQL)
- MapStruct (DTO mapping)
- Lombok
- springdoc-openapi (Swagger UI)

# License

MIT  



