plugins {
    java
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "medilabo.solutions"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}
val springCloudGatewayVersion = "5.0.1"
val springCloudNetflixVersion = "5.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // 1. NOUVEAU NOM : Starter officiel Gateway pour Spring Boot 4 / WebFlux (Netty)
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux:$springCloudGatewayVersion")

    // 2. Client Eureka avec sa version de module individuelle
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:$springCloudNetflixVersion")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.test {
    enabled = false
}

