# Risk Log

This document tracks potential risks to the Portfolio Management REST API project and outlines mitigation strategies.

---

## Risk Register

| Risk ID | Risk Description | Likelihood | Impact | Mitigation |
|---------|------------------|------------|--------|------------|
| R1 | API may not handle incorrect or missing input data | Medium | High | Add validation on all endpoints and return clear error messages |
| R2 | Performance issues when portfolio grows larger than expected | Low | Medium | Use efficient data structures and simple database optimization |
| R3 | Security issues if API is publicly accessible (no authentication by design) | High | High | Restrict deployment to demo/internal environments, document limitation clearly |
| R4 | Poor error handling could confuse users | Low | Medium | Implement a standard error response format |
| R5 | Feature creep may delay delivery | Medium | Medium | Define MVP clearly and approve changes before adding features |
| R6 | Deployment or environment setup errors | Low | Medium | Use simple deployment scripts and document setup instructions |
| R7 | Miscommunication or overlap in responsibilities | Low | Medium | Daily standups and role assignments ensure alignment |
| R8 | External API dependency may fail, change, or become costly | Medium | High | Implement fallback mechanisms, monitor API status, and document integration clearly |
| R9 | H2 in-memory database may not reflect production behavior | Medium | Medium | Validate against a lightweight persistent DB before release |
| R10 | Limited frontend stack may restrict future features or maintainability | Medium | Medium | Keep frontend simple for MVP, document trade-offs, and revisit if expansion needed |
| R11 | Delaying performance considerations (CRUD-first approach) may lead to rework | Low | Medium | Monitor performance early with sample data and adjust as needed |
| R12 | Documentation drift if Markdown logs are not updated consistently | Low | Low | Assign responsibility for log maintenance and review at standups |

---

*Last Updated: 2025-08-26*
