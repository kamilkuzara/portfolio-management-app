**AAAK Investment Portfolio Management**
An investment portfolio management app where users can add, delete, and view investments.
Built with Spring Boot (Java) and a lightweight HTML/CSS/JS frontend, using an H2 in-memory database for storage.
⚙️ Tech Stack
- Java 17
- Spring Boot 3
- Maven (project build & dependency management)
- H2 Database (in-memory, for development and testing)
- HTML / CSS / JavaScript (frontend)
🚀 Getting Started
Prerequisites
Make sure you have the following installed:
- Java 17+ (https://adoptium.net/) 
- Maven (https://maven.apache.org/) 
Running the Application
Clone the repository and run:
./mvnw spring-boot:run
The app will start on http://localhost:8080.
✅ Testing
This project uses JUnit for automated testing.
Run all tests with:
./mvnw test
🚀 Continuous Integration
A GitHub Actions workflow (.github/workflows/ci.yml) is set up to:
- Run JUnit tests
- Enforce code formatting with Spotless
- Check for potential bugs with SpotBugs
📋 Contribution Guidelines
We use a simple workflow:
- Create a branch for each feature or bugfix.
- Open a Pull Request when ready.
- Code will be reviewed before merging to main.
📖 Project Management
Development is tracked with a Kanban board on Trello.
- Tasks are broken down into MVP, Sprint 1, Sprint 2, etc.
- Each task is assigned to a developer.
📝 License
This project is licensed under the MIT License – see the LICENSE file for details.
