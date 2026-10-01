plugins {
	java
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:5.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
tasks.withType<Test> {
	useJUnitPlatform()
}
