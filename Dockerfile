# Use a base image with Java 17 and Maven installed
FROM openjdk:17-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files and the project pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Copy the project source code
COPY src src

# Build the application
RUN ./mvnw clean install -DskipTests

# Use a smaller base image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder image
COPY --from=builder /app/target/email-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Define the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
