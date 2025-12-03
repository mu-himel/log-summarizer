FROM maven:3.6.3-openjdk-17-slim AS builder

WORKDIR /app

COPY . .

COPY ./src/main/resources/application.properties ./src/main/resources/application.properties

RUN mvn clean install

FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar"]