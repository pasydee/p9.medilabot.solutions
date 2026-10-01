plugins {
	java
	id("org.springframework.boot") version "4.1.1"
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
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
dependencies {
    // Spring Web (RestTemplate, MVC)
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // Thymeleaf + Spring Security integration
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("com.mysql:mysql-connector-j")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-thymeleaf-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
