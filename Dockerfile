# Use a lightweight OpenJDK 23 image
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file
COPY target/testingapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
