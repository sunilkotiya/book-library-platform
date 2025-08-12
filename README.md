# Book Library Platform

A microservices-based book library management system built with Spring Boot and Java 17.

## 🏗️ Architecture

This platform consists of multiple microservices:

- **Book Service** (`book-service`) - Core book management functionality
- **Review Service** (`review-service`) - Book review and rating system  
- **Comment Service** (`comment-service`) - Comment management for books and reviews
- **Survey Service** (`survey-service`) - User surveys and feedback collection
- **Common Library** (`common-library`) - Shared utilities and Kafka messaging

## 🛠️ Technology Stack

- **Java 17** - Programming language
- **Spring Boot 3.2.4** - Application framework
- **Spring Data JPA** - Data persistence
- **PostgreSQL** - Primary database
- **Apache Kafka** - Event-driven messaging
- **Maven** - Build and dependency management
- **Lombok** - Code generation
- **JUnit 5 + Mockito** - Testing framework

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL 12+
- Apache Kafka 2.8+
- Docker (optional, for containerized deployment)

## 🚀 Quick Start

### 1. Clone the repository
```bash
git clone https://github.com/sunilkotiya/book-library-platform.git
cd book-library-platform
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Run individual services
```bash
# Book Service (default port: 8081)
cd book-service
mvn spring-boot:run

# Comment Service (configure port in application.yml)
cd comment-service  
mvn spring-boot:run
```

## 📁 Project Structure

```
book-library-platform/
├── common-library/          # Shared utilities and messaging
├── book-service/            # Core book management
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── pom.xml
├── review-service/          # Book reviews and ratings
├── comment-service/         # Comment management
├── survey-service/          # User surveys
├── pom.xml                 # Parent POM
└── README.md
```

## ⚠️ Current Status

**This project is currently in early development stage.** Most services contain only skeleton classes and require full implementation.

### Implementation Status:
- ✅ Project structure and dependencies configured
- ✅ Maven multi-module setup complete
- ❌ Entity models need implementation
- ❌ Repository interfaces need implementation  
- ❌ Service layer needs implementation
- ❌ REST controllers need implementation
- ❌ Database configuration required
- ❌ Kafka configuration required
- ❌ Tests need to be written

For a detailed analysis, see [PROJECT_REVIEW.md](PROJECT_REVIEW.md).

## 🔧 Configuration

### Database Setup
1. Create PostgreSQL database:
```sql
CREATE DATABASE book_library;
CREATE USER book_user WITH PASSWORD 'book_password';
GRANT ALL PRIVILEGES ON DATABASE book_library TO book_user;
```

2. Update `application.yml` in each service with database credentials.

### Kafka Setup
1. Start Kafka server (default: localhost:9092)
2. Configure Kafka bootstrap servers in `application.yml`

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run tests for specific service
cd book-service
mvn test
```

## 📚 API Documentation

API documentation will be available via Swagger UI once the services are implemented:
- Book Service: `http://localhost:8081/swagger-ui.html`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🔗 Related Documentation

- [Project Review and Recommendations](PROJECT_REVIEW.md)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
