# Risk Log

This document tracks potential risks to the Portfolio Management REST API project and outlines mitigation strategies.

---

## Risk Register

| Risk ID | Risk Description | Likelihood | Impact | Mitigation |
|--------|------------------|------------|--------|-----------|
| R1 | API may not handle incorrect or missing input data | Medium     | High | Add validation on all endpoints and return clear error messages |
| R2 | Performance issues when portfolio grows larger than expected | Low        | Medium | Use efficient data structures and simple database optimization |
| R3 | Security issues if API is publicly accessible | Medium     | High | Add basic authentication and run minimal security tests |
| R4 | Poor error handling could confuse users | Low        | Medium | Implement a standard error response format |
| R5 | Feature creep may delay delivery | Medium     | Medium | Define MVP clearly and approve changes before adding features |
| R6 | Deployment or environment setup errors | Low        | Medium | Use simple deployment scripts and document setup instructions |
| R7 | Responsibilities split into roles | Medium     | Medium | Define clear roles and responsibilities for each team member to avoid duplication of effort and ensure accountability for deliverables |


---

## Risk Status Key
- **Likelihood**: Low, Medium, High – probability of occurrence.
- **Impact**: Low, Medium, High – potential effect on project.
- **Mitigation**: Actions to reduce likelihood or impact.

---

*Last Updated: 2025-08-22*
