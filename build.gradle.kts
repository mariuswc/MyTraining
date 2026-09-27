import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

version = "4.2.0-M1"

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.springframework.boot:spring-boot-starter-web:$version")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$version")
    implementation("org.springframework.boot:spring-boot-starter-webflux:$version")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(24)
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    freeCompilerArgs.set(listOf("-Xannotation-default-target=param-property"))
}