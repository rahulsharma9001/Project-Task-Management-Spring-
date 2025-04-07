# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Add the jar file
COPY target/Project-Task-Management-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
