plugins {
    java
    id("org.springframework.boot") version "4.1.0"
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
extra["springCloudVersion"] = "2025.1.2"

dependencies {
    // Web MVC (controllers REST)
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    // JPA (Hibernate)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Validation (Jakarta)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    // --- DATABASE ---

    // MySQL driver (runtime only)
    runtimeOnly("com.mysql:mysql-connector-j")

    // --- LOMBOK ---

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // --- TESTS ---

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc")
    // Optionnel : Client de découverte si vous utilisez Eureka
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")



}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.test {
    enabled = false
}

