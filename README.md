# AAAK Investment Portfolio Management

Lightweight portfolio management application with a Spring Boot backend and a HTML/CSS/JS frontend. The backend uses an in-memory H2 database for development and tests. This repository contains REST endpoints to manage Assets and Transactions and includes unit tests for controllers, services and entities.

## Features
- CRUD operations for Assets (create, read, update, delete)
- Read endpoints for Transactions and filtering by asset
- JPA entities with a simple service/repository/controller structure
- Unit tests (JUnit 5, Mockito) and lightweight controller tests (MockMvc)
- H2 in-memory database for local development and CI

## Tech stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 (runtime, in-memory for tests/dev)
- JUnit 5, Mockito, Spring Test (MockMvc)
- Maven for build and dependency management
- Minimal static frontend (HTML/CSS/JS) in `client/html`

## Quick start — prerequisites
- Java 17+ (Adoptium or other JDK)
- Maven (or use the included wrapper `./mvnw` on Unix/macOS or `mvnw.cmd` on Windows)

## Build and run
From project root (server module):
- Compile and run tests:
  ./mvnw test
- Run the application:
  ./mvnw spring-boot:run
The backend defaults to port `8080`. The in-memory H2 console is available when enabled; configuration may be added in `application.properties`.

## Tests
- Unit and integration-style tests located under `server/src/test/java`.
- Use `./mvnw test` to run the test suite locally or on CI.
- Tests use Mockito and Spring Test for controller unit tests (MockMvc). The project POM already includes `spring-boot-starter-test` and `h2` as runtime dependency.

## API (brief)
Note: check controllers for exact request/response models.

Assets
- GET /assets
  - Returns list of assets; supports filtering via query param `name` (GET /assets?name=...)
- GET /assets/{id}
  - Returns a single asset by id
- POST /assets
  - Create new asset (accepts JSON AssetRequest)
- DELETE /assets/{id}
  - Delete asset by id
- PUT /assets/{id} (or equivalent update endpoint)
  - Update fields on existing asset (see controller/service signatures)

Transactions
- GET /transactions
  - Returns list of all transactions
- GET /transactions?asset={assetName}
  - Returns transactions filtered by asset name (case-insensitive)

Refer to controller classes under `server/src/main/java/com/neueda/portfolio_management/controller` for request/response field details.

## Project structure (important paths)
- server/src/main/java — backend source code (controllers, services, entities, repositories)
- server/src/test/java — unit tests for controllers, services and entities
- client/html — static frontend pages (index, assets management)
- docs — test plan, decision & risk logs

## Development notes & testing guidance
- Entities: `Asset` and `Transaction` are JPA entities used across services and controllers.
- Converters: `TransactionTypeConverter` normalizes enum values persisted to DB; tests cover null, case-insensitive and invalid inputs.
- Controller tests use MockMvc (standalone) so they are fast and do not start the full Spring context.
- The suite includes a lightweight Spring context smoke test (`PortfolioManagementApplicationTests`) to detect wiring/configuration problems early.

## CI
- The server `pom.xml` includes `spring-boot-starter-test`; CI should run `./mvnw test`.
- The project is set up to use H2 for tests and runtime development; no external DB is required for CI.

## Contributing
- Create a feature branch and open a PR against `main`.
- Keep changes small and document design decisions in `docs/decision-log.md` when relevant.

## License
This project is provided under the MIT License — see the LICENSE file in the repository.
