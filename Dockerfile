# ----------- Build Stage -----------
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ----------- Runtime Stage -----------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=default
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
