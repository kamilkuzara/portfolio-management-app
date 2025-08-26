# Decision Log

This document records key decisions made for the Portfolio Management REST API project.

---

## Decision Register

| Date       | Decision | Reasoning | Impact |
|------------|---------|-----------|--------|
| 2025-08-22 | Use Spring Boot for backend | Familiarity and ease of setup for REST APIs | Faster development and easier testing |

| 2025-08-22 | Use H2 in-memory database for testing | Lightweight and requires no setup | Simplifies automated testing |

| 2025-08-22 | Build frontend with HTML/CSS/JavaScript | Simple setup, no need for heavy frameworks, aligns with already available environment | Speeds up frontend development and reduces learning overhead |

| 2025-08-22 | No authentication/authorization | The app is intended for a single user and presentation purposes only | Reduces complexity and development time, but limits future multi-user support |

| 2025-08-22 | Store logs in Markdown format | Easy to version control and readable on GitHub | Maintains clear documentation history |

| 2025-08-22 | Start with CRUD functionality before adding performance features | Ensures core API stability first | Reduces complexity in early development |

| 2025-08-22 | Introduce basic automation for testing and build | Use Maven and GitHub workflow for continuous integration &#40;CI&#41; | Ensures that each push triggers build and tests automatically |

| 2025-08-22 | The project's workload is split into four roles | Everybody assumes a role of either Backend, Frontend, Tester, Project Coordinator | Clear ownership of responsibilities improves accountability, efficiency, and collaboration within the project |

| 2025-08-22 | Introduce daily standups | 15-minute meetings each morning where every team member shares what they did yesterday, what they plan to do today, and any blockers | Increases transparency, improves team alignment, and enables early identification of issues |

| 2025-08-25 | Simplify the database to match the low-scale of the project | Use a lightweight schema and minimal optimization, prioritizing simplicity over scalability | Reduces setup and maintenance effort, speeds up development, and avoids over-engineering for current project needs |

| 2025-08-25 | Use an external API for both possible investments and current price data | Integrate a third-party API to fetch investment options and real-time price information instead of building in-house data management | Saves development time, ensures up-to-date data, leverages reliable external sources, while satisfying customer's requirements |

*Last Updated: 2025-08-26*
