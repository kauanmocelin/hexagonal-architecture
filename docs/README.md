# Hexagonal Architecture

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/kauanmocelin/hexagonal-architecture/blob/main/LICENSE)

# About

This is an implementation of Hexagonal Architecture as objective to learn more about software architecture by hands on.
With this architecture all layers was tested independently and easily, an elegance way to accomplish decoupling of code.

# Architectural Diagram

![Hexagonal Architecture Diagram](hexagonal-architecture.png)

# Prerequisites

- [Java 17](https://openjdk.org/projects/jdk/17/)
- [Docker](https://docs.docker.com/get-started/get-docker/)

# Technologies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven](https://maven.apache.org/download.cgi)
- [Lombok](https://projectlombok.org/)
- [Testcontainers](https://testcontainers.com/)
- [ArchUnit](https://www.archunit.org/)

# Running

1. Clone the repository:
```bash
git clone https://github.com/kauanmocelin/hexagonal-architecture.git
```

2. Enter the project folder: 
```bash
cd hexagonal-architecture
```

3. Build the project using Maven:
```bash
./mvnw clean install
```

4. Run the project using spring plugin:
```
./mvnw spring-boot:run -pl infrastructure
```

# Author

**Kauan Mocelin**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/kauanmocelin/)