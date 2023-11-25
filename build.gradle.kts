import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.openapi.generator") version "6.6.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "ru.viklover"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework:spring-jdbc")
    implementation("org.openapitools:openapi-generator-gradle-plugin:6.6.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

val artifactId = rootProject.name
val generatedSourcesPath = "${layout.buildDirectory.get()}/generated"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
    dependsOn("openApiGenerate")
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/src/main/resources/contracts/endpoints.yaml")
    outputDir.set(generatedSourcesPath)
    apiPackage.set("$group.$artifactId.name.contracts.controller")
    modelPackage.set("$groupId.$artifactId.contracts.models")
    packageName.set("$groupId.$artifactId.contracts")
    configOptions.set(
        mapOf(
            "reactive" to "true",
            "useSpringBoot3" to "true",
            "useSwaggerUI" to "false",
            "interfaceOnly" to "true",
            "annotationLibrary" to "none",
            "packageName" to "$groupId.$artifactId.contracts",
            "documentationProvider" to "none"
        )
    )
}

kotlin.sourceSets["main"].kotlin.srcDir("$generatedSourcesPath/src/main/kotlin")

// tasks.withType<Test> {
//     useJUnitPlatform()
// }

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}
