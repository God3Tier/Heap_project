# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .env .env
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/heap_application_project-0.0.1-SNAPSHOT.jar /app/app.jar
COPY --from=build /app/.env .env
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
