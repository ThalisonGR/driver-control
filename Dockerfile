FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy gradle files
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Fix line endings on Windows for gradlew
RUN sed -i 's/\r$//' gradlew
RUN chmod +x gradlew

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy source and build
COPY src src
RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
