# Use a base image with Java 21
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and build configuration
# This is crucial for Gradle to be executable inside the container
COPY gradlew gradlew.bat ./
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY build/libs/*.jar app.jar

# Copy the source code
COPY src ./src

# Build the application - creates the JAR file in build/libs
# Make gradlew executable if it's not already (good practice for Linux environments)
RUN chmod +x gradlew
RUN ./gradlew jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Define the flag and secret as environment variables *inside* the container.
ENV CTF_FLAG="AfekaCTF{Never_Give_Up_On_The_Flag}"
ENV FLAG_SECRET="secure_ctf_magic_word_42"

# Command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]