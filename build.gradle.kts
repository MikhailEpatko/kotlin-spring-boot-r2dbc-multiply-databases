import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    id("io.gitlab.arturbosch.detekt").version("1.21.0")
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20"
    kotlin("plugin.jpa") version "1.7.20"
}

apply {
    detekt {
        config = files("${project.rootDir}/detekt/config.yml")
        parallel = true
    }
}

group = "ru.epatko.application"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:2.7.4")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.7")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")

    runtimeOnly("org.postgresql:postgresql:42.5.0")
    runtimeOnly("org.postgresql:r2dbc-postgresql:0.9.2.RELEASE")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}