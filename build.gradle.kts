plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.opencodex.jobportal"
version = "0.0.1-SNAPSHOT"
description = "Web portal for job offers for developers"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    // Spring Boot Core
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  	implementation("org.springframework.boot:spring-boot-starter-security")
  	implementation("org.springframework.boot:spring-boot-starter-validation")
  	implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    // BBDD Postgres
    runtimeOnly("org.postgresql:postgresql")

    // BBDD Migrations
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14")

    // Testing
  	testImplementation("org.springframework.boot:spring-boot-starter-test")
  	testImplementation("org.springframework.security:spring-security-test")
  	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
