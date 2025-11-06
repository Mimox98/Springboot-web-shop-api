# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /workspace

# Leverage Docker layer caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
RUN ./mvnw -q -DskipTests dependency:go-offline

# Build the app
COPY src src
RUN ./mvnw -q -DskipTests package

# ---------- Run stage ----------
FROM eclipse-temurin:23-jre
WORKDIR /app

# Copy the fat jar
COPY --from=build /workspace/target/*.jar /app/app.jar

# Render provides $PORT; Spring must bind to it
ENV JAVA_OPTS=""
EXPOSE 8080
CMD ["sh","-c","java $JAVA_OPTS -Dserver.port=$PORT -jar /app/app.jar"]
