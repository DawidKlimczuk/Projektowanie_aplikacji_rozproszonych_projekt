plugins {
    id("java")               // Dodanie wsparcia dla Javy
    id("application")        // Aplikacja konsolowa
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()           // Repozytorium Maven do pobrania bibliotek
}

dependencies {
    // RMI i podstawowa funkcjonalność Javy
    implementation("javax.activation:activation:1.1.1")

    // Spring Web do obsługi REST
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.5")

    // Spring Boot Starter Test dla testów
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.5")


    // Biblioteki REST (JAX-RS)
    implementation("org.glassfish.jersey.core:jersey-server:2.35")
    implementation("org.glassfish.jersey.containers:jersey-container-jdk-http:2.35")

    // Kolejki JMS
    implementation("org.apache.activemq:activemq-all:5.17.0") // ActiveMQ jako broker JMS

    // RPC obsługa - minimalna biblioteka (opcjonalnie do RPC)
    implementation("com.googlecode.json-simple:json-simple:1.1.1") // Przykład lekkiej biblioteki JSON

    // Logowanie dla lepszej diagnostyki
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")

    // JUnit do testów jednostkowych (opcjonalne)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

application {
    mainClass.set("Server") // Punkt wejścia do aplikacji - serwer główny
}

tasks.test {
    useJUnitPlatform()      // Konfiguracja testów JUnit 5
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Ustawienie wersji Javy
    }
}
