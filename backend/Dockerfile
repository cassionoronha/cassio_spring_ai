FROM maven:3.9.9-amazoncorretto-21 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21-slim
ARG OPENAI_TOKEN
ENV OPENAI_TOKEN=${OPENAI_TOKEN}
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]