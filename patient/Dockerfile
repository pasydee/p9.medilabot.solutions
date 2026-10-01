# ---- Étape de build ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiés séparément pour profiter du cache Docker sur les dépendances
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle* settings.gradle* ./
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

COPY src ./src
RUN ./gradlew clean bootJar --no-daemon -x test

# ---- Étape d'exécution ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]