
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw mvnw.cmd pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jre-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]