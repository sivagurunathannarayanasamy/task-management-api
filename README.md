# Task Management API (taskapi)

A RESTful task management API built with Spring Boot 3.x/4.x and Java 21, demonstrating clean layered architecture, consistent API response design, comprehensive error handling, and a full test suite covering both unit and integration levels.

## Tech Stack

- **Java 21**
- **Spring Boot** (Web, Data JPA, Validation)
- **H2** — in-memory database
- **Gradle**
- **JUnit 5 + Mockito** — unit testing
- **MockMvc + @SpringBootTest** — integration testing

## Architecture

Standard layered structure:

with DTOs and a mapper layer separating the API's public contract from internal persistence models.

### Key Design Decisions

**Consistent response envelope.** Every endpoint returns an `ApiResponse<T>` wrapping `statusCode`, `message`, and `data` — giving API consumers one predictable shape to parse, regardless of endpoint.

**Pagination as a specialized envelope, not a bolt-on field.** `PagedResponse<T>` extends `ApiResponse<T>`, adding a `pageInfo` field (page number, size, total elements, total pages) as a sibling to `data` — keeping `data` itself consistently shaped (a plain list) whether or not the endpoint is paginated.

**Centralized exception handling.** A `@RestControllerAdvice`-based `GlobalExceptionHandler` maps domain and framework exceptions to consistent HTTP responses:

| Exception | Status | Notes |
|---|---|---|
| `TaskNotFoundException` | 404 | Message from exception |
| `MethodArgumentNotValidException` | 400 | Field-level validation errors as a map |
| `HttpMessageNotReadableException` | 400 | Fixed message — avoids leaking Jackson parse internals |
| `IllegalArgumentException` | 400 | Message from exception |
| `Exception` (catch-all) | 500 | Fixed message — avoids leaking internals |

**Deliberate empty-result semantics.** Endpoints returning collections (`getTasksByStatus`, paginated `GET /api/tasks`) return `200` with an empty list/page when nothing matches — not `404`. A missing single resource (`getTaskById`) is `404`; a valid query that simply finds nothing is `200`.

## Endpoints

| Method | Path | Description |
|---|---|---|
| `GET` | `/api/tasks` | Paginated, sortable list of tasks (`?page=&size=&sort=`) |
| `GET` | `/api/tasks/{id}` | Get a single task by ID |
| `GET` | `/api/tasks/status/{status}` | Filter tasks by status |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `GET` | `/api/tasks/count` | Total task count |

### Example — Paginated request


```json
{
  "statusCode": 200,
  "message": "Tasks in page retrieved successfully",
  "data": [
    { "id": 1, "title": "Task A", "description": "First task", "status": "TODO" }
  ],
  "pageInfo": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalElements": 3,
    "totalPages": 1
  }
}
```

## Running Locally

```bash
./gradlew bootRun
```

App runs on `http://localhost:8080`. H2 console available at `/h2-console` (if enabled).

## Testing

```bash
./gradlew test
```

Test suite includes:
- **Unit tests** (`TaskServiceTest`) — service-layer logic in isolation, using Mockito to mock `TaskRepository`/`TaskMapper`. Covers positive and negative cases (e.g. task found vs. not found).
- **Integration tests** (`TaskControllerIntegrationTest`) — full-stack tests via `MockMvc` and `@SpringBootTest`, exercising the real controller → service → repository → H2 database path, including validation failure scenarios.

## Notes

Built incrementally as a structured, day-by-day learning project — see commit history for the progression from basic CRUD through pagination, exception handling, and test coverage.