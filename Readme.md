# 📌 Project Task Management System
A **Spring Boot** application for managing **projects and tasks** within a team.  
It allows **project managers** to create projects, assign tasks, and track progress.

## 📖 Features
✅ **User Management**
- Create, retrieve, update, and delete users.

✅ **Project Management**
- Create, retrieve, update, and delete projects.
- Assign a project to a user.

✅ **Task Management**
- Create, retrieve, update, and delete tasks.
- Assign tasks to users.
- Filter tasks by **status & priority**.

✅ **Exception Handling**
- Uses @RestControllerAdvice for centralized error handling.
- Includes custom exceptions:
    - ResourceNotFoundException
    - DuplicateResourceException
    - ValidationException
    - BadRequestException

✅ **Persistence**
- Uses **Spring Data JPA** with **H2 Database** for lightweight storage.
- Implements relationships:
    - **One-to-Many:** User → Projects
    - **One-to-Many:** Project → Tasks
    - **Many-to-One:** Task → User

## 🛠 Technologies Used
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **H2 Database** (In-Memory)
- **Maven**
- **Lombok** (For reducing boilerplate code)
- **JUnit 5 & Mockito** (For testing)

## 📂 Project Structure
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── rahulsharma
│   │   │           └── Project_Task_Management
│   │   │               ├── entity
│   │   │               │   └── User.java
│   │   │               │   └── Task.java
│   │   │               │   └── Project.java
│   │   │               ├── repository
│   │   │               │   └── Userpository.java
│   │   │               │   └── Taskpository.java
│   │   │               │   └── Projectpository.java

│   │   │               ├── service
│   │   │               │   └── UserService.java
│   │   │               │   └── TaskService.java
│   │   │               │   └── ProjectService.java

│   │   │               ├── controller
│   │   │               │   └── UserController.java
│   │   │               │   └── TaskController.java
│   │   │               │   └── ProjectController.java

│   │   │               ├── exception
│   │   │               │   ├── BadRequestException.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               │   ├── DuplicateResourceException.java
│   │   │               │   ├── ResourceNotFoundException.java
│   │   │               │   ├── ValidationException.java

│   │   │               └── ProjectTaskManagementApplication.java
│   └── test
│       └── java
│           └── com
│               └── rahulsharma
│                   └── BookingInventory
│                       └── ProjectTaskManagementApplicationTest.java
└── pom.xm

```

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/rahulsharma9001/Project-Task-Management-Spring-.git
   ```
2. Build the Project:
   ```bash
   mvn clean install
   ```

3. Run the Application:
   ```bash
   mvn spring-boot:run
   ```

### 4️⃣ Access the APIs (Swagger or Postman)
Once running, you can test APIs at:  

```
http://localhost:8080
```

## 🛠 API Endpoints

### 🔹 User API
| Method | Endpoint | Description |
|--------|----------|-------------|
| \`POST\` | \`/users\` | Create a new user |
| \`GET\`  | \`/users/{id}\` | Get user by ID |
| \`PUT\`  | \`/users/{id}\` | Update user details |
| \`DELETE\` | \`/users/{id}\` | Delete a user |

### 🔹 Project API
| Method | Endpoint | Description |
|--------|----------|-------------|
| \`POST\` | \`/projects\` | Create a new project |
| \`GET\`  | \`/projects/{id}\` | Get project by ID |
| \`PUT\`  | \`/projects/{id}\` | Update project details |
| \`DELETE\` | \`/projects/{id}\` | Delete a project |

### 🔹 Task API
| Method | Endpoint | Description |
|--------|----------|-------------|
| \`POST\` | \`/tasks\` | Create a new task |
| \`GET\`  | \`/tasks/{id}\` | Get task by ID |
| \`PUT\`  | \`/tasks/{id}\` | Update task details |
| \`DELETE\` | \`/tasks/{id}\` | Delete a task |
| \`GET\` | \`/tasks/filter?status={status}&priority={priority}\` | Filter tasks |



## 📌 Future Enhancements
- ✅ **Integrate Redis caching** (Coming soon)
- ✅ To implement Spring Security Mechanism**
