# poc-testing-kata

This project is a Spring Boot application that provides a REST endpoint to calculate results for a dungeon game and saves the outcomes to a database. This README provides a complete guide to setting up, containerizing, and testing the application.

## Project Setup

### 1. Containerize the Application with Docker

First, we'll containerize the application using Docker.

#### Create a `Dockerfile`

A `Dockerfile` is created in the project root to define the image. It uses an OpenJDK 21 base image to match the project's Java version.

```dockerfile
# Use a lightweight OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file
COPY target/testingapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### Build the Docker Image

```bash
docker build -t poc-testing-kata .
```

#### Run the Docker Container

```bash
docker run -p 8080:8080 poc-testing-kata
```

### 2. Save Results in a PostgreSQL Database

The application is configured to connect to a PostgreSQL database to persist game results.

#### Dependencies

The following dependencies were added to `pom.xml`:
*   `spring-boot-starter-data-jpa`: For database interaction.
*   `postgresql`: The PostgreSQL JDBC driver.

#### Configuration

The `src/main/resources/application.properties` file is set up to connect to a PostgreSQL instance.

### 3. Use an In-Memory DB for Testing (H2)

For local development and testing, the project is configured to use the H2 in-memory database.

#### Configuration

The `application.properties` file is set to use H2 by default, with the PostgreSQL settings commented out. You can switch between them by changing which configuration is active.

#### Accessing the H2 Console

While the application is running, you can access the H2 database console to view data:
1.  Open your browser to `http://localhost:8080/h2-console`.
2.  Use the following settings to connect:
    *   **JDBC URL**: `jdbc:h2:mem:testdb`
    *   **User Name**: `sa`
    *   **Password**: (leave blank)

### 4. Automated Threat Analysis Tests with TreatAgile

Automated threat analysis involves scanning your application for security vulnerabilities using a specialized tool like TreatAgile. This is typically done in a CI/CD pipeline.

#### How It Works

1.  **Build Your App**: A build process creates an artifact (e.g., a JAR file or Docker image).
2.  **Scan the Artifact**: The artifact is submitted to TreatAgile's scanning service via an API call or command-line tool.
3.  **Get Results**: TreatAgile analyzes the artifact and returns a report detailing any security threats or vulnerabilities.

#### Example (Conceptual)

If TreatAgile provides a REST API, you might automate the scan with a script like this:

```bash
# This is a generic example. Refer to TreatAgile's documentation for actual usage.
curl -X POST https://api.treatagile.com/scan \
  -H "Authorization: Bearer <YOUR_TREATAGILE_API_TOKEN>" \
  -F "file=@target/testingapp-0.0.1-SNAPSHOT.jar"
```

To proceed, you would need to consult the **TreatAgile documentation** for their specific API endpoints, authentication methods, and required parameters.