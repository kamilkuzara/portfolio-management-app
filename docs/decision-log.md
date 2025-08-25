# Decision Log

This document records key decisions made for the Portfolio Management REST API project.

---

## Decision Register

| Date       | Decision | Reasoning | Impact |
|------------|---------|-----------|--------|
| 2025-08-22 | Use Spring Boot for backend | Familiarity and ease of setup for REST APIs | Faster development and easier testing |
| 2025-08-22 | Use H2 in-memory database for testing | Lightweight and requires no setup | Simplifies automated testing |
| 2025-08-22 | Store logs in Markdown format | Easy to version control and readable on GitHub | Maintains clear documentation history |
| 2025-08-25 | Start with CRUD functionality before adding performance features | Ensures core API stability first | Reduces complexity in early development |
| 2025-08-25 | Introduce basic automation for testing and build | Use Maven and GitHub Actions for continuous integration &#40;CI&#41; | Ensures that each push triggers build and tests automatically |


*Last Updated: 2025-08-25*
