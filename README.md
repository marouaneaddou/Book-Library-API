# Book-Library-API

A RESTful API for managing books and authors, built with **Java** and **Spring Boot**.
The API provides CRUD operations for books and authors and manages the relationship between them.

## Built With

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Bean Validation

## Features

- Author CRUD
- Book CRUD
- Author–Book relationship
- Request validation
- Partial updates with `PATCH`
- Global exception handling
- Business rule validation
- PostgreSQL persistence
- Application logging

## API Endpoints

### Authors

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/authors` | Get all authors |
| GET | `/authors/{id}` | Get an author |
| POST | `/authors` | Create an author |
| PATCH | `/authors/{id}` | Update an author |
| DELETE | `/authors/{id}` | Delete an author |

### Books

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/books` | Get all books |
| GET | `/books/{isbn}` | Get a book |
| POST | `/books` | Create a book |
| PATCH | `/books/{isbn}` | Update a book |
| DELETE | `/books/{isbn}` | Delete a book |

## Project Structure

The project follows a layered architecture:

```text
src/
├── main/
│   ├── java/
│   │   └── com.marouane.library/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── entity/
│   │       ├── dto/
│   │       ├── command/
│   │       ├── mapper/
│   │       ├── exception/
│   │       └── projections/
│   │
│   └── resources/
│       └── application.properties
│
└── test/
```
## Main Layers
  * **Controller** : Handles HTTP requests and responses.
  * **Mapper** : Converts between DTOs, Commands, and Entities.
  * **DTO / Command** : Transfers data between the API and application layers.
  * **Service** : Contains business logic and business rules.
  * **Repository** : Handles database access using Spring Data JPA.
  * **Entity** : Represents the database model.
  * **Exception** : Contains application exceptions and global exception handling.

## Database Configuration

The application uses **PostgreSQL**.

### 1. Create the Database
Create the PostgreSQL database:

```sql
CREATE DATABASE library_db;
```
### 2. Configure Environment Variables
Create a `.env` file in the project root:
```env
DB_URL=jdbc:postgresql://localhost:5432/library_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
```
> Replace the `database username` and `password` with your own PostgreSQL credentials.

## How to Run

### Requirements
* Java 17+
* PostgreSQL
* Maven

### 1. Clone the repository
  ```bash
  git clone https://github.com/marouaneaddou/Book-Library-API.git
  cd Book-Library-API
  ```
### 2 Configure PostgreSQL
  > Make sure `PostgreSQL` is running and the `database has been created`.`

  > Configure your `environment` variables with the `PostgreSQL connection details.`
### 3. Start the Application
  Using Maven Wrapper:
  ```bash
  ./mvnw spring-boot:run
  ```
  On Windows:
  ```bash
  mvnw.cmd spring-boot:run
  ```
  Or using Maven:
  ```bash
  mvn spring-boot:run
  ```
  The API will be available at:
  ```
  http://localhost:8080
  ```

## Future Improvements
  Possible future improvements:
*  Authentication
*  Authorization and role-based access control
*  Pagination
*  Filtering and sorting
*  Book search
*  OpenAPI / Swagger documentation
*  Docker support

## Purpose
This project was built to practice `Java backend development` with `Spring Boot` and apply `REST API design`, `layered architecture`, `database persistence`, `validation`, and business logic separation in a practical project.
